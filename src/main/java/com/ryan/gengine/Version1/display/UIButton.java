package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by a689638 on 9/2/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class UIButton extends FrameContentObject implements Clickable

{
    Paint paint = Color.white;
    Paint altPaint = Color.white.darker();
    Paint textPaint = Color.black;
    Stroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );

    boolean down;

    public UIButton(Point place, Dimension size, Dimension arcSize) {
        super();
        height = Integer.MAX_VALUE;
        setPlace(place);
        shape = new RoundRectangle2D.Float(place.x, place.y, size.width, size.height, arcSize.width, arcSize.height);

    }

    @Override
    public void click(boolean leftClick) {
        System.out.println(this.name + leftClick);
    }

    @Override
    public void clickDown() {
        down = true;
    }

    @Override
    public void clickUp() {
        down = false;
    }

    @Override
    public void draw(Graphics g, Dimension offset) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setPaint(!down ? paint : altPaint);
        g2.setStroke(stroke);

        RoundRectangle2D r2d = (RoundRectangle2D) shape;
        g2.fill(r2d);
        g2.setPaint(textPaint);
        g2.draw(r2d);


        g2.setFont(new Font("Calibri", Font.BOLD, 18));
        drawCenteredString(this.name, (int) r2d.getWidth(), (int) r2d.getHeight(), g);

    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
}

