package com.example.demo.botApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

@Component
public class MainHandler {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public MainHandler() {}

    public SendMessage handleUpdate(Update update) {
        Message message = update.getMessage();
        //Проверка на наличия update
        if (message != null && !message.hasText()) {
            SendMessage ErrorreplyMessage = new SendMessage();
            ErrorreplyMessage.setChatId(message.getChatId().toString());
            ErrorreplyMessage.setText("Неверный формат запроса");
            return ErrorreplyMessage;
        }
            if (message != null && message.hasText()) {
                String inputMsg = message.getText();
                SendMessage replyMessage = new SendMessage();
                String botState = "";
                Rate rate = new Rate();
                replyMessage.setChatId(message.getChatId().toString());

                //Настройка клавиатуры
                replyMessage.setReplyMarkup(replyKeyboardMarkup);
                ArrayList<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow keyboardFirstRow = new KeyboardRow();
                KeyboardRow keyboardSecondRow = new KeyboardRow();

                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);

                keyboard.clear();
                keyboardFirstRow.clear();
                keyboardFirstRow.add("Курс Доллара \uD83C\uDDFA\uD83C\uDDF8");
                keyboardFirstRow.add("Курс Евро \uD83C\uDDEA\uD83C\uDDFA");
                keyboardFirstRow.add("Курс Рубля \uD83C\uDDF7\uD83C\uDDFA");
                keyboardSecondRow.add("Помощь");
                keyboard.add(keyboardFirstRow);
                keyboard.add(keyboardSecondRow);
                replyKeyboardMarkup.setKeyboard(keyboard);

                //Обработка кнопок
                switch (inputMsg) {
                    case "/start":
                        replyMessage.setText("Приветствую! Чтобы получить инфромацию о текущих курсах валют: нажмите на одну из кнопок ниже");
                        replyMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                    case "Курс Доллара \uD83C\uDDFA\uD83C\uDDF8":
                        botState = "США";
                        replyMessage.setText("Курс доллара к тенге в народном банке на " + rate.getRateToUser(botState));
                        replyMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                    case "Курс Евро \uD83C\uDDEA\uD83C\uDDFA":
                        botState = "Евро";
                        replyMessage.setText("Курс евро к тенге в народном банке на " + rate.getRateToUser(botState));
                        replyMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                    case "Курс Рубля \uD83C\uDDF7\uD83C\uDDFA":
                        botState = "рубль";
                        replyMessage.setText("Курс российского рубля к тенге в народном банке на " + rate.getRateToUser(botState));
                        replyMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                    default:
                        replyMessage.setText("Нажмите на кнопку выбора валюты");
                        replyMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                }
                return replyMessage;
            }
        return null;
    }
}


