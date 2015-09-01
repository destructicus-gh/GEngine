package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.service.GameOutput;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class SimplePrintOutput implements GameOutput {
    private Game g;
    public void addGame(Game g) {
        this.g = g;
    }

    public void sendEvent(GEvent gEvent) {
        if (gEvent.toString() == null|| "null".trim().equals(gEvent.toString())) return;
        System.out.println("Event:"+gEvent+"!");
    }

    @Override
    public void stop() {

    }
}
