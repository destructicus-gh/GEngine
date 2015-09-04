package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;
import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameFrame;
import com.ryan.gengine.Version1.service.GameOutput;

import java.awt.*;

/**
 * Created by a689638 on 8/31/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */


class HexagonPiece extends FrameContentObject implements Draggable, Clickable {
    Color color = Color.BLUE;
    public boolean isDragging = false;

    static int sizeNum = 20;

    public HexagonPiece() {


    }

    @Override
    public void draw(Graphics g, Dimension offset) {
        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setPaint(isDragging ? Color.red : color);
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint(
                    (int) (getPlace().x + offset.width + sizeNum * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (getPlace().y + offset.height + sizeNum * Math.sin(i * 2 * Math.PI / 6)));
        shape = p;
        g2.setStroke(new BasicStroke(4.0f));
        g2.fill(shape);
        //GradientPaint redtowhite = new GradientPaint(0,0,Color.RED,100, 0,Color.WHITE, false);
        g2.setPaint(color.darker());
        g2.draw(shape);


    }

    @Override
    public boolean isDragging() {
        return isDragging;
    }

    @Override
    public void dragStart() {
        height = 3;
        isDragging = true;
    }

    @Override
    public void dragging() {

    }

    @Override
    public void dragStop() {
        height = 0;
        isDragging = false;
    }

    @Override
    public void click(boolean leftClick) {

    }

    @Override
    public void clickDown() {

    }

    @Override
    public void clickUp() {

    }
}

public class DraggableDisplayTest extends GameFrame implements GameOutput {

    boolean running = true;
    DraggablePanel draggablePanel;

    public DraggableDisplayTest(String title, Dimension d) {
        super(title, d);
        this.draggablePanel = new DraggablePanel(new Dimension(d.width - 100, d.height - 100), new Dimension(0, 0));
        add(this.draggablePanel);

        float mult = 0;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                HexagonPiece h = new HexagonPiece();
                h.setPlace( new Point(
                        (i * 3 * HexagonPiece.sizeNum) + (int) (mult * HexagonPiece.sizeNum),
                        j * (int) (HexagonPiece.sizeNum * Math.cos(30 * 0.017))));
                h.name = "" + j + " " + i;
                this.draggablePanel.addDraggableObject(h);
            }
            mult = mult == 0 ? 1.5f : 0;
        }

        UIButton u = new UIButton(new Point(0, 0), new Dimension(150, 30), new Dimension(10, 10));
        u.name = "test button";
        this.draggablePanel.staticObjects.add(u);
        setVisible(true);
    }

    @Override
    public void addGame(Game g) {

    }

    @Override
    public void sendEvent(GEvent gEvent) {

    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            this.draggablePanel.rpaint();
        }
    }
}
