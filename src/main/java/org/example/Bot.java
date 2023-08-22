package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.*;

public class Bot extends TelegramLongPollingBot {
    private String userName;
    private Long chatId;
    private static Set<Long> uniqueUsers = new HashSet<>();
    private static Date date;
    private static List<Date> dates = new ArrayList<>();
    private static List<String> activity = new ArrayList<>();

     static List<String> lastTenactivityForHistory = new ArrayList<>();

    private static List<String> userNames = new ArrayList<>();
    private static List<String> x;
    private static int counterRequests = 0;
    private static String popularActivity;
    private static String popularUserName;

     static int numberCunt=0;
     static int activityCunt=0;
     static int jokeCunt=0;
    static int catFactCunt=0;
     static int quoteCunt=0;

    private static String selectedActivity;

    public String getUserName() {
        return userName;
    }

    public static Set<Long> getUniqueUsers() {
        return uniqueUsers;
    }

    public void onUpdateReceived(Update update) {
        uniqueUsers.add(chatId);
        System.out.println(uniqueUsers.toString() + " " + counterRequests + " " + popularUserName + " " + popularActivity + " LIST AC:" + activity + " users: " + userNames);
        System.out.println("arry: " + x);
        System.out.println("date: " + date);
        System.out.println("user name:" + this.getUserName());
        chatId = update.getMessage().getChatId();
        System.out.println(update.getMessage().getText());
        ButtonsForBot buttonsForBot = new ButtonsForBot();
        SendMessage sendMessage = new SendMessage();
        APIClient apiClient = new APIClient();
        SendMessage message = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        message.setReplyMarkup(buttonsForBot.createKeyboardMarkup());
        this.userName = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
        if (update.getMessage().getText().equals("/start")) {
            counterRequests++;
            sendMessageForMe(sendMessage, "chose");
            update.getMessage().setText(null);
        } else {
            if (update.getMessage().getText().equals("Joke")) {
                saveDataForMe("JOKE");
                sendMessageForMe(sendMessage, apiClient.getJOKE());
                this.jokeCunt++;


            } else if (update.getMessage().getText().equals("CatFact")) {
                saveDataForMe("CAT_FACT");
                this.catFactCunt++;


                sendMessageForMe(sendMessage, apiClient.getCAT_FACT());
            } else if (update.getMessage().getText().equals("Quote")) {
                saveDataForMe("QUOTE");
                this.quoteCunt++;


                sendMessageForMe(sendMessage, apiClient.getQUOTE());
            } else if (update.getMessage().getText().equals("NumberFact")) {
                saveDataForMe("NUMBER_FACT");
                sendMessageForMe(sendMessage, apiClient.getNUMBER_FACT());
                this.numberCunt++;


            } else if (update.getMessage().getText().equals("Activity")) {
                saveDataForMe("ACTIVITY");
                sendMessageForMe(sendMessage, apiClient.getACTIVITY());
                this.activityCunt++;
            }
            message.setText("chose");
            message.setChatId(chatId);
            executeForMe(message);
        }
        System.out.println("arry" + x);
        x.add(String.valueOf(counterRequests));
    }

    public void saveDataForMe(String sendMessage) {
        counterRequests++;
        selectedActivity = sendMessage;
        date = new Date();
        activity.add(sendMessage);
        userNames.add(userName);
        dates.add(date);
        lastTenactivityForHistory.add(userName+" || "+selectedActivity+" || "+date.getTime()+" | \n");
        Collections.reverse(lastTenactivityForHistory);

        popularActivity = findMostFrequentString(activity);
        popularUserName = findMostFrequentString(userNames);
    }

    public void sendMessageForMe(SendMessage sendMessage, String activityToSend) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(activityToSend);
        executeForMe(sendMessage);
    }

    public void executeForMe(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "ChaimReminderBot";
    }

    @Override
    public String getBotToken() {
        return "6059845154:AAFKBC5pjBMujfutMgNGj1RSzNtDy7siEnE";
    }

    public static Date getDate() {
        return date;
    }

    public static List<String> getUserNames() {
        return userNames;
    }

    public static String getPopularActivity() {
        return popularActivity;
    }

    public static String getPopularUserName() {
        return popularUserName;
    }

    public static int getCounterRequests() {
        return counterRequests;
    }

    public static String findMostFrequentString(List<String> strings) {
        Map<String, Integer> counter = new HashMap<>();
        if (strings != null) {
            for (String str : strings) {
                counter.put(str, counter.getOrDefault(str, 0) + 1);
            }
            String mostFrequentString = null;
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : counter.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentString = entry.getKey();
                }
            }
            return mostFrequentString;
        } else {
            return null;
        }
    }
}
