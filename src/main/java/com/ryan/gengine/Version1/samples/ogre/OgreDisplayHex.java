package com.ryan.gengine.Version1.samples.ogre;

import com.ryan.gengine.Version1.display.FrameContentObject;
import com.ryan.gengine.Version1.service.Clickable;

import java.awt.*;

/**
 * Created by a689638 on 9/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class OgreDisplayHex extends FrameContentObject implements Clickable {
    public Color clearColor = new Color(199, 243, 255, 255);
    public Color crateredColor = new Color(0, 0, 0, 255);
    public Color color;

    public OgreHex data;

    static int sizeNum = 20;

    public OgreDisplayHex(OgreHex data) {
        this.data = data;
        this.color = data.cratered ? crateredColor : clearColor;

    }

    @Override
    public void draw(Graphics g, Dimension offset) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        g2.setPaint(color);
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint(
                    (int) (getPlace().x + offset.width + sizeNum * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (getPlace().y + offset.height + sizeNum * Math.sin(i * 2 * Math.PI / 6)));
        this.shape = p;
        g2.setStroke(new BasicStroke(4.0f));
        g2.fill(shape);
        g2.setPaint(color.darker());
        g2.draw(shape);
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
