package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserStatistics extends JPanel {
    private JLabel label;

    public UserStatistics() {
        label = new JLabel();
        label.setFont(label.getFont().deriveFont(20.0f)); // הגדלת גודל הגופן ל-20

        this.add(label);
        treed();
    }

    public synchronized void treed() {
        System.out.println("+++++++++++++++++++++statistics is run++++++++++++++++++++++++++");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    label.setText(
                            "<html> Counter Requests:" + Bot.getCounterRequests() +
                                    ".<br>  PopularUserName:" + Bot.getPopularUserName() +
                                    ".<br>  PopularActivity:" + Bot.getPopularActivity() +
                                    ".<br>  UniqueUsers:" + Bot.getUniqueUsers().size() + "</html");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("--------------statistics is update---------------");
                }
            }
        }).start();
    }

}