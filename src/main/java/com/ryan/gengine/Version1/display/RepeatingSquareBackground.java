package com.ryan.gengine.Version1.display;

import java.awt.*;

/**
 * Created by a689638 on 9/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class RepeatingSquareBackground extends RepeatingBackground {
    public RepeatingSquareBackground(Dimension windowSize, Dimension tileSize) {
        super(windowSize, tileSize);
        if (tileSize.width!= tileSize.height)
            throw new IllegalArgumentException("Must be square;"+tileSize.width+"!="+tileSize.height);
        ShapeDrawData shapeDrawData = new ShapeDrawData();
        shapeDrawData.size = tileSize;
        shapeDrawData.setBothPoints(new Point(tileSize.width / 2, tileSize.height / 2));
        shapeDrawData.rotation = Math.toRadians(45);
        shapeDrawData.distanceFromCenter = (int) Math.sqrt((tileSize.width*tileSize.width)*2)/2;
        objects.add(new CenterSquare(shapeDrawData));
        rebakeTile();
    }
    public RepeatingSquareBackground(Dimension windowSize, Dimension tileSize, ShapeDrawData shapeDrawData) {
        super(windowSize, tileSize);
        objects.add(new CenterSquare(shapeDrawData));
        rebakeTile();
    }



}
