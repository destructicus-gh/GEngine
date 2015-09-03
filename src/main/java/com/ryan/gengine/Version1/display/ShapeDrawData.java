package com.ryan.gengine.Version1.display;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by a689638 on 9/2/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class ShapeDrawData implements Serializable {
    public transient Paint currentPaint;
    public transient Paint currentEdgePaint;

    public Paint mainPaint;
    public Paint altPaint;

    public Paint mainEdgePaint;
    public Paint altEdgePaint;

    public Stroke mainStroke;

    public Point currentPoint;
    public Point originalPoint;
    public Dimension size;
    public int distanceFromCenter;

    public boolean drawFill;
    public boolean drawOutline;
    public double rotation;

    public int height;

    public ShapeDrawData(Paint mainPaint, Paint altPaint,
                         Paint mainEdgePaint, Paint altEdgePaint, Stroke mainStroke,
                         Point originalPoint, Dimension size, int distanceFromCenter, boolean drawFill,
                         boolean drawOutline, double rotation, int height) {


        this.mainPaint = mainPaint;
        this.currentPaint = mainPaint;
        this.currentEdgePaint = mainEdgePaint;
        this.altPaint = altPaint;
        this.mainEdgePaint = mainEdgePaint;
        this.altEdgePaint = altEdgePaint;
        this.mainStroke = mainStroke;
        this.currentPoint = originalPoint;
        this.originalPoint = originalPoint;
        this.size = size;
        this.distanceFromCenter = distanceFromCenter;
        this.drawFill = drawFill;
        this.drawOutline = drawOutline;
        this.rotation = rotation;
        this.height = height;
    }

    public ShapeDrawData() {
        this(Color.white, Color.black, Color.black, Color.darkGray, new BasicStroke(2.0f),
                new Point(0,0), new Dimension(0,0), 0, true, true, 0, 0);
    }

    public static ShapeDrawData fromFile(File f) {
        try (
                InputStream file = new FileInputStream(f);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer)
        ) {

            return (ShapeDrawData) input.readObject();

        } catch (ClassNotFoundException ex) {
            System.out.println("Cannot perform input. Class not found. " + ex);
        } catch (IOException ex) {
            System.out.println("Cannot perform input. " + ex);
        }
        return null;

    }


    public void toFile(File inFile) {
        try (
                OutputStream file = new FileOutputStream(inFile);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer)
        ) {
            output.writeObject(this);
        } catch (IOException ex) {
            System.out.println("Cannot perform output. " + ex);
        }

    }


    @Override
    public String toString() {
        return "ShapeDrawData{" +
                "currentPaint=" + currentPaint +
                ", currentEdgePaint=" + currentEdgePaint +
                ", mainPaint=" + mainPaint +
                ", altPaint=" + altPaint +
                ", mainEdgePaint=" + mainEdgePaint +
                ", altEdgePaint=" + altEdgePaint +
                ", mainStroke=" + mainStroke +
                ", currentPoint=" + currentPoint +
                ", originalPoint=" + originalPoint +
                ", size=" + size +
                ", distanceFromCenter=" + distanceFromCenter +
                ", drawFill=" + drawFill +
                ", drawOutline=" + drawOutline +
                ", rotation=" + rotation +
                ", height=" + height +
                '}';
    }

    public void setBothPoints(Point point) {
        this.originalPoint = point;
        this.currentPoint = point;
    }
}
