package com.github.manjotsidhu.manjotsidhu_bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ManjotSidhuBot extends TelegramLongPollingBot {

    String botToken = "";

    ManjotSidhuBot(String token) {
        botToken = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            
            String input = update.getMessage().getText();
            Integer inputId = update.getMessage().getMessageId();
            long chatId = update.getMessage().getChatId();

            Pattern ptn = Pattern.compile("\\/(\\w+)\\s(\\w+)\\s(.+)");
            Matcher mtch = ptn.matcher(input);

            if (mtch.find()) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setReplyToMessageId(inputId)
                        .setText("Test Passed");

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setReplyToMessageId(inputId)
                        .setText(Templates.ERROR);

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Manjot Sidhu Bot";
    }

    @Override
    public String getBotToken() {
        if (botToken.equals("")) {
            System.err.println("Bot API token not set");
            System.exit(1);
        }
        return botToken;
    }
}
