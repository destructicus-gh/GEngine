package com.ryan.gengine.Version1.display;

import java.awt.*;

/**
 * Created by a689638 on 8/31/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public abstract class FrameContentObject{
    String name;
    Point localPosition = new Point(0,0);
    Dimension bounds;
    Shape shape;
    public int height;

    FrameContentObject(Point p, Shape s){
        this.shape = s;
        localPosition = p;
    }
    FrameContentObject(){
    }

    public abstract void draw(Graphics g, Dimension offset);
}
