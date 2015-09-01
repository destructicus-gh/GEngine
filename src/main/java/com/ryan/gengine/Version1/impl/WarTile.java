package com.ryan.gengine.Version1.impl;

import java.awt.*;

/**
 * Created by a689638 on 8/20/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
enum WarColors {
    player1(new Color(194, 35, 38, 128)),
    player2(new Color(2, 120, 120, 128)),
    activeComparison(new Color(253, 182, 50, 200)),
    collateral(new Color(128, 22, 56, 200)),
    extra(new Color(243, 115, 56, 128));

    private Color color;

    WarColors(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return this.color;
    }
}


public class WarTile extends StandardCardTile {
    private int owner;
    private boolean isComparing;
    private boolean isCollateral;


    public WarTile(Point p, Dimension d, StandardCard card) {
        super(p, d, card);
    }

    public WarTile(Point p, Dimension d) {
        super(p, d);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.drawCard(g);
        if (this.isComparing | this.isCollateral) {
            g2.setStroke(new BasicStroke(10));
            g2.setColor((isComparing) ? WarColors.activeComparison.getColor() : WarColors.collateral.getColor());
            g2.drawRect(location.x + 5, location.y + 5, dimensions.width - 10, dimensions.height - 10);
            g2.setColor((owner == 1) ? WarColors.player1.getColor() : WarColors.player2.getColor());
            this.fillRect(g2);
        } else {
            g.setColor((owner == 1) ? WarColors.player1.getColor() : WarColors.player2.getColor());
            this.fillRect(g);
        }

    }

    //Getters and Setters
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        isCollateral = false;
        isComparing = false;
        this.owner = owner;
    }

    public boolean getIsComparing() {
        return isComparing;
    }

    public void setIsComparing(boolean isComparing) {
        this.isComparing = isComparing;
    }

    public boolean getIsCollateral() {
        return isCollateral;
    }

    public void setIsCollateral(boolean isCollateral) {
        this.isCollateral = isCollateral;
    }
}
