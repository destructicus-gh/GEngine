package com.ryan.gengine.Version1.service.singles;

/**
 * Created by a689638 on 7/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public abstract class AbstractCard implements Holdable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Holdable setHolder() {
        return this;

    }
}
