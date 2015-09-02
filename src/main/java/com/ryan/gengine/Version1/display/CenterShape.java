package com.ryan.gengine.Version1.display;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by a689638 on 9/2/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class CenterShape extends FrameContentObject {
    ShapeDrawData drawData;
    int sides;

    public CenterShape(ShapeDrawData shapeDrawData, int sides){
        drawData = shapeDrawData;
        this.sides = sides;
    }



    private Polygon generateShape(Dimension offset){
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint(
                    (int) (drawData.currentPoint.x + offset.width + drawData.distanceFromCenter
                            * Math.cos(i * 2 * Math.PI / this.sides)),
                    (int) (drawData.currentPoint.y + offset.height + drawData.distanceFromCenter
                            * Math.sin(i * 2 * Math.PI / this.sides)));
        return p;
    }

    @Override
    public void draw(Graphics g, Dimension offset) {
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform af = new AffineTransform();
        af.rotate(drawData.rotation);
        Shape rotatedShape = af.createTransformedShape(shape);

        shape = generateShape(offset);
        if (drawData.drawFill){
            g2.setPaint(drawData.currentPaint);
            g2.fill(rotatedShape);
        }
        if (drawData.drawOutline){
            g2.setStroke(drawData.mainStroke);
            g2.setPaint(drawData.currentEdgePaint);
            g2.draw(rotatedShape);
        }






    }
}
