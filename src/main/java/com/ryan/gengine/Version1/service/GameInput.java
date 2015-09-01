package com.ryan.gengine.Version1.service;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public interface GameInput {
    void sendInput(String input) throws InterruptedException;
    String getInput() throws InterruptedException;
}
