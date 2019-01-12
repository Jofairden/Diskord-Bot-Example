package com.jofairden.commands

import com.jessecorbett.diskord.api.model.Message
import com.jofairden.BotCommand
import com.jofairden.DiskordBot
import org.springframework.stereotype.Component

@Component
class PingCommand : BotCommand("ping") {

    override suspend fun action(listener: DiskordBot, message: Message) {
        with(listener) {
            logger.info("I got a message :: ${message.content}")
            message.reply("pong")
        }
    }

}