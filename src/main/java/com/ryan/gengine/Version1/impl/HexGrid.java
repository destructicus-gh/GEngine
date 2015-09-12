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
    private class FinalPoint extends Point{
        private int x;
        private int y;
        public FinalPoint(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null)
                return false;

           //System.out.println(o instanceof Point);
            if(!(o instanceof Point)) return false;

            FinalPoint f;
            if (o.getClass() == FinalPoint.class){
                f = (FinalPoint) o;
            }
            else{
                Point p = (Point) o;
                f = new FinalPoint(p.x, p.y);
            }
            FinalPoint that = f;

            //System.out.println(this.x+" "+that.x);
            //System.out.println(this.y+" "+that.y);
            if (x != that.x) return false;
            return y == that.y;

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + x;
            result = 31 * result + y;
            return result;
        }

    }


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

        for (int x = 0;x< d.width;x++){
            for (int y = 0;y< d.height;y++){
                pieces.put(new Point(x, y), new HexPiece<>(new FinalPoint(x, y)));
            }
        }
        root = getAt(new Point (0,0));
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
        for(Point p: pieces.keySet()){
            //System.out.println(p.toString() + pieces.get(p).toString());
        }


    }
    public HexPiece<E> getAt(Point p){
        return (pieces.get(p));


    }
    public E getValueAt(Point p){
        return (pieces.get(p)).value;

    }
    public FinalPoint fromPoint(Point p){
        //System.out.println("n"+p.toString());
        return new FinalPoint(p.x, p.y);
    }

    public Dimension getDimension() {
        return dimension;
    }
}
