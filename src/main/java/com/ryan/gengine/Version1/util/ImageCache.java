package com.ryan.gengine.Version1.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a689638 on 8/19/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class ImageCache {
    private static Map<String, BufferedImage> images = new HashMap<>();
    public static BufferedImage getImage(String name){
        return images.get(name);
    }
    public static void addImage(String name, BufferedImage image){
        images.put(name, image);
    }
    public static void addImages(Map<String, BufferedImage> newImages){
        images.putAll(newImages);
    }

}
