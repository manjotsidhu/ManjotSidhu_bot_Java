package com.github.manjotsidhu.manjotsidhu_bot;

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
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Message Recieved:\n" + update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Manjot Sidhu Bot";
    }

    @Override
    public String getBotToken() {
        if(botToken.equals("")) { 
            System.err.println("Bot API token not set");
            System.exit(1);
        }
        return botToken;
    }
}
