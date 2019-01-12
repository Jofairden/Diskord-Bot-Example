package com.jofairden.services

import com.jessecorbett.diskord.api.model.Message
import com.jofairden.BotCommand
import com.jofairden.DiskordBot
import org.springframework.stereotype.Service

@Service
class CommandService(
    private val botCommands: List<BotCommand>
) {

    companion object {
        private const val PREFIX = "?"
    }

    suspend fun handleMessage(listener: DiskordBot, message: Message) {
        if (message.content.startsWith(PREFIX)) {
            botCommands.filter {
                message.content.startsWith("$PREFIX${it.trigger}")
            }.forEach {
                it.action(listener, message)
            }
        }
    }

}