package com.tema1.helpers;

import java.util.Comparator;

public class HashtableElementComparator implements Comparator<HashtableElement> {
    @Override
    public int compare(HashtableElement card1, HashtableElement card2) {
        int firstTry = card1.getFrequency() - card2.getFrequency();
        if (firstTry != 0) return firstTry;
        else {
            firstTry = card1.getProfit() - card2.getProfit();
            if (firstTry != 0) return firstTry;
        }
        return card1.getCardID() - card2.getCardID();
    }
}