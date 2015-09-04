package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.Clickable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a689638 on 9/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class RepeatingBackground extends FrameContentObject implements Clickable{
    Dimension tileSize;
    Dimension windowSize;
    List<FrameContentObject> objects = new ArrayList<>();
    BufferedImage bakedTile;

    protected RepeatingBackground(Dimension windowSize, Dimension tileSize){
        this.tileSize = tileSize;
        this.windowSize = windowSize;
        this.height = Integer.MIN_VALUE;
        this.shape = new Rectangle(windowSize);
    }

    public RepeatingBackground(Dimension windowSize, Dimension tileSize, List<FrameContentObject> objects) {
        this(windowSize, tileSize);
        this.objects.addAll(objects);
        this.bakedTile = new BufferedImage(tileSize.width, tileSize.height, BufferedImage.TYPE_INT_ARGB);


        Graphics g = this.bakedTile.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        Dimension nope = new Dimension(0, 0);
        for (FrameContentObject f : objects) {
            f.draw(g, nope);
        }

    }

    public void rebakeTile(){
        this.bakedTile = new BufferedImage(tileSize.width, tileSize.height, BufferedImage.TYPE_INT_ARGB);


        Graphics g = this.bakedTile.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        Dimension nope = new Dimension(0, 0);
        for (FrameContentObject f : objects) {
            f.draw(g, nope);
        }



    }
    public void writeOut(String filename){
        try {

            File outputfile = new File(filename);
            ImageIO.write(bakedTile, "png", outputfile);
        } catch (IOException e) {
            System.out.println("f.");
        }

    }

    @Override
    public void draw(Graphics g, Dimension offset) {

        Dimension localOffset = new Dimension(offset.width%tileSize.width,offset.height%tileSize.height );
        for (int x = -1; x < tileSize.width; x++) {
            for (int y = -1; y < tileSize.height; y++) {
                g.drawImage(bakedTile, x*tileSize.width  + localOffset.width, y*tileSize.height + localOffset.height, null);
            }
        }
        g.fillOval(offset.width-20, offset.height-20, 40, 40);

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
