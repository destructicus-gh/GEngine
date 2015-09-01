package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.service.singles.AbstractCard;
import com.ryan.gengine.Version1.service.singles.Holdable;
import com.ryan.gengine.Version1.service.singles.Holder;

/**
 * Created by a689638 on 7/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class StandardCard extends AbstractCard {
    private final StandardDeck.Suit suit;
    private final String value;
    private Holder holder;
    public static final String[] VALUES = new String[]{"2","3","4","5","6","7","8","9","10", "J","Q","K","A"};

    public StandardCard(String letter, StandardDeck.Suit suit){
        this.value = letter;
        this.suit = suit;
        this.setName(suit.name()+letter);
    }

    public StandardCard(int valueIndex, StandardDeck.Suit suit){
        this(VALUES[valueIndex], suit);
    }

    public static StandardCard fromString(String input) {
        String[] s = input.split("\\.");
        return new StandardCard(s[0], StandardDeck.Suit.forName(s[1]));
    }
    public String getPrettyString(int i) {
        return suit.name()+" "+value;
    }

    @Override
    public String toString() {
        return "StandardCard{" +
                "suit=" + suit +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StandardCard that = (StandardCard) o;
        //System.out.println(that+":"+this);
        return suit == that.suit && !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = (suit != null) ? suit.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public Holdable setHolder(Holder h) {
        this.holder = h;
        return this;
    }

    public StandardDeck.Suit getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public static int compareValue(StandardCard one, StandardCard two){
        return one.getValue().compareTo(two.getValue());
    }

    public int getValueInt(){

            switch (getValue()) {
                case "J":
                    return 10;
                case "Q":
                    return 11;
                case "K":
                    return 12;
                case "A":
                    return 0;
                default:
                    return Integer.parseInt(getValue());
            }
    }
}
