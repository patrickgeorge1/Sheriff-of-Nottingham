package com.tema1.game;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.helpers.StashResult;
import com.tema1.players.*;

import java.util.*;

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
//        System.out.println("Sheriff " + x);
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
        // joaca rundele
        for (int round = 1; round <= numberOfRounds; round++) {
//            System.out.println(" ------------  Round " + round + "  ------------");
            playRound(round);
        }



        // sterg bunuri ilegale, si fac profit carti, fara bonus king si queen
        for (int i = 0; i < numberOfPlayers; i++) {
            Player p = this.players.get(i);
            p.computeProfit();
        }

        // adaug bonusiri pe king si queen
        Map<Integer, ArrayList<Integer>> kingAndQuuen = GoodsFactory.getKingAndQueen(numberOfPlayers);
        for (Integer card:kingAndQuuen.keySet()) {
            ArrayList<Integer> kandq = kingAndQuuen.get(card);
            if (kandq.size() > 0) {
                // dau bonus la king
                int king = kandq.get(0);
                players.get(king).updateProfitByBonus(GoodsFactory.getBonusFor(card, "king"));
                if (kandq.size() == 2) {
                    // dau bonus la quuen
                    int queen = kandq.get(1);
                    players.get(queen).updateProfitByBonus(GoodsFactory.getBonusFor(card, "queen"));
                }
            }
        }

        // fac clasament
        PlayerComparator playerComparator = new PlayerComparator();
        Collections.sort(players, playerComparator);
        for (Player player:players) {
            System.out.println(player.getId() + " " + Player.typeConversion.get(player.getType()) + " " + player.getProfitByBonus());
        }

//        System.out.println(GoodsFactory.cardStats);
    }

}
