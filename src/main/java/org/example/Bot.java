package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendGame;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ke.w
 * @version 0.1: Bot, v0.1 2024年07月24日 10:31 PM ke.w Exp $
 */
public class Bot extends TelegramLongPollingBot {

    InlineKeyboardButton start = InlineKeyboardButton.builder()
            .text("Start")
            .url("http://game.webxinxin.com/2048/")
            .build();

    private InlineKeyboardMarkup keyboardM1 = InlineKeyboardMarkup.builder()
            .keyboardRow(new ArrayList<>(Arrays.asList(start)))
            .build();

    @Override
    public String getBotUsername() {
        return "skyelvesTest0Bot";
    }

    @Override
    public String getBotToken() {
        return "7322478725:AAGAVA6Mjx4HB6kBbfAug7av6gSYPdvO1iU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message msg = update.getMessage();
            User user = msg != null ? msg.getFrom() : null;
            Long id = user.getId();
            String txt = msg.getText();
            if (txt.equals("/start")) {
                sendGame(id);
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String gameShortName = callbackQuery.getGameShortName();
            if ("test".equals(gameShortName)) {
                try {
                    // Example URL, should be replaced with your game URL
                    String gameUrl = "http://game.webxinxin.com/2048/";

                    // Call answerCallbackQuery method to send the URL
                    AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
                    answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
                    answerCallbackQuery.setUrl(gameUrl);

                    execute(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendGame(Long id) {
        String game_short_name = "test";
        SendGame sm = SendGame.builder()
                .chatId(id.toString())
                .gameShortName(game_short_name)
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb) {
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
