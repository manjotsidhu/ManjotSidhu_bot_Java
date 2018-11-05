package com.github.manjotsidhu.manjotsidhu_bot;

import java.util.Scanner;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        String botToken = "";
        
        if(args.length == 0) {
            Scanner scan = new Scanner(System.in);
            botToken = scan.next();
        } else {
            botToken = args[0];
        }

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new ManjotSidhuBot(botToken, "Manjot Sidhu Bot"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
