package com.tema1.helpers;

import com.tema1.goods.GoodsFactory;

import java.util.Comparator;

public class HashtableElement {
    private int cardID;
    private int frequency = 0;
    private int profit;

    public HashtableElement(int cardID) {
        this.cardID = cardID;
        this.profit = GoodsFactory.getInstance().getGoodsById(cardID).getProfit();
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void incrementFq() {
        this.frequency++;
    }
}