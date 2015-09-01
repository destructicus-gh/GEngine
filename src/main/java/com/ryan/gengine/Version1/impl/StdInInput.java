package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.service.GameInput;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class StdInInput implements GameInput, Runnable {
    private LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
    private boolean running = true;
    private Scanner sc = new Scanner(System.in);


    @Override
    public void sendInput(String input) throws InterruptedException {
        queue.transfer(input);
    }

    @Override
    public String getInput() throws InterruptedException {
        System.out.println("Press Enter");
        return queue.take();

    }

    @Override
    public void run() {
        while (running){
            try {

                String input = sc.nextLine();
                sendInput(input);

            } catch (InterruptedException | NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        running = false;
    }
}
