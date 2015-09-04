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
public class SnappableTest extends GameFrame {
    boolean running = true;
    RepeatingSquareBackground background;
    DraggablePanel panel;

    public SnappableTest(String title, Dimension size) {
        super(title, size);
        this.panel = new DraggablePanel(new Dimension(200, 200));//this size does nothing;
        Dimension tileSize = new Dimension(50, 50);
        this.background = new RepeatingSquareBackground(size,tileSize );
        this.background.name = "background";
        panel.backgroundDraggingObjects.add(this.background);
        this.background.height = -1;
        HexagonPiece h = new HexagonPiece();
        h.name = "hex";
        panel.addDraggableObject(h);


        ShapeDrawData data = new ShapeDrawData();
        data.mainPaint = Color.red;
        data.currentPaint = data.mainPaint;
        data.size = tileSize;
        data.distanceFromCenter = (int) Math.sqrt((data.size.width*data.size.width)*2)/2;
        data.rotation = Math.toRadians(45);
        data.setBothPoints(new Point(200, 200));
        SquareTile tile = new SquareTile(data);
        tile.snapSize = tileSize;
        tile.name = "square tile";
        panel.draggableObjects.add(tile);


        //System.out.println(data);
        add(this.panel);
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
