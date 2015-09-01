package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.exception.EmptyDeckException;
import com.ryan.gengine.Version1.exception.WrongHoldableException;
import com.ryan.gengine.Version1.service.sets.AbstractDeck;
import com.ryan.gengine.Version1.service.singles.Holdable;
import com.ryan.gengine.Version1.util.PropertiesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by a689638 on 7/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class StandardDeck extends AbstractDeck {
    public enum Suit {
        Heart(0), Club(1), Diamond(2), Spade(3);
        private int index;

        Suit(int indexIn) {
            this.index = indexIn;
        }

        public int getIndex() {
            return index;
        }

        public static Suit forIndex(int i) {
            switch (i) {
                case 0:
                    return Suit.Heart;
                case 1:
                    return Suit.Club;
                case 2:
                    return Suit.Diamond;
                case 3:
                    return Suit.Spade;
            }
            return Suit.Spade;
        }
    public static Suit forName(String s) {
            switch (s.toLowerCase()) {
                case "heart":
                    return Suit.Heart;
                case "club":
                    return Suit.Club;
                case "diamond":
                    return Suit.Diamond;
                case "spade":
                    return Suit.Spade;
            }
            return Suit.Spade;
        }
    }

    private final List<StandardCard> heldCards;
    private final List<StandardCard> allCards;

    {
        allCards = new ArrayList<>();
        heldCards = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (String digit : PropertiesManager.getPropertyList("std_digits")) {
                StandardCard card = new StandardCard(digit, s);
                allCards.add(card);
                heldCards.add(card);
            }
        }
    }


    private void holdableIsCardCheck(Holdable h) {
        if (!(h instanceof StandardCard)) {
            throw new WrongHoldableException("Must supply StandardDeck with StandardCard");
        }
    }

    @Override
    public void add(Holdable h) {
        holdableIsCardCheck(h);
        heldCards.add((StandardCard) h);
    }



    @Override
    public boolean inHand(Holdable item) {
        return heldCards.contains(item);
    }

    @Override
    public Holdable drawFrom(Holdable h) {
        holdableIsCardCheck(h);
        StandardCard compareCard = (StandardCard) h;
        Iterator<StandardCard> it = heldCards.iterator();
        while (it.hasNext()) {
            StandardCard card = it.next();
            if (compareCard.equals(card)) {
                it.remove();
                return card;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return heldCards.isEmpty();
    }

    public StandardCard drawCard() {
        if (heldCards.isEmpty()) throw new EmptyDeckException("Standard Deck is empty");
        StandardCard cardOut = heldCards.get(0);
        heldCards.remove(0);
        return cardOut;
    }

    public void shuffleDeck() {
        Collections.shuffle(heldCards, new Random());
    }

    public ListIterator<StandardCard> getIterator() {
        return heldCards.listIterator();
    }
}
