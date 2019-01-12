package com.jofairden

import com.jessecorbett.diskord.api.model.Message
import com.jessecorbett.diskord.api.rest.Embed
import com.jessecorbett.diskord.util.sendMessage
import org.apache.logging.log4j.kotlin.Logging

abstract class BotCommand(
    val trigger: String
) : Logging {

    abstract suspend fun action(message: Message)

    suspend fun reply(channelId: String, text: String, embed: Embed? = null)
            = DiskordBot.clientStore.channels[channelId].sendMessage(text, embed)
}