package com.ryan.gengine.Version1.impl;

import com.ryan.gengine.Version1.objects.FaceDownStack;
import com.ryan.gengine.Version1.service.GEvent;
import com.ryan.gengine.Version1.service.Game;
import com.ryan.gengine.Version1.util.GameParams;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by a689638 on 8/12/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
class EmptyHandException extends Exception{
    EmptyHandException(String messsage){
        super(messsage);
    }
}


class WarUpdate extends GEvent {
    public static WarUpdate printUpdate(String updateText) {
        Map<String, Object> data = new HashMap<>();
        data.put("text", updateText);
        return new WarUpdate("Update", data);
    }

    public static WarUpdate ownerUpdate(int number , int owner, StandardCard cards[]){
        Map<String, Object> data = new HashMap<>();
        List<Point> points = new ArrayList<>();
        for (StandardCard card: cards){
            points.add(new Point (card.getSuit().getIndex(),card.getValueInt() ));
        }
        data.put("points", points);
        data.put("owner", owner);
        data.put("number", number );
        return new WarUpdate("owns", data);
    }

    public static WarUpdate compareUpdate(StandardCard card1, StandardCard card2){
        Map<String, Object> data = new HashMap<>();
        data.put("location1", new Point(card1.getSuit().getIndex(), card1.getValueInt()));
        data.put("location2", new Point(card2.getSuit().getIndex(),card2.getValueInt()));
        return new WarUpdate("compare", data);

    }
    public static WarUpdate countUpdate(int count){
        Map<String, Object> data = new HashMap<>();
        data.put("count", count);
        return new WarUpdate("count", data);
    }

    public static WarUpdate collateralUpdate(List<StandardCard> collaterals){
        Map<String, Object> data = new HashMap<>();
        List<Point> points = new ArrayList<>();
        points.addAll(collaterals.stream().map(
                card -> new Point(card.getSuit().getIndex(), card.getValueInt())).collect(Collectors.toList()));
        data.put("points", points);
        return new WarUpdate("collateral", data);
    }



    private WarUpdate(String update, Map<String, Object> data) {
        super(update, data);

    }

    @Override
    public String toString() {
        return (String) super.getData("text");
    }
}

public class WarGame extends Game {
    //player1
    FaceDownStack<StandardCard> player1 = new FaceDownStack<>();

    //player2
    FaceDownStack<StandardCard> player2 = new FaceDownStack<>();

    //game space
    StandardDeck deck = new StandardDeck();
    Scanner sc = new Scanner(System.in);

     private static GameParams gp(){
         GameParams gp = new GameParams();
         return gp;
     }



    public WarGame() {
        super(gp());
        deck.shuffleDeck();
    }

    static void update(String... update) {
        StringBuilder date = new StringBuilder();
        for (String up : update) {
            date.append(up);
        }
        //sendEvent(WarUpdate.printUpdate(date.toString()));
        //System.out.println(date);
    }

    @Override
    public void start() {

        update("Game Starting");
        boolean alternate = true;
        String ud = "";
        while (!deck.isEmpty()) {
            StandardCard card = deck.drawCard();
            ud+=card.toString();
            if (alternate) {
                player1.putOnTop(card);
                sendEvent(WarUpdate.ownerUpdate(1, 1, new StandardCard[]{card}));
                ud+="to player1";
            } else {
                player2.putOnTop(card);
                sendEvent(WarUpdate.ownerUpdate(1, 2, new StandardCard[]{card}));
                ud+="to player2";

            }
            //System.out.println(ud);
            ud = "";
            alternate = !alternate;
        }
        update("Cards Dealt");
    }

    @Override
    public void each() {
        update("Round starting");
        //getInput();
        try {
            FaceDownStack<StandardCard> winner = warBattle(new FaceDownStack<StandardCard>());
        } catch (EmptyHandException e) {
            System.out.println("BOOM");
        }


        if (player2.isEmpty()){
            update("player1 wins game");
            this.endGame();
        }
        else if (player1.isEmpty()){
            update("player2 wins game");
            this.endGame();
        }
        update("Round finishes");

    }

    private FaceDownStack<StandardCard> warBattle(FaceDownStack<StandardCard> winnersReward) throws EmptyHandException {
        update("Battle!");
        sendEvent(WarUpdate.countUpdate(player1.count()));
        StandardCard cardFromPlayer1 = player1.pop();
        StandardCard cardFromPlayer2 = player2.pop();
        if (cardFromPlayer1 == null || cardFromPlayer2 == null){
            throw new EmptyHandException("Game over, man");
        }
        sendEvent(WarUpdate.compareUpdate(cardFromPlayer1, cardFromPlayer2));
        update("Comparing: Player 1 ", cardFromPlayer1.toString(), ", Player 2 ", cardFromPlayer2.toString());
        int compareResults = StandardCard.compareValue(cardFromPlayer1, cardFromPlayer2);
        //getInput();
        winnersReward.putOnTop(cardFromPlayer1);
        winnersReward.putOnTop(cardFromPlayer2);
        if (compareResults == 0) {//war
            update("\tTie/War");
            List<StandardCard> collateral = player1.pop((player1.count() >= 3?2:player1.count()-1));
            collateral.addAll(player2.pop((player2.count() >= 3 ? 2 : player2.count() - 1)));

            sendEvent(WarUpdate.collateralUpdate(collateral));

            winnersReward.putOnTop(collateral);
            //getInput();
            return warBattle(winnersReward).absorb(winnersReward);


        } else if (compareResults > 1) {
            update("\tPlayer 1 wins");
            for (StandardCard c: winnersReward.cards){
                sendEvent(WarUpdate.ownerUpdate(1, 1, new StandardCard[]{c}));

            }
            return player1.absorb(winnersReward);

        } else {
            update("\tPlayer 2 wins");
            for (StandardCard c: winnersReward.cards){
                sendEvent(WarUpdate.ownerUpdate(1, 2, new StandardCard[]{c}));
            }
            return player2.absorb(winnersReward);
        }
    }

    @Override
    public void end() {
        update("Game Ends");
    }


}
