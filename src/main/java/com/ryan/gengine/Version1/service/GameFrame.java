package com.ryan.gengine.Version1.service;

import javax.swing.*;
import java.awt.*;

/**
 * Created by a689638 on 8/31/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public abstract class GameFrame extends JFrame implements Runnable{
    private boolean running = true;
    private Dimension size;
    public GameFrame(String title, Dimension size){
        super(title);
        setLayout(new BorderLayout());
        this.size= size;
        setSize(size.width, size.height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
