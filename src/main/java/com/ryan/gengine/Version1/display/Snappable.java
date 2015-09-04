package com.ryan.gengine.Version1.display;

import java.awt.*;

/**
 * Created by a689638 on 9/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public interface Snappable {
    Point snapTo();
    Point locateTile();
}
