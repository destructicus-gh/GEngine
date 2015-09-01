package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;
import com.ryan.gengine.Version1.service.GTile;
import com.ryan.gengine.Version1.service.singles.HoverableTile;

import java.awt.*;

/**
 * Created by a689638 on 8/20/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
class GTileButton extends GTile implements Clickable, HoverableTile {
    private boolean raised = true;
    private Color upColor = Color.lightGray;
    private Color downColor = upColor.darker();

    public GTileButton(Point p, Dimension d) {
        super(p, d);
    }

    public GTileButton(Point p, Dimension d, String text) {
        this(p, d);
        this.text = text;
        this.textColor = Color.black;
    }

    @Override
    public void draw(Graphics g) {
        int trash = raised ? drawUp(g) : drawDown(g);
        g.setFont(this.font);
        g.setColor(textColor);
        g.drawString(text, location.x, location.y + 25);
    }

    int drawUp(Graphics g) {
        g.setColor(upColor);
        g.fill3DRect(location.x, location.y, dimensions.width, dimensions.height, raised);
        return 0;
    }

    int drawDown(Graphics g) {
        g.setColor(downColor);
        g.fillRect(location.x, location.y, dimensions.width, dimensions.height);
        return 0;
    }

    @Override
    public void click(boolean leftClick) {
        System.out.println("Clicked button " + text);
    }

    @Override
    public void clickDown() {
        raised = false;
    }

    @Override
    public void clickUp() {
        raised = true;
    }
    @Override
    public void hoverOn() {

    }

    @Override
    public void hoverOff() {
    }
    public Color getDownColor() {
        return downColor;
    }

    public void setDownColor(Color downColor) {
        this.downColor = downColor;
    }

    public Color getUpColor() {
        return upColor;
    }

    public void setUpColor(Color upColor) {
        this.upColor = upColor;
    }


}