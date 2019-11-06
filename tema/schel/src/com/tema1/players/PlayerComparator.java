package com.tema1.players;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.profitWithouBonus - o2.profitWithouBonus;
    }
}
