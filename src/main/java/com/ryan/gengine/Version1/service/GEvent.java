package com.ryan.gengine.Version1.service;

import java.util.Map;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */


public abstract class GEvent {
    private String type;
    private Map<String, Object> data;

    public GEvent(String type, Map<String, Object> data) {
        this.type = type;
        this.data = data;
    }

    public String getType(){
        return type;
    }

    public Object getData(String key){
        return data.get(key);
    }

    public abstract String toString();
}
