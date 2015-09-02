package com.ryan.gengine.Version1.display;

/**
 * Created by a689638 on 8/31/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public interface Draggable {
    boolean isDragging();
    void dragStart();
    void dragging();
    void dragStop();

}
