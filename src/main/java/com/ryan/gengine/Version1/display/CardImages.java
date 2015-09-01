package com.ryan.gengine.Version1.display;

import com.ryan.gengine.Version1.impl.StandardCard;
import com.ryan.gengine.Version1.impl.StandardDeck;
import com.ryan.gengine.Version1.util.ImageCache;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a689638 on 8/19/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class CardImages {


    public static void init() {
        List<BufferedImage> cards = new ArrayList<>();
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File("C:\\Users\\a689638\\IdeaProjects\\GEngine\\src\\main\\resources\\classic-playing-cards.png"));
        } catch (IOException e) {
            return;
        }
        String dir = "C:\\Users\\a689638\\IdeaProjects\\GEngine\\src\\main\\resources\\";
        String[] values = new String[]{"A", "2","3","4","5","6","7","8","9","10", "J","Q","K"};
        int count = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 0; value < 13; value++) {
                BufferedImage temp = null;
                temp = bf.getSubimage((value) * 73, suit * 97, 73, 97);
                cards.add(temp);
                ImageCache.addImage(values[value] + "." + StandardDeck.Suit.Club.forIndex(suit).toString(), temp);
                File outputfile = new File(dir + "saved" + count++ + ".png");
                try {
                    ImageIO.write(temp, "png", outputfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }
    public static BufferedImage fromStandardCard(StandardCard card){
        return ImageCache.getImage(card.getValue()+"."+card.getSuit().name());
    }
}