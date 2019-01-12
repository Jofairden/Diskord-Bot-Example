package com.jofairden.config

import com.jessecorbett.diskord.dsl.Bot
import com.jofairden.DiskordBot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeanConfig {

    @Bean()
    open fun getBot(): Bot {
        return DiskordBot.bot
    }
}