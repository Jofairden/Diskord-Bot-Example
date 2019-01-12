package com.jofairden.config

import com.jofairden.DiskordBot
import com.jofairden.KtApp
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeanConfig {

    @Bean()
    open fun getBot(): DiskordBot {
        return KtApp.bot
    }
}