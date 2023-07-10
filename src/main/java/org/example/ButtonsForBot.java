package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonsForBot {
    private ReplyKeyboardMarkup keyboardRows = null;
    private KeyboardButton button1 = null;
    private KeyboardButton button2 = null;
    private KeyboardButton button3 = null;

    public ReplyKeyboardMarkup createKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        button1 = new KeyboardButton(Buttons.getNameToSend().get(0));
        button2 = new KeyboardButton(Buttons.getNameToSend().get(1));
        button3 = new KeyboardButton(Buttons.getNameToSend().get(2));
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(button1);
        row.add(button2);
        row.add(button3);
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
