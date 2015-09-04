package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;

import java.awt.*;

/**
 * Created by a689638 on 9/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class SquareTile extends CenterSquare implements Clickable, Draggable, Snappable {
    Dimension snapSize;
    public SquareTile(ShapeDrawData shapeDrawData) {
        super(shapeDrawData);
        shape = new Rectangle(shapeDrawData.size);
    }

    @Override
    public void click(boolean leftClick) {
        System.out.println(this.name + "tile clicked");
    }

    @Override
    public void clickDown() {
        drawData.currentPaint = drawData.altPaint;
        drawData.currentEdgePaint = drawData.altEdgePaint;

    }

    @Override
    public void clickUp() {
        drawData.currentPaint = drawData.mainPaint;
        drawData.currentEdgePaint = drawData.mainEdgePaint;
    }

    @Override
    public boolean isDragging() {
        return false;
    }

    @Override
    public void dragStart() {
        height = 3;
    }

    @Override
    public void dragging() {

    }

    @Override
    public void dragStop() {
        height = 0;
    }

    @Override
    public Point snapTo() {
        int nearest = snapSize.width;
        int x =  nearest * Math.round(drawData.currentPoint.x /nearest);
        int y = nearest * Math.round(drawData.currentPoint.y /nearest);
        Point newPoint = new Point(x+(nearest/2), y+(nearest/2));
        System.out.println(newPoint);

        return new Point(newPoint.x, newPoint.y);
    }

    @Override
    public void draw(Graphics g, Dimension offset){
        super.draw(g, offset);
    }
    public Point locateTile(){

        return new Point (getPlace().x /snapSize.width, getPlace().y /snapSize.height);
    }


}
