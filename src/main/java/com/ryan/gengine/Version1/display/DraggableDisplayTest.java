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
import java.util.PriorityQueue;

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
                    (int) (localPosition.x + offset.width + sizeNum * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (localPosition.y + offset.height + sizeNum * Math.sin(i * 2 * Math.PI / 6)));
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


class DraggablePanel extends GamePanel {

    Dimension distanceLock = null;
    Dimension offset = new Dimension(0, 0);
    Dimension contentSize;

    List<FrameContentObject> staticObjects = new ArrayList<>();
    List<FrameContentObject> draggableObjects = new ArrayList<>();


    List<FrameContentObject> backgroundDraggingObjects = new ArrayList<>();


    Point lastDrag;
    FrameContentObject draggedObject;
    FrameContentObject clickedObject;


    boolean draggingField;
    Draggable draggableElement;


    DraggablePanel(Dimension contentSize) {
        this.contentSize = contentSize;

    }

    DraggablePanel(Dimension contentSize, Dimension offset) {
        this.contentSize = contentSize;
        this.offset = offset;

    }

    public PriorityQueue<FrameContentObject> newPQ() {
        return new PriorityQueue<>(1, new Comparator<FrameContentObject>() {
            @Override
            public int compare(FrameContentObject o1, FrameContentObject o2) {
                return o1.height - o2.height;
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        FrameContentObject f = getClick(e.getPoint());

        if (f == null) return;
        if (f instanceof Clickable) {
            ((Clickable) f).click(e.getButton() == MouseEvent.BUTTON1);
        }
        System.out.println(f.name);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastDrag = e.getPoint();
        FrameContentObject f = getClick(e.getPoint());
        if (f instanceof Draggable) {
            draggedObject = f;
        } else {
            draggedObject = null;
        }
        if (f instanceof UIButton){
            ((UIButton)f).clickDown();
            clickedObject = f;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggedObject != null) {
            ((Draggable) this.draggedObject).dragStop();
            draggedObject = null;
        }
        if (draggingField) {
            draggingField = false;

        }
        if (clickedObject != null){
            ((Clickable) clickedObject).clickUp();
        }

    }

    public void addDraggableObject(FrameContentObject frameContentObject) {
        this.draggableObjects.add(frameContentObject);
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    FrameContentObject getClick(Point p) {
        List<FrameContentObject> objectsHit = new ArrayList<>();

        for (FrameContentObject f : staticObjects) {
            if (f.shape.contains(p)) {
                objectsHit.add(f);
            }
        }
        if (!objectsHit.isEmpty()) {
            return objectsHit.get(0);
        }

        for (FrameContentObject f : draggableObjects) {
            if (f.shape.contains(p)) {
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
            this.draggingField = true;
            this.offset = new Dimension(-1 * (lastDrag.x - dragTo.x) + offset.width, -1 * (lastDrag.y - dragTo.y) + offset.height);
            this.lastDrag = dragTo;
        } else { //drag object
            Draggable d = ((Draggable) f);
            if (d.isDragging()) {
                d.dragging();
            } else {
                d.dragStart();
            }
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
        PriorityQueue<FrameContentObject> pq = newPQ();

        for (FrameContentObject frameContentObject : this.backgroundDraggingObjects) {
            frameContentObject.draw(g, this.offset);
        }

        pq.addAll(this.draggableObjects);
        while (!pq.isEmpty()) {
            pq.poll().draw(g, this.offset);
        }
        for (FrameContentObject frameContentObject : this.staticObjects) {
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

        float mult = 0;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                HexagonPiece h = new HexagonPiece();
                h.localPosition = new Point(
                        (i * 3 * HexagonPiece.sizeNum) + (int) (mult * HexagonPiece.sizeNum),
                        j * (int) (HexagonPiece.sizeNum * Math.cos(30 * 0.017)));
                h.name = "" + j + " " + i;
                this.draggablePanel.addDraggableObject(h);
            }
            mult = mult == 0 ? 1.5f : 0;
        }

        UIButton u = new UIButton(new Point (0,0), new Dimension(150, 30), new Dimension(10, 10));
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
