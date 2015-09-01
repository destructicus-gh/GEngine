package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.service.singles.Holder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by a689638 on 7/10/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class StandardCardHand implements Holder<StandardCard> {
    private Set<StandardCard> heldObjects = new HashSet<StandardCard>();

    public void add(StandardCard item) {
        heldObjects.add(item);
    }

    public boolean inHand(StandardCard item) {
        return heldObjects.contains(item);
    }

    public StandardCard drawFrom(StandardCard item) {
        if (!heldObjects.contains(item))
            return null;
        else {
            heldObjects.remove(item);
            return item;
        }
    }
}
