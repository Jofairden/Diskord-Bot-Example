package com.jofairden

import com.jessecorbett.diskord.api.model.Message
import com.jessecorbett.diskord.api.websocket.DiscordWebSocket
import com.jessecorbett.diskord.util.ClientStore
import com.jessecorbett.diskord.util.EnhancedEventListener
import com.jofairden.services.CommandService
import org.springframework.beans.factory.annotation.Autowired


class DiskordBot(
    token: String
) : EnhancedEventListener(token) {

    companion object {
        lateinit var webSocket: DiscordWebSocket
        lateinit var clientStore: ClientStore
    }

    init {
        webSocket = DiscordWebSocket(token, this)
        Companion.clientStore = clientStore
    }

    @Autowired
    private lateinit var commandService: CommandService

    fun start() {
        val factory = KtApp.appContext.autowireCapableBeanFactory
        factory.autowireBean(this)
        factory.initializeBean(this, DiskordBot::class.java.name)

        webSocket.start()
    }

    override suspend fun onMessageCreate(message: Message) {
        commandService.handleMessage(this, message)
    }
}

