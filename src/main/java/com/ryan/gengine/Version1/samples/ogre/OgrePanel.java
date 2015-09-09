package com.ryan.gengine.Version1.samples.ogre;

import com.ryan.gengine.Version1.display.DraggablePanel;
import com.ryan.gengine.Version1.display.UIButton;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a689638 on 9/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class OgrePanel extends DraggablePanel {

    Map<String, List<UIButton>> menus = new HashMap<>();


    public OgrePanel(Dimension contentSize) {
        super(contentSize);
        List<UIButton> menu;
        /////////////////////////////////////////////////////
        //Unit Placement
        menu = new ArrayList<>();

        menus.put("city placement", menu);
        /////////////////////////////////////////////////////
        //Ogre Placement
        menu = new ArrayList<>();

        menus.put("ogre placement", menu);
        /////////////////////////////////////////////////////
        //Unit Actions
        menu = new ArrayList<>();

        menus.put("unit movement", menu);
        /////////////////////////////////////////////////////
        //Ogre Actions
        menu = new ArrayList<>();

        menus.put("ogre movement", menu);
        /////////////////////////////////////////////////////


    }


}
