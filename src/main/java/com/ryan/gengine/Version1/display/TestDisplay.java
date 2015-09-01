package com.ryan.gengine.Version1.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class TestDisplay extends JPanel {
    int count  = 20;
    public TestDisplay(){
    }
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect((count+=10), 10, 10+count, 10);
    }
}
