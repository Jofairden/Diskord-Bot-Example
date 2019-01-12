package com.jofairden.commands

import com.jessecorbett.diskord.api.model.Message
import com.jofairden.BotCommand
import org.springframework.stereotype.Component

@Component
class PingCommand : BotCommand("ping") {

    override suspend fun action(message: Message) {
        logger.info("I got a message :: ${message.content}")
        reply(message.channelId, "Pong")
    }

}