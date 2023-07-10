package org.example;

import io.quickchart.QuickChart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static org.example.Bot.catFactCunt;

public class graph extends JPanel {
    private Set<String> requests = new HashSet<>();
    private Set<String> dates = new HashSet<>();
    private String updateURL = "chaim";
    private ImageIcon    image = null;

    public graph() {
        try {
            updateImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataUpdate();


    }
    public void dataUpdate() {
        requests.add(String.valueOf(Bot.getCounterRequests()));
        dates.add(String.valueOf(Bot.getDate()));
        System.out.println("dates " + dates);
        System.out.println("requests " + requests);
    }

    String imageUrl;
    public void updateImage() throws IOException {

         QuickChart chart = new QuickChart();

        new Thread(() -> {
            while (true) {

                setConfig(chart);

                String path = chart.getUrl();
                try {
                    image = new ImageIcon(new URL(path));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                repaint();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    private void setConfig(QuickChart chart) {
        imageUrl = ("{"
                + "    type: 'bar',"
                + "    data: {"
                + "        labels: ['Joke','cat fact', 'numbers fact', 'activity', 'Quotue'],"
                + "        datasets: [{"
                + "            label: 'Requests',"
                + "            data: ["+(Bot.jokeCunt)+", "+(Bot.catFactCunt)+", "+(Bot.numberCunt)+", "+(Bot.activityCunt)+", "+(Bot.quoteCunt)+"]"
                + "        }]"
                + "    }"
                + "}");


        chart.setConfig(imageUrl);

    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Image img = image.getImage();
            g.drawImage(img,12,200,this);
            repaint();
        } else {
        }


    }
}
