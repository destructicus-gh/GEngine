package com.ryan.gengine.Version1.display;

import java.awt.*;

/**
 * Created by a689638 on 9/2/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class ShapeDrawData {
    Paint currentPaint;
    Paint currentEdgePaint;

    Paint mainPaint;
    Paint altPaint;

    Paint mainEdgePaint;
    Paint altEdgePaint;

    Stroke mainStroke;

    Point currentPoint;
    Point originalPoint;
    Dimension size;
    Integer distanceFromCenter;

    boolean drawFill;
    boolean drawOutline;
    double rotation;


}
