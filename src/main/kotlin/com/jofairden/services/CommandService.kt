package com.jofairden.services

import com.jessecorbett.diskord.dsl.Bot
import com.jessecorbett.diskord.dsl.command
import com.jessecorbett.diskord.dsl.commands
import com.jofairden.BotCommand
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class CommandService(
    private val bot: Bot,
    private val botCommands: List<BotCommand>
) {

    companion object {
        private const val PREFIX = "?"
    }

    @PostConstruct
    fun init() {
        bot.commands(PREFIX) {
            botCommands.forEach {
                command(it.trigger) {
                    it.action(this)
                }
            }
        }
    }
}