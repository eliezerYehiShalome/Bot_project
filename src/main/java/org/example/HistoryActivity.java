package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.example.Bot.getUserNames;

public class HistoryActivity extends JFrame {
    private List<String> lastTen = Bot.lastTenactivityForHistory;
    Font fontTytel = new Font("avi", Font.BOLD, 21);
    private JLabel label;

    public HistoryActivity() {


        this.setTitle("History Activity");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setBackground(Color.RED);

        JLabel[] labels = new JLabel[10];

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(10, 70 + (i * 20), 300, 20);
            this.add(labels[i]);
        }


        JLabel titleLabel = new JLabel("History");
        titleLabel.setFont(fontTytel);
        titleLabel.setBounds(10, 10, 200, 50);
        this.add(titleLabel);

        new Thread(() -> {
            while (true) {
                Collections.reverse(lastTen);

                for (int i = 0; i < labels.length; i++) {
                    if (i < lastTen.size()) {
                        labels[i].setText((i + 1) + ") " + lastTen.get(i));
                    } else {
                        labels[i].setText("");
                    }
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
