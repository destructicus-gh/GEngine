package com.ryan.gengine.Version1.util;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a689638 on 9/8/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class SVGTools {
    public static class InternalSVGData{
        public Rectangle rect;
        public List<Path2D.Double> paths = new ArrayList<>();
        public List<Color> colors = new ArrayList<>();

    }


    private static Double[] getPoints(String rawPoints){
        Pattern p = Pattern.compile("\\-?[\\d\\.]+");
        List<Double> list = new LinkedList<>();
        Matcher m = p.matcher(rawPoints);
        while (m.find()) {
            list.add(Double.parseDouble(m.group()));
        }
        return list.toArray(new Double[list.size()]);
    }


    private static Path2D.Double getPath(String rawContents){
        String paths = getParamString("d", rawContents);
        Pattern pattern = Pattern.compile("[MCcSsQqTtAaLl][\\d.\\-,\\s]+(?=[CcSsQqTtAaLlzZ])[zZ]?");
        Matcher matcher = pattern.matcher(paths);

        Path2D.Double path = new Path2D.Double();
        double[] cControl = new double[2];

        while(matcher.find()){
            String group = matcher.group();
            System.out.println(group);
            Character command = group.charAt(0);
            group = (group.endsWith("z")?group.substring(1):group.substring(1, group.length()-2));
            Double[] points = getPoints(group);

            if (Character.isLowerCase(command)){
                double[] pointDouble = new double[]{path.getCurrentPoint().getX(), path.getCurrentPoint().getY()};
                for (int i =0;i<points.length;i++){
                    points[i] += pointDouble[i%2];
                }
            }
            switch (Character.toUpperCase(command)){
                case 'M':
                    path.moveTo(points[0], points[1]);
                    break;
                case 'C':
                    path.curveTo(points[0], points[1], points[2], points[3], points[4], points[5]);
                    cControl[0] = 2*points[4] -points[2];
                    cControl[1] = 2*points[5] -points[3];
                    break;
                case 'S':
                    path.curveTo(cControl[0], cControl[1], points[0], points[1],points[2], points[3]);
                    break;
                case 'L':
                    path.lineTo(points[0], points[1]);
                    break;
            }
        }
        path.closePath();
        return path;
    }

    private static int getParamIntPx(String paramNanme, String contents){
        Pattern p = Pattern.compile(paramNanme + "=\"\\d*(px)?\"");
        Matcher m = p.matcher(contents);

        if (!m.find()) {
            return 0;
        }
        return Integer.valueOf(m.group().split("=")[1].replaceAll("[px\"]*", ""));
    }
    private static String getParamString(String paramName, String contents){
        Pattern p = Pattern.compile(paramName+"=\".*?\"");
        Matcher m = p.matcher(contents);
        if (!m.find()) System.out.println(p.pattern()+" no match with "+contents);
        return m.group().split("=")[1].replaceAll("\"", "");
    }

    public static InternalSVGData fromFile(File svgFile) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(svgFile));
        StringBuilder sb = new StringBuilder();
        String contents = "";
        while ((contents = br.readLine()) != null){
            sb.append(contents);
        }
        contents = sb.toString();

        InternalSVGData isvg = new SVGTools.InternalSVGData();
        isvg.rect = new Rectangle();

        isvg.rect.x = getParamIntPx("x", contents);
        isvg.rect.y = getParamIntPx("y", contents);
        isvg.rect.height = getParamIntPx("height", contents);
        isvg.rect.width = getParamIntPx("width", contents);

        Matcher m = Pattern.compile("<path.*?/>").matcher(contents);
        while (m.find()){
            String rawPath = m.group();
            Color c = Color.decode(getParamString("fill", rawPath));
            Path2D.Double p = getPath(rawPath);
            isvg.paths.add(p);
            isvg.colors.add(c);
        }
        return isvg;

    }

    public static BufferedImage toBufferedImage(InternalSVGData is){
        BufferedImage bf = new BufferedImage(is.rect.width, is.rect.height, BufferedImage.TYPE_INT_ARGB);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g = (Graphics2D) bf.getGraphics();
        g.setRenderingHints(rh);

        for(int i = 0; i< is.paths.size();i++){
            g.setColor(is.colors.get(i));
            g.fill(is.paths.get(i));
        }


        return bf;

    }
}
