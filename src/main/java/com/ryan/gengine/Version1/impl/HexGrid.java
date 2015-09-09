package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.service.AbstractHex;

import java.awt.*;
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
public class HexGrid<E extends AbstractHex> { //uses odd-q indexing
    public static final String[] directionNames = new String[]{"up", "down", "upleft", "upright", "downleft", "downright"};
    private Dimension dimension;

    HexPiece<E> root;
    public Map<Point, HexPiece<E>> pieces = new HashMap<>();


    public class HexPiece<F> {
        public F value;
        Point place;
        Map<String, HexPiece<F>> directions = new HashMap<>();
        {
            for (String direction : directionNames) {
                directions.put(direction, null);
            }
        }

        public HexPiece(Point place) {
            this.place = place;
        }
    }

    public HexGrid(Dimension d){
        this.dimension = d;
        root = new HexPiece<>(new Point(0,0));
        for (int x = 0;x< d.width;x++){
            for (int y = 0;y< d.height;y++){
                pieces.put(new Point(x, y), new HexPiece<>(new Point(x, y)));
            }
        }
        for (Point place: pieces.keySet()){
            HexPiece<E> piece = pieces.get(place);
            if (place.x%2 == 1){ //x is odd
                piece.directions.put("upleft", getAt(new Point(place.x-1, place.y)));
                piece.directions.put("upright", getAt(new Point(place.x+1, place.y)));
                piece.directions.put("downleft", getAt(new Point(place.x-1, place.y+1)));
                piece.directions.put("downright", getAt(new Point(place.x+1, place.y+1)));
            }
            else{
                piece.directions.put("upleft", getAt(new Point(place.x-1, place.y-1)));
                piece.directions.put("upright", getAt(new Point(place.x+1, place.y-1)));
                piece.directions.put("downleft", getAt(new Point(place.x-1, place.y)));
                piece.directions.put("downright", getAt(new Point(place.x+1, place.y)));
            }
            piece.directions.put("up", getAt(new Point(place.x, place.y-1)));
            piece.directions.put("down", getAt(new Point(place.x, place.y+1)));
        }
    }
    public HexPiece<E> getAt(Point p){
        if (pieces.containsKey(p)){
            return (pieces.get(p));
        }
        return null;

    }
    public E getValueAt(Point p){
        if (pieces.containsKey(p)) {
            return (pieces.get(p)).value;
        }
        return null;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
