package com.github.manjotsidhu.manjotsidhu_bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ManjotSidhuBot extends AbilityBot {

    ManjotSidhuBot(String botToken, String username) {
        super(botToken, username);
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
                        .setText(String.valueOf(chatId));

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setReplyToMessageId(inputId)
                        .setText(Strings.ERROR);

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int creatorId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Ability sayHelloWorld() {
        return Ability
                  .builder()
                  .name("hello")
                  .info("says hello world!")
                  .locality(ALL)
                  .privacy(PUBLIC)
                  .action(ctx -> silent.send("Hello world!", ctx.chatId()))
                  .build();
    }
}
