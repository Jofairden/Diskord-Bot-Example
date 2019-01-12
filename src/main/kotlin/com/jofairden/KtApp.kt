package com.jofairden

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
open class KtApp {

    companion object {
        lateinit var appContext: ConfigurableApplicationContext
        lateinit var bot: DiskordBot

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val job = GlobalScope.launch {
                // We need to read our token safely
                // NOBODY MUST KNOW YOUR BOT TOKEN! KEEP IT SAFE!
                // Recommended is to read it from a (local) file or grab
                // it from an environment variable
                bot = DiskordBot(getBotToken())
            }
            job.join()

            appContext = runApplication<KtApp>(*args) {
                setBannerMode(Banner.Mode.CONSOLE)
                webApplicationType = WebApplicationType.NONE
            }

            bot.start()
        }

        private fun getBotToken(): String {
            val classLoader = KtApp::class.java.classLoader
            val file = File(classLoader.getResource("bot.token")!!.file)

            if (!file.canRead()) {
                throw RuntimeException("Unable to read bot.token file")
            }

            return file.readText()
        }
    }
}