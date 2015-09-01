package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameOutput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by a689638 on 8/13/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class CivilWarDisplay extends JPanel implements Runnable, GameOutput {
    private Game g;
    LinkedTransferQueue<GEvent> events = new LinkedTransferQueue<>();
    private boolean running = true;
    private final Color[][] grid = new Color[4][13];
    {
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 0; value < 13; value++) {
                setColor(suit, value, Color.blue, "init");
            }
        }
    }
    public final int squareSize = 50;

    private int player1count = 1;

    private final Color player1 = new Color(0xC22326);
    private final Color player2 = new Color(0x027878);
    private final Color activeComparison = new Color(0xFDB632);
    private final Color collateral = new Color(0x801638);
    private final Color extra = new Color(0xF37338);

    public void setColor(int suit, int value, Color color, String from) {
        grid[suit][value] = color;
        rpaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (running) {
            if (!events.isEmpty()) {
                try {
                    process(events.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void addGame(Game g) {
        this.g = g;
    }

    public void process(GEvent gEvent) {

        switch (gEvent.getType()) {
            case "compare":
                int[] location1 = (int[]) gEvent.getData("location1");
                int[] location2 = (int[]) gEvent.getData("location2");
                setColor(location1[0], location1[1], activeComparison, "compare 1");
                setColor(location2[0], location2[1], activeComparison, "compare 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "owns":
                ArrayList<Point> points = ((ArrayList) gEvent.getData("location"));
                Color owner = ((int) gEvent.getData("owner") == 1) ? player1 : player2;
                for (Point point: points){
                    setColor(point.x, point.y, owner, "owns");
                }
                break;
            case "count":
                player1count = (int) gEvent.getData("count");
                break;
            case "collateral":
                for (int[] point : (int[][]) gEvent.getData("points")) {
                    setColor(point[0], point[1], collateral, "collateral");
                }
                break;

        }

    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public void sendEvent(GEvent gEvent) {
        events.offer(gEvent);
    }

    public void rpaint(){
        this.repaint();
        this.invalidate();
        this.revalidate();


    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 0; value < 13; value++) {

                g.setColor(grid[suit][value]);
                g.fillRect(suit * squareSize, value * squareSize, squareSize, squareSize);

            }
        }

        int bdcw = (4 * squareSize) / 52;
        int sideOffset = ((4 * squareSize) - (bdcw * 52)) / 2;

        for (int i = sideOffset; i < 52 + sideOffset; i++) {
            if (sideOffset + player1count > i) {
                g.setColor(player1);
            } else {
                g.setColor(player2);
            }
            g.fillRect(i, 13 * squareSize, bdcw, squareSize);
        }
    }
}
