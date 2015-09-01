package com.ryan.gengine.Version2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a689638 on 8/3/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
class Piece{

}

class Token extends Piece{
    String contents=  "";

    @Override
    public String toString() {
        return "{" + contents +  '}';
    }
}
class Chunk extends Piece{
    List<Piece> things = new ArrayList<Piece>();

    @Override
    public String toString() {
        String returnString = "{";
        for (Piece p: things){
            returnString+=p.toString()+",";
        }
        return returnString+',';
    }
}
class Game{
    Map<String, Chunk> parameters = new HashMap<String, Chunk>();
}



public class MercuryParser {

    public int countStartingTabs(String line){
        if (line.charAt(0) == '\t'){
            return 1 + countStartingTabs(line.substring(1));
        }
        else{
            return 0;
        }
    }
    public void go() throws IOException {
        Path path = Paths.get("C:\\Users\\a689638\\IdeaProjects\\GEngine\\src\\main\\resources\\war.txt");
        BufferedReader bf = new BufferedReader(new FileReader(path.toFile()));
        String masterlist = "";
        String temp = "";

        while (null != (temp = bf.readLine())){
            masterlist+=temp;
        }

        List<Chunk> chunks = new ArrayList<Chunk>();
        for (String thing: masterlist.split("::")){
            System.out.println(thing);
            String[] lines =  thing.split("\n");
            int i = 0;
            int currTab = -1;
            Chunk c = new Chunk();
            while (i<lines.length){
                if (lines[i].length()<2) continue;
                if (currTab==countStartingTabs(lines[i])) {

                }

            }
        }


    }
}
