package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Buttons extends JPanel {
    private final int CHECK_BOX_WIDTH = 100;
    private final int CHECK_BOX_HEIGHT = 50;
    private final int CHECK_BOX_X = 600;
    private int checkBoxY = 100;
    private int counter = 0;
    private UserStatistics userStatistics = new UserStatistics();
    private HistoryActivity button = new HistoryActivity();
    private graph graph = new graph();
    private JLabel label = new JLabel();

    public static List<String> getNameToSend() {
        return nameToSend;
    }

    public Buttons() {
        this.setLayout(null);
        JCheckBox[] checkBoxes = new JCheckBox[5];
        String[] nameOfCheckBox = {"Joke", "CatFact", "Quote", "NumberFact", "Activity"};
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(nameOfCheckBox[i]);
            checkBoxes[i].setBounds(CHECK_BOX_X, checkBoxY, CHECK_BOX_WIDTH, CHECK_BOX_HEIGHT);
            checkBoxY += 50;
            int index = i;
            checkBoxes[i].addActionListener(e -> {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                boolean selected = checkBox.isSelected();
                if (selected) {
                    if (counter >= 3) {
                        checkBox.setSelected(false);
                        JOptionPane.showMessageDialog(null,
                                "You cannot select more than three actions.",
                                "bot",
                                 JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        counter++;
                        nameToSend.add(nameOfCheckBox[index]);
                        System.out.println(nameOfCheckBox[index] + " is selected: " + selected);
                        System.out.println("Selection counter: " + counter);
                    }
                } else {
                    counter--;
                    nameToSend.remove(nameOfCheckBox[index]);
                }
            });
            this.add(checkBoxes[i]);
        }
        Font font = new Font("avi", Font.BOLD, 21);
        Font fontForUserStatistics = new Font("avjji", Font.BOLD, 14);
        userStatistics.setBounds(1, 20, 300, 180);
        userStatistics.setFont(font);
        add(userStatistics);




        label.setText("<html>Choose three activities </html>");
        label.setBounds(CHECK_BOX_X - 58, 6, 300, 80);
        label.setFont(font);
        this.add(label);

        JButton button=new JButton("History");
        button.setFont(font);
        button.setBackground(Color.CYAN);
        button.setBounds(300, 12, 200, 100);
        button.addActionListener((e -> {

            HistoryActivity historyActivity= new HistoryActivity();
            historyActivity.setVisible(true);

        }));
        add(button);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graph.paint(g);
        repaint();
    }

    public static List<String> nameToSend = new ArrayList<>();
}
