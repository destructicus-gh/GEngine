package com.ryan.gengine.Version1.service;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public interface GameOutput {
    void addGame(Game g);
    void sendEvent(GEvent gEvent);
    void stop();
}
