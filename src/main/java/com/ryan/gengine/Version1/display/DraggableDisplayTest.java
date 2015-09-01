package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameFrame;
import com.ryan.gengine.Version1.service.GameOutput;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a689638 on 8/31/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
class DraggablePanel extends GamePanel {


    Dimension offset = new Dimension(0, 0);
    Dimension contentSize;

    List<FrameContentObject> staticObjects = new ArrayList<>();
    List<FrameContentObject> draggableObjects = new ArrayList<>();
    List<FrameContentObject> backgroundDraggingObjects = new ArrayList<>();


    Point lastDrag;


    boolean draggingField;
    DraggableElement draggableElement;


    DraggablePanel(Dimension contentSize) {
        this.contentSize = contentSize;

    }

    DraggablePanel(Dimension contentSize, Dimension offset) {
        this.contentSize = contentSize;
        this.offset = offset;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastDrag = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggingField) {
            draggingField = false;
            System.out.println("Def done dragging");
        }

    }

    public void addDraggableObject(FrameContentObject frameContentObject) {
        draggableObjects.add(frameContentObject);
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    FrameContentObject getClick(Point p){
        for (FrameContentObject f: draggableObjects){

        }
        return null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point dragTo = e.getPoint();
        FrameContentObject f = getClick(dragTo);
        if (f == null||!(f instanceof DraggableElement)){
            this.draggingField = true;
            System.out.println("Dragged" + dragTo.toString());
            this.offset = new Dimension(-1*(lastDrag.x - dragTo.x) + offset.width, -1*(lastDrag.y - dragTo.y) + offset.height);
            this.lastDrag = dragTo;
        }
        else{
            System.out.println("Drag on "+f.name);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (FrameContentObject frameContentObject : draggableObjects) {
            frameContentObject.draw(g, this.offset);
        }
    }
}


public class DraggableDisplayTest extends GameFrame implements GameOutput {

    boolean running = true;
    DraggablePanel draggablePanel;

    public DraggableDisplayTest(String title, Dimension d) {
        super(title, d);
        this.draggablePanel = new DraggablePanel(new Dimension(d.width - 100, d.height - 100), new Dimension(100, 100));
        add(this.draggablePanel);

        this.draggablePanel.addDraggableObject(new FrameContentObject(new Point(0, 0), new Rectangle(0,0,100,100)){
        @Override
            public void draw(Graphics g, Dimension offseter) {
                g.setColor(Color.BLACK);
                g.fillRect(this.localPosition.x + offseter.width, this.localPosition.y + offseter.height, 200, 200);
            }
        });
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
