package com.ryan.gengine.Version1.samples.ogre;

import com.ryan.gengine.Version1.service.GEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a689638 on 9/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class OgreEvent extends GEvent {
    int type;
    public final static int UNIT_MOVE = 0;
    public final static int UNIT_FIRE = 1;
    public final static int TURN_END = 2;
    public final static int TURN_START = 3;
    public final static int STAT_UPDATE = 4;

    private OgreEvent(int type, String s, Map<String, Object> data) {
        super(s, data);
        this.type = type;
    }

    public OgreEvent(int type, Map<String, Object> data) {
        this(type, "not using this", data);
    }

    public static OgreEvent turnBegins(String player) {
        Map<String, Object> data = new HashMap<>();
        data.put("player", player);
        return new OgreEvent(OgreEvent.TURN_START, player + " starting", data);
    }

    public static OgreEvent turnEnds(String player) {
        Map<String, Object> data = new HashMap<>();
        data.put("player", player);
        return new OgreEvent(OgreEvent.TURN_END, player + " ending", data);
    }

    public static OgreEvent ogreUpdate(Ogre ogre) {
        Map<String, Object> data = new HashMap<>();
        data.put("ogre", ogre);
        return new OgreEvent(OgreEvent.STAT_UPDATE, ogre.toString(), data);
    }


    @Override
    public String toString() {
        return null;
    }
}
