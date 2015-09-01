package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;
import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameFrame;
import com.ryan.gengine.Version1.service.GameOutput;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public HexagonPiece() {



    }

    @Override
    public void draw(Graphics g, Dimension offset) {

        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setPaint(color);
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint(
                    (int) (localPosition.x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (localPosition.y + 50 * Math.sin(i * 2 * Math.PI / 6)));
        shape = p;
        g2.setStroke(new BasicStroke(10.0f));
        g2.fill(shape);
        GradientPaint redtowhite = new GradientPaint(0,0,Color.RED,100, 0,Color.WHITE, false);
        g2.setPaint(redtowhite);
        g2.draw(shape);





    }

    @Override
    public void dragStart() {

    }

    @Override
    public void dragging() {

    }

    @Override
    public void dragStop() {

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


class DraggablePanel extends GamePanel {

    Dimension distanceLock = null;
    Dimension offset = new Dimension(0, 0);
    Dimension contentSize;

    List<FrameContentObject> staticObjects = new ArrayList<>();
    List<FrameContentObject> draggableObjects = new ArrayList<>();
    List<FrameContentObject> backgroundDraggingObjects = new ArrayList<>();


    Point lastDrag;
    FrameContentObject draggedObject;


    boolean draggingField;
    Draggable draggableElement;


    DraggablePanel(Dimension contentSize) {
        this.contentSize = contentSize;

    }

    DraggablePanel(Dimension contentSize, Dimension offset) {
        this.contentSize = contentSize;
        this.offset = offset;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(getClick(e.getPoint()).localPosition);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastDrag = e.getPoint();
        draggedObject = getClick(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggingField) {
            draggingField = false;
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

    FrameContentObject getClick(Point p) {
        List<FrameContentObject> objectsHit = new ArrayList<>();
        for (FrameContentObject f : draggableObjects) {
            if (f.shape.contains(p)){
                objectsHit.add(f);
            }
        }
        Collections.sort(objectsHit, new Comparator<FrameContentObject>() {
            @Override
            public int compare(FrameContentObject o1, FrameContentObject o2) {
                return o1.height - o2.height;
            }
        });
        return objectsHit.size() == 0 ? null : objectsHit.get(0);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point dragTo = e.getPoint();
        FrameContentObject f = draggedObject;
        if (f == null || !(f instanceof Draggable)) { //drag field
            System.out.println("shouldnt be here");
            this.draggingField = true;
            this.offset = new Dimension(-1 * (lastDrag.x - dragTo.x) + offset.width, -1 * (lastDrag.y - dragTo.y) + offset.height);
            this.lastDrag = dragTo;
        }
        else{ //drag object
            f.localPosition = new Point(-1 * (lastDrag.x - dragTo.x) + f.localPosition.x, -1 * (lastDrag.y - dragTo.y) + f.localPosition.y);
            this.lastDrag = dragTo;
        }
        reUp();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (FrameContentObject frameContentObject : this.backgroundDraggingObjects) {
            frameContentObject.draw(g, this.offset);
        }
        for (FrameContentObject frameContentObject : this.draggableObjects) {
            frameContentObject.draw(g, this.offset);
        }
    }

    public void reUp() {
        if (distanceLock == null) return;
        if (distanceLock.width < offset.width) {
            offset.width = distanceLock.width;
        }

        if (distanceLock.height < offset.height) {
            offset.height = distanceLock.height;
        }
    }

    public void setDistanceLock(Dimension distance) {
        distanceLock = distance;
        reUp();

    }
}


public class DraggableDisplayTest extends GameFrame implements GameOutput {

    boolean running = true;
    DraggablePanel draggablePanel;

    public DraggableDisplayTest(String title, Dimension d) {
        super(title, d);
        this.draggablePanel = new DraggablePanel(new Dimension(d.width - 100, d.height - 100), new Dimension(0, 0));
        add(this.draggablePanel);
/*
        this.draggablePanel.backgroundDraggingObjects.add(new FrameContentObject(new Point(0, 0), new Rectangle(0, 0, 100, 100)) {
            @Override
            public void draw(Graphics g, Dimension offseter) {
                g.setColor(Color.BLACK);
                shape = new Rectangle(this.localPosition.x + offseter.width, this.localPosition.y + offseter.height, 200, 200);
                Graphics2D g2 = (Graphics2D) g;
                g2.fill(shape);
            }
        });
*/
        this.draggablePanel.addDraggableObject(new HexagonPiece());
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
