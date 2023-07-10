package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


import static org.example.Bot.getUserNames;

public class HistoryActivity extends JPanel {
    private static List<String> lastTen =getUserNames();

    private JLabel label;
    private List<String> totalUsers = new ArrayList<>();

    public void treedForLastTen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=====================treedForLastTen===================");
                while (true) {




                    label.setText("last 10 :" +lastTen+"==");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("+++++++++++++++++++last is update+++++++++++++++++++++++");
                }
            }
        }).start();
    }

    public  HistoryActivity() {
        label = new JLabel();
        label.setBounds(10, 10, 400, 20);
        add(label);
        treedForLastTen();
    }
}
