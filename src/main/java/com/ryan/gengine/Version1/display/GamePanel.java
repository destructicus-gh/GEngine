package com.ryan.gengine.Version1.display;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by a689638 on 8/31/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public abstract class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
    public GamePanel(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public synchronized void rpaint() {
        this.repaint();
    }
}
