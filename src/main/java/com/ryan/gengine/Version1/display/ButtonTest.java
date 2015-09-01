package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.service.GTile;
import com.ryan.gengine.Version1.service.GridDisplay;
import com.ryan.gengine.Version1.service.GridPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a689638 on 8/20/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */



public class ButtonTest extends GridDisplay {
    public ButtonTest() {
        super("", new GridPanel(new Dimension(3, 2), new Dimension(40, 40)), new Dimension(135, 115));
        List<List<GTile>> tiles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<GTile> temp = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                temp.add(new GTileButton(
                        new Point(i * this.gridPanel.getEachTileSize().width, j * this.gridPanel.getEachTileSize().height),
                        new Dimension(40, 40),
                        "" + i + j));
            }
            tiles.add(temp);
        }
        gridPanel.setGrid(tiles);
    }

    @Override
    public void run() {

    }
}
