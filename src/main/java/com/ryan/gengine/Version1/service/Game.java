package com.ryan.gengine.Version1.service;

import com.ryan.gengine.Version1.util.GameParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */


public abstract class Game{
    Set<GameOutput> outputs = new HashSet<>();
    List<GameInput> inputs = new ArrayList<>();
    private boolean gameEnds = false;

    private GameParams params;
    public Game(GameParams params){
    }

    public GameParams getParams() {
        return params;
    }

    public void setParams(GameParams params) {
        this.params = params;
    }

    public final void registerOutput(GameOutput g){
        g.addGame(this);
        outputs.add(g);
    }
    public final void registerInput(GameInput g){

        inputs.add(g);
    }
    public final void sendEvent(GEvent event){
        for (GameOutput out: outputs){
            out.sendEvent(event);
        }
    }
    public final String getInput(){
        try {
            return inputs.get(0).getInput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }


    public final void endGame(){
        gameEnds = true;
    }

    public abstract void start();
    public abstract void each();
    public abstract void end();
    public final void begin(){
        if (outputs.isEmpty()){
            throw new IllegalStateException("No Outputs set!");
        }
        run();
    }

    private void run(){
        start();
        while (!gameEnds){
            each();
        }
        end();

    }

}
