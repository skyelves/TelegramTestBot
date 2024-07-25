package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;

/**
 * @author ke.w
 * @version 0.1: Bot, v0.1 2024年07月24日 10:31 PM ke.w Exp $
 */
public class Bot extends TelegramLongPollingBot {

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
        if (update.getMessage().isCommand()) {
            System.out.println("Command received: " + update.getMessage().getText());
        }
        Message msg = update.getMessage();
        User user = msg != null ? msg.getFrom() : null;
        Long id = user.getId();
        sendMsg(id, "Hello " + user.getFirstName());
    }

    public void sendMsg(Long id, String msg) {
        SendMessage sm = SendMessage.builder()
                .chatId(id.toString()) //Who are we sending a message to
                .text(msg).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
