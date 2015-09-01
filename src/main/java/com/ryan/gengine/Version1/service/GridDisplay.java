package com.ryan.gengine.Version1.service;

import java.awt.*;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */

public abstract class GridDisplay extends GameFrame {
    private boolean running = true;
    private Dimension size;

    protected GridPanel gridPanel;

    public GridDisplay(String title, GridPanel panel, Dimension d) {
        super(title, d);
        add(panel);
        gridPanel = panel;
        setVisible(true);
    }
}
