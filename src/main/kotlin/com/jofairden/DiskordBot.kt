package com.jofairden

import com.jessecorbett.diskord.dsl.Bot
import com.jessecorbett.diskord.util.ClientStore
import com.jessecorbett.diskord.util.EnhancedEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.Banner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import java.io.File


@SpringBootApplication
open class DiskordBot {

    companion object {
        lateinit var appContext: ConfigurableApplicationContext
        lateinit var bot: Bot
        lateinit var clientStore: ClientStore

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val job = GlobalScope.launch {
                // We need to read our token safely
                // NOBODY MUST KNOW YOUR BOT TOKEN! KEEP IT SAFE!
                // Recommended is to read it from a (local) file or grab
                // it from an environment variable
                bot = Bot(getBotToken(), false)
            }
            job.join()

            // Make clientStore accessible and take it over
            EnhancedEventListener::class.java.getDeclaredField("clientStore")
                .let {
                    it.isAccessible = true
                    val value = it.get(bot)
                    clientStore = value as ClientStore
                }

            // Start Spring
            appContext = runApplication<DiskordBot>(*args) {
                setBannerMode(Banner.Mode.CONSOLE)
                webApplicationType = WebApplicationType.NONE
            }

            // Start bot
            bot.start()
        }

        private fun getBotToken(): String {
            val classLoader = DiskordBot::class.java.classLoader
            val file = File(classLoader.getResource("bot.token")!!.file)

            if (!file.canRead()) {
                throw RuntimeException("Unable to read bot.token file")
            }

            return file.readText()
        }
    }
}

