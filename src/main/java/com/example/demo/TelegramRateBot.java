package com.example.demo;

import com.example.demo.botApi.MainHandler;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramRateBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    public TelegramRateBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }
    private  MainHandler mainHandler = new MainHandler();

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        SendMessage replyMessageToUser = mainHandler.handleUpdate(update);
        return replyMessageToUser;
    }

    @Override
    public String getBotUsername() {return botUserName;}

    @Override
    public String getBotToken() {return botToken;}

    @Override
    public String getBotPath() {return webHookPath;}


    public void setWebHookPath(String webHookPath) {this.webHookPath = webHookPath;}

    public void setBotUserName(String botUserName) {this.botUserName = botUserName;}

    public void setBotToken(String botToken) {this.botToken = botToken;}
}
