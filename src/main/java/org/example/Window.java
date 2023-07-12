package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("bot manager");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        Buttons buttons = new Buttons();
         this.add(buttons);
        this.setVisible(true);

    }

    public static final int WIDTH = 800;
    public static final int HEIGHT = 550;
}
