package com.ryan.gengine.Version1.service.singles;

import com.ryan.gengine.Version1.service.GElement;

/**
 * Created by a689638 on 7/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public interface Holdable extends GElement {
    Holdable setHolder(Holder h);
}
