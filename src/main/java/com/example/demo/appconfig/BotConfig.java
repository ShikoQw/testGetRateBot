package com.example.demo.appconfig;

import com.example.demo.TelegramRateBot;
import com.example.demo.TestGetRateBot1Application;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public TelegramRateBot telegramRateBot() {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        TelegramRateBot telegramRateBot = new TelegramRateBot(options);
        telegramRateBot.setBotUserName(botUserName);
        telegramRateBot.setBotToken(botToken);
        telegramRateBot.setWebHookPath(webHookPath);

        return telegramRateBot;
    }
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

