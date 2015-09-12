package com.ryan.gengine.Version1.samples.ogre;

import com.ryan.gengine.Version1.display.DraggablePanel;
import com.ryan.gengine.Version1.impl.HexGrid;
import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameFrame;
import com.ryan.gengine.Version1.service.GameInput;
import com.ryan.gengine.Version1.service.GameOutput;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by a689638 on 9/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class OgreDisplay extends GameFrame implements GameOutput, GameInput{

    public Game ogreLogic;
    public TransferQueue<OgreEvent> tq = new LinkedTransferQueue<>();
    HexGrid<OgreHex> grid;
    public Ogre ogre;

    boolean running = true;
    DraggablePanel draggablePanel;

    public OgreDisplay(String title, Dimension size, HexGrid<OgreHex> grid) {
        super(title, size);
        this.grid = grid;

        float mult = 0;
        for (int j = 0; j < grid.getDimension().height; j++) {
            for (int i = 0; i < grid.getDimension().width; i++) {
                OgreHex oh = grid.getValueAt(new Point(i, j));
                assert oh != null:"No OgreHex at {"+i+", "+j+"}";
                OgreDisplayHex h = new OgreDisplayHex(grid.getValueAt(new Point(i, j)));
                h.setPlace( new Point(
                        (i * 3 * OgreDisplayHex.sizeNum) + (int) (mult * OgreDisplayHex.sizeNum),
                        j * (int) (OgreDisplayHex.sizeNum * Math.cos(30 * 0.017))));
                //h. = "" + j + " " + i;
                this.draggablePanel.addDraggableObject(h);
            }
            mult = mult == 0 ? 1.5f : 0;
        }


    }

    @Override
    public void run() {
        while(this.running){
            OgreEvent g = tq.poll();
            process(g);
        }
    }

    @Override
    public void addGame(Game g) {

    }

    @Override
    public void sendEvent(GEvent gEvent) { //called from elsewhere
        if(gEvent instanceof OgreEvent){
            tq.add((OgreEvent)gEvent);
        }

    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public void sendInput(String input) throws InterruptedException { //sends to game
        GEvent gEvent = new OgreEvent(0, new HashMap<>());
        ogreLogic.sendEvent(gEvent);
    }

    @Override
    public String getInput() throws InterruptedException { //gets input from waitable source
        return null;
    }

    private void updateOgre(Ogre ogre){

    }

    private void process(OgreEvent g){
        switch (g.type){
            case OgreEvent.TURN_START:
                break;
            case OgreEvent.TURN_END:
                break;
            case OgreEvent.STAT_UPDATE:
                updateOgre ((Ogre) g.getData("ogre"));
                break;

        }
    }

}
