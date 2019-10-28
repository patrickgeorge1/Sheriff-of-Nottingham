package com.tema1.helpers;

import com.tema1.goods.Goods;

import java.util.ArrayList;

public class Stash {
    private int bribe;
    private ArrayList<Goods> cards;
    private int declaration;

    public int getBribe() {
        return bribe;
    }

    public void setBribe(int bribe) {
        this.bribe = bribe;
    }

    public ArrayList<Integer> getCards() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Goods good:this.cards) {result.add(good.getId());}
        return result;
    }

    public void setCards(ArrayList<Goods> cards) {
        this.cards = cards;
    }

    public int getDeclaration() {
        return declaration;
    }

    public void setDeclaration(int declaration) {
        this.declaration = declaration;
    }

    public Stash(int bribe, ArrayList<Goods> cards, int declaration) {
        this.bribe = bribe;
        this.declaration = declaration;
        this.cards = new ArrayList<Goods>(cards);
    }

    // check or not the stash and compute profit/ penalty for customer and sheriff
    public StashResult checkOrCompute(boolean cheat) {
        int profit = 0;  // pt c
        int penalty = 0; // pt c
        int sPenalty = 0;
        int sProfit = 0;
        ArrayList<Goods> confiscated = new ArrayList<Goods>();
        ArrayList<Goods> allowed = new ArrayList<Goods>();
        if (cheat) {
            for (Goods good:this.cards) {
                profit += good.getProfit();
                allowed.add(good);
            }
            return new StashResult(profit, bribe, bribe, 0, allowed, confiscated);
        }

        for (Goods good:this.cards) {
            // a declarat cartea corect
            if (good.getId() == declaration) {
                profit += good.getProfit();
                allowed.add(good);
                sPenalty += good.getPenalty();
            }
            else {
                sProfit += good.getPenalty();
                penalty += good.getPenalty();
                confiscated.add(good);
            }
        }
        // a fost sincer
        if (penalty == 0) return new StashResult(profit, 0, 0, sPenalty, allowed, confiscated);
        else return new StashResult(profit, penalty, sProfit, 0, allowed, confiscated);
    }
}