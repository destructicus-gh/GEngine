package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by a689638 on 9/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class DraggablePanel extends GamePanel {

    Dimension distanceLock = null;
    Dimension offset = new Dimension(0, 0);
    Dimension contentSize; //TO-DO: give this a purpose

    List<FrameContentObject> staticObjects = new ArrayList<>();
    List<FrameContentObject> draggableObjects = new ArrayList<>();


    public List<FrameContentObject> backgroundDraggingObjects = new ArrayList<>();


    Point lastDrag;
    FrameContentObject draggedObject;
    FrameContentObject clickedObject;


    boolean draggingField;
    Draggable draggableElement;


    public DraggablePanel(Dimension contentSize) {
        this.contentSize = contentSize;

    }

    public DraggablePanel(Dimension contentSize, Dimension offset) {
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
        if (f instanceof UIButton) {
            ((UIButton) f).clickDown();
            clickedObject = f;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggedObject != null) {
            ((Draggable) this.draggedObject).dragStop();

            if (draggedObject instanceof Snappable) {
                Snappable s = ((Snappable) draggedObject);
                Point newPlace = s.snapTo();
                draggedObject.setPlace(newPlace);
                System.out.println("at location" + s.locateTile());

            }
            draggedObject = null;
        }
        if (draggingField) {
            draggingField = false;

        }
        if (clickedObject != null) {
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

    List<FrameContentObject> getClicks(Point p) {
        List<FrameContentObject> objectsHit = new ArrayList<>();

        for (FrameContentObject f : staticObjects) {
            if (f.shape.contains(p)) {
                objectsHit.add(f);
                System.out.println(f.toString() + ":static");
            }
        }
        for (FrameContentObject f : draggableObjects) {
            try {
                if (f.shape.contains(p)) {
                    objectsHit.add(f);
                    System.out.println(f.toString() + ":draggable");
                }
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(f.name + " is broken");
                System.out.println(f.shape);
            }
        }
        for (FrameContentObject f : backgroundDraggingObjects) {
            if (f.shape.contains(p)) {
                objectsHit.add(f);
                System.out.println(f.toString() + ":backgrounddraggable");
            }
        }
        return objectsHit;
    }

    FrameContentObject getClick(Point p) {
        List<FrameContentObject> objectsHit = new ArrayList<>();

        for (FrameContentObject f : staticObjects) {
            if (f.shape.contains(p)) {
                objectsHit.add(f);

            }
        }
        if (!objectsHit.isEmpty()) {  //Blocks the UI, just in case
            return objectsHit.get(0);
        }

        for (FrameContentObject f : draggableObjects) {
            try {
                if (f.shape.contains(p)) {
                    objectsHit.add(f);

                }
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(f.name + " is broken");
                System.out.println(f.shape);

            }
        }
        for (FrameContentObject f : backgroundDraggingObjects) {
            if (f.shape.contains(p)) {
                objectsHit.add(f);
            }
        }

        Collections.sort(objectsHit, (o1, o2) ->//HERE
                (o1.height - o2.height != 0) ? (o2.height - o1.height) : o1.name.compareTo(o2.name));
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
            f.setPlace(new Point(-1 * (lastDrag.x - dragTo.x) + f.getPlace().x, -1 * (lastDrag.y - dragTo.y) + f.getPlace().y));
            this.lastDrag = dragTo;
        }
        reUp();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // getClicks(e.getPoint());
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
            FrameContentObject f = pq.poll();
            f.draw(g, this.offset);
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