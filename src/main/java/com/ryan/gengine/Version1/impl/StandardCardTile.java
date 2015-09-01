package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.service.GTile;

import java.awt.*;

/**
 * Created by a689638 on 8/19/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public abstract class StandardCardTile extends GTile {
    private StandardCard card;


    private boolean useCardImage = true;

    public StandardCardTile(Point p, Dimension d) {
        super(p, d);
    }

    public StandardCardTile(Point p, Dimension d, StandardCard card) {
        this(p, d);
        this.card = card;

    }

    public StandardCard getCard() {
        return card;
    }

    public void setCard(StandardCard card) {
        this.card = card;
    }

    public void setColor(Color color) {
        super.setColor(color);

    }
    public void drawCard(Graphics g){
        Graphics2D g2 = ((Graphics2D) g);
        if (image != null) {
            Image img2 = image.getScaledInstance(dimensions.width, dimensions.height,
                    Image.SCALE_AREA_AVERAGING);
            g2.drawImage(img2, location.x, location.y, dimensions.width, dimensions.height, null);
        }
    }





}
