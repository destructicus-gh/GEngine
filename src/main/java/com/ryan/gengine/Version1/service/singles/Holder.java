package com.ryan.gengine.Version1.service.singles;

/**
 * Created by a689638 on 7/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public interface Holder<T extends Holdable> {
    void add(T item);
    boolean inHand(T item);
    T drawFrom(T item);
}
