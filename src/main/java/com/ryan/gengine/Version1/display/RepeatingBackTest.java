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
        /*

        List<FrameContentObject> objects = new ArrayList<>();


        for (int i = 2;i<7;i++){
            ShapeDrawData shapeDrawData = new ShapeDrawData();
            shapeDrawData.distanceFromCenter = 10;
            shapeDrawData.drawFill = false;
            shapeDrawData.setBothPoints(new Point(20*i, 20*i));
            CenterShape centerShape = new CenterShape(shapeDrawData, 3+i);
            objects.add(centerShape);
        }
*/
        this.background = new RepeatingSquareBackground(size, new Dimension(100, 100));
        this.background.writeOut("test.png");

        panel.backgroundDraggingObjects.add(this.background);
        UIButton u = new UIButton(new Point(0, 0), new Dimension(150, 30), new Dimension(10, 10)){
            @Override
            public void click(boolean leftClick) {
                super.click(leftClick);
                background.rebakeTile();
                background.reeee();
            }
        };
        u.name = "Rebake Tile";
        panel.staticObjects.add(u);
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
            //this.background.rebakeTile();
        }
    }
}
