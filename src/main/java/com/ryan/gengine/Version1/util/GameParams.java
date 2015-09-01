package com.ryan.gengine.Version1.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class GameParams{
    private Map<String, String> gameParams = new HashMap<String, String>();
    public void addParam(String key, String value){
        if (gameParams.containsKey(key)){
            throw new IllegalArgumentException("Parameter "+key+" has already been created");
        }
        else{
            gameParams.put(key, value);
        }
    }
    public void updateParam(String key, String value){
        if (!gameParams.containsKey(key)){
            throw new IllegalArgumentException("Parameter "+key+" is not in parameters");
        }
        else{
            gameParams.put(key, value);
        }
    }
    public String getParam(String key){
        if (!gameParams.containsKey(key)){
            throw new IllegalArgumentException("Parameter "+key+" is not in parameters");
        }
        else{
            return gameParams.get(key);
        }
    }
}