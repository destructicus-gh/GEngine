package com.ryan.gengine.Version1.service;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public abstract class GTile {
    protected Dimension dimensions;
    protected Color color = Color.lightGray;
    protected Color textColor;
    protected String text;
    protected Font font;
    protected Point location;
    protected Point coordinates;
    protected BufferedImage image;

    private GTile(){
        this.font = new Font("TimesRoman", Font.PLAIN, 18);
    }

    public GTile(Point p, Dimension d){
        this();
        this.coordinates = p;
        this.location = new Point(p.x*d.width, p.y*d.height);
        this.dimensions = d;
    }



    public void fillRect(Graphics g){
        g.fillRect(location.x, location.y, dimensions.width, dimensions.height);
    }
    
    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public abstract void draw(Graphics g);

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}