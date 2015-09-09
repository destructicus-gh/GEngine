package com.ryan.gengine.Version1.samples.ogre;

import com.ryan.gengine.Version1.impl.HexGrid;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.util.GameParams;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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
public class OgreLogic extends Game {
    HexGrid<OgreHex> field;// = new HexGrid<>(new Dimension(15, 21));
    List<Unit> units = new ArrayList<>();
    Ogre ogre;

    TransferQueue <OgreEvent> events = new LinkedTransferQueue<>();

    boolean ogreTurn;


    public OgreLogic(GameParams params, HexGrid<OgreHex> field) {
        super(params);
        this.field = field;
    }

    @Override
    public void start() {
        for(Point p: field.pieces.keySet()){
            HexGrid.HexPiece hex = field.pieces.get(p);
            OgreHex ogreHex = new OgreHex();
            ogreHex.setPoint(p);
            hex.value = ogreHex;
        }
        field.pieces.get(new Point(3, 3)).value.cratered = true;
    }

    @Override
    public void each() {
        if (ogreTurn){
            this.sendEvent(OgreEvent.turnBegins("ogre"));
            this.sendEvent(OgreEvent.ogreUpdate(this.ogre));
            OgreEvent ogreEvent = new OgreEvent(-1, null);
            do{
                try {
                    ogreEvent = this.events.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            } while(ogreEvent.type != OgreEvent.TURN_END);
        }
        else{

        }
        ogreTurn = !ogreTurn;
    }

    @Override
    public void end() {

    }


}
