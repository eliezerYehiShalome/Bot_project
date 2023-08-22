package org.example;

import com.sun.tools.javac.Main;

public class FakeMain {
    public static void main(String[] args) {
        try {
            Main.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
