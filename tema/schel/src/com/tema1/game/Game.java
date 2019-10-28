package com.tema1.game;

import com.tema1.goods.Goods;
import com.tema1.helpers.StashResult;
import com.tema1.players.BasePlayer;
import com.tema1.players.BriberPlayer;
import com.tema1.players.GreedyPlayer;
import com.tema1.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private int numberOfRounds;
    private int numberOfPlayers;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Integer> deck;

    public Game(int rounds, int players, List<String> playersType, List<Integer> cardsType) {
        this.numberOfRounds = rounds;
        this.numberOfPlayers = players;
        int i = 0;
        for (String playerType:playersType) {
            if (playerType.equals("basic")) this.players.add(new BasePlayer(i));
            if (playerType.equals("greedy")) this.players.add(new GreedyPlayer(i));
            if (playerType.equals("bribe")) this.players.add(new BriberPlayer(i));
            i++;
        }
        this.deck = new ArrayList<Integer>(cardsType);
    }

    public void playSubroundWithSheriiff(int x, int roundNumber) {
        System.out.println("Sheriff " + x);
        for (int i = 0; i < numberOfPlayers; i++) {
            if (i != x) {
                Player p = this.players.get(i);
                StashResult stashResult = new StashResult(0, 0, 0, 0, new ArrayList<Goods>(), new ArrayList<Goods>());
                if (p.getType() == 0) stashResult = ((BasePlayer) p).takeTurn(this.deck, roundNumber);
                if (p.getType() == 1) stashResult = ((GreedyPlayer) p).takeTurn(this.deck, roundNumber);
//                if (p.getType() == 2) stashResult = ((BriberPlayer) p).takeTurn(this.deck, roundNumber);

                for (Integer confiscated:stashResult.getConfiscated()) {
                    this.deck.add(confiscated);
                }
                p.setMoney(p.getMoney() + stashResult.cProfit - stashResult.cPenalty);
                this.players.get(x).setMoney(this.players.get(x).getMoney() + stashResult.sProfit - stashResult.sPenalty);
            }
        }
    }

    public void playRound(int roundNumber) {
        for (int player = 0; player < this.numberOfPlayers; player++) {
            this.playSubroundWithSheriiff(player, roundNumber);
        }
    }

    public void play() {
        for (int round = 1; round <= numberOfRounds; round++) {
            System.out.println(" ------------  Round " + round + "  ------------");
            playRound(round);
        }
    }

}
