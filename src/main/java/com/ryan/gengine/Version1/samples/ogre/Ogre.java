package com.ryan.gengine.Version1.samples.ogre;

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
public class Ogre extends Unit {
    Map<String, Part> attributes = new HashMap<>();
    {
        attributes.put("missiles", new Part(2, 3, 6, 5));
        attributes.put("mainBattery", new Part(1, 4, 4, 3));
        attributes.put("secondaryBattery", new Part(4, 3, 3, 2));
        attributes.put("antiPersonnel", new Part(8, 1, 1, 1));
        attributes.put("treads", new Part(45, 1, 0, 0));
    }

    private class Part {
        int quantity;
        int tentativeQuantity;
        int defense;
        int attack;
        int range;

        public Part(int quantity, int defense, int attack, int range) {
            this.quantity = quantity;
            this.defense = defense;
            this.attack = attack;
            this.range = range;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTentativeQuantity() {
            return tentativeQuantity;
        }

        public void setTentativeQuantity(int tentativeQuantity) {
            this.tentativeQuantity = tentativeQuantity;
        }

        @Override
        public String toString() {
            return "Part{" +
                    "quantity=" + quantity +
                    ", tentativeQuantity=" + tentativeQuantity +
                    ", defense=" + defense +
                    ", attack=" + attack +
                    ", range=" + range +
                    '}';
        }
    }

    @Override
    public String toString() {


        StringBuilder sb = new StringBuilder();
        for(String s: attributes.keySet()){
            sb.append(attributes.get(s).toString());
            sb.append("\n");
        }


        return "Ogre{" +
                "attributes=" + sb.toString() +
                '}';
    }
}
