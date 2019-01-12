package com.jofairden

import com.jessecorbett.diskord.api.model.Message
import org.apache.logging.log4j.kotlin.Logging

abstract class BotCommand(
    val trigger: String
) : Logging {

    abstract suspend fun action(listener: DiskordBot, message: Message)
}