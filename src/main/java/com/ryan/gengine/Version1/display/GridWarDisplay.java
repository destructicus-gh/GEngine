package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.impl.StandardCard;
import com.ryan.gengine.Version1.impl.StandardCardTile;
import com.ryan.gengine.Version1.impl.StandardDeck;
import com.ryan.gengine.Version1.impl.WarTile;
import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameOutput;
import com.ryan.gengine.Version1.service.GridDisplay;
import com.ryan.gengine.Version1.service.GridPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
class OldWarTile extends StandardCardTile {
    private static boolean numbersShowing = false;
    private Integer number = 0;
    private Color ownerColor = null;

    public OldWarTile(Point p, Dimension d) {
        super(p, d);
        try {
            this.image = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\KoalaMini.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.dimensions = d;
        //this.color = WarColors.extra.getColor();
        this.font = new Font("TimesRoman", Font.PLAIN, 18);
    }

    public OldWarTile(Point p, Dimension d, Integer number) {
        this(p, d);
        this.number = number + 1;
    }


    public void setOwnerColor(Color color) {
        ownerColor = color;
    }

    public void setColor(Color color) {
        super.setColor(color);
        ownerColor = null;

    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = ((Graphics2D) g);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (image != null) {
            Image img2 = image.getScaledInstance(dimensions.width, dimensions.height,
                    Image.SCALE_AREA_AVERAGING);
            g2.drawImage(img2, location.x, location.y, dimensions.width, dimensions.height, null);

        }
        Color color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 125);
        g2.setColor(color);
        g2.fill3DRect(location.x, location.y, dimensions.width, dimensions.height, true);
        if (this.ownerColor != null) {
            this.ownerColor = new Color(this.ownerColor.getRed(), this.ownerColor.getGreen(), this.ownerColor.getBlue(), 125);
            g2.setColor(ownerColor);

            g2.fillRoundRect(location.x + 5, location.y + 5, dimensions.width - 10, dimensions.height - 10, 20, 20);
        }
        if (numbersShowing) {
            g.setFont(this.font);
            g2.setPaint(new Color(1, 1, 1, .5f));
            g2.drawString(number.toString(), location.x + ((number < 10) ? 16 : 13), location.y + 25);

        }


    }


}


public class GridWarDisplay extends GridDisplay implements GameOutput {

    private Game g;
    LinkedTransferQueue<GEvent> events = new LinkedTransferQueue<>();
    private boolean running = true;


    public GridWarDisplay(String title, Dimension tileDimension, Dimension eachTileDimension, Dimension windowDimension) {
        super(title, new GridPanel(tileDimension, eachTileDimension), windowDimension);
        List<List<WarTile>> grid = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<WarTile> temp = new ArrayList<>();
            for (int j = 0; j < tileDimension.height; j++) {
                WarTile ww = new WarTile(
                        new Point(i, j),
                        eachTileDimension);
                StandardCard card = new StandardCard(j,  StandardDeck.Suit.forIndex(i));
                ww.setCard(card);
                ww.setImage(CardImages.fromStandardCard(card));

                temp.add(ww);
            }
            grid.add(temp);
        }
        gridPanel.setGrid(grid);
        gridPanel.setContentAt(new Point(4, 0), new GTileButton(new Point(4,0), eachTileDimension, "Pause"));

    }

    @Override
    public void addGame(Game g) {

    }

    @Override
    public void sendEvent(GEvent gEvent) {
        events.offer(gEvent);
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {

                processEvent(events.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processEvent(GEvent gEvent) {
        switch (gEvent.getType()) {
            case "compare":
                Point location1 = (Point) gEvent.getData("location1");
                Point location2 = (Point) gEvent.getData("location2");


                ((WarTile) gridPanel.getContentAt(location1)).setIsComparing(true);
                ((WarTile) gridPanel.getContentAt(location2)).setIsComparing(true);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "owns":
                ArrayList points = ((ArrayList) gEvent.getData("points"));
                for (Object point : points) {
                    ((WarTile) gridPanel.getContentAt((Point) point)).setOwner((int) gEvent.getData("owner"));
                }
                break;
            case "count":
                //player1count = (int) gEvent.getData("count");
                break;
            case "collateral":
                for (Object point : (ArrayList) gEvent.getData("points")) {
                    ((WarTile) gridPanel.getContentAt((Point) point)).setIsCollateral(true);
                }
                break;

        }
        gridPanel.rpaint();
    }

}
