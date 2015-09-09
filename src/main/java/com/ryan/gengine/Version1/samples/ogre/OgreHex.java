package com.ryan.gengine.Version1.samples.ogre;

import com.ryan.gengine.Version1.service.AbstractHex;

import java.awt.*;
import java.util.List;

/**
 * Created by a689638 on 9/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class OgreHex extends AbstractHex{
    public boolean cratered = false;
    public List<Unit> unitsHere;


    @Override
    public void setPoint(Point point) {
        super.setPoint(point);
    }
}
