package com.ryan.gengine.Version1.objects;

import com.ryan.gengine.Version1.service.singles.AbstractCard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class FaceDownStack<T extends AbstractCard> {
    public Deque<T> cards = new ArrayDeque<>();
    public void putOnTop(T a){
        cards.addFirst(a);
    }
    public void putOnTop (List<T> a){
        for(T t:a){
            this.putOnTop(t);
        }
    }public void putOnBottom (List<T> a){
        for(T t:a){
            this.putOnBottom(t);
        }
    }

    public void putOnBottom(T a){
        cards.addLast(a);
    }
    public T pop(){
        return cards.pollFirst();
        /*
        T ret = cards.get(0);
        cards.remove(ret);
        cards.remove(0);
        if (cards.contains(ret)) throw new RuntimeException("You suck");
        return ret;
        */
    }
    public List<T> pop(int num){
        List<T> ret = new ArrayList<>();
        for (int i = 0;i<num;i++){
            if (!cards.isEmpty()){
                ret.add(this.pop());
            }
        }
        return ret;
    }


    public FaceDownStack<T> absorb(FaceDownStack<T> otherStack){
        putOnBottom(otherStack.pop(otherStack.count()));
        return this;
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }


    public int count(){
        return cards.size();
    }
}
