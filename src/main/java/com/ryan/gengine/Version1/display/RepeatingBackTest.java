package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.GameFrame;

import java.awt.*;

/**
 * Created by a689638 on 9/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class RepeatingBackTest extends GameFrame {
    boolean running = true;
    RepeatingSquareBackground background;
    DraggablePanel panel;
    public RepeatingBackTest(String title, Dimension size) {
        super(title, size);
        panel = new DraggablePanel(new Dimension(200,200));

        this.background = new RepeatingSquareBackground(size, new Dimension(100, 100));
        this.background.writeOut("test.png");

        panel.backgroundDraggingObjects.add(this.background);

        add(panel);
        this.setVisible(true);
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            this.panel.rpaint();
        }
    }
}
