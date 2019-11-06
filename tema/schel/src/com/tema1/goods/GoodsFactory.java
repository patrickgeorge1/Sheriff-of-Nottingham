package com.tema1.goods;

import com.tema1.helpers.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class GoodsFactory {
    private static GoodsFactory instance = null;
    private static final int ILLEGAL_PENALTY = 4;
    private static final int LEGAL_PENALTY = 2;
    // Constante pentru conversie bunuri ilegale
    static Map<Integer, ArrayList<ArrayList<Integer>>> illegalConversion = new HashMap<>() {{
        put(20, new ArrayList<ArrayList<Integer>>(){{add(new ArrayList<>() {{add(3); add(1);}});}});
        put(21, new ArrayList<ArrayList<Integer>>(){{add(new ArrayList<>() {{add(2); add(3);}});}});
        put(22, new ArrayList<ArrayList<Integer>>(){{add(new ArrayList<>() {{add(2); add(2);}});}});
        put(23, new ArrayList<ArrayList<Integer>>(){{add(new ArrayList<>() {{add(4); add(7);}});}});
        put(24, new ArrayList<ArrayList<Integer>>(){{add(new ArrayList<>() {{add(2); add(4);}}); add(new ArrayList<>() {{add(3); add(6);}}); add(new ArrayList<>(){{add(1); add(3);}});}});
    }};

    static Map<Integer, ArrayList<Integer>> kingAndQuuenBonus = new HashMap<>() {{
        put(0, new ArrayList<Integer>() {{add(20); add(10);}});
        put(1, new ArrayList<Integer>() {{add(19); add(9);}});
        put(2, new ArrayList<Integer>() {{add(18); add(9);}});
        put(3, new ArrayList<Integer>() {{add(17); add(8);}});
        put(4, new ArrayList<Integer>() {{add(16); add(7);}});
        put(5, new ArrayList<Integer>() {{add(15); add(6);}});
        put(6, new ArrayList<Integer>() {{add(14); add(5);}});
        put(7, new ArrayList<Integer>() {{add(13); add(4);}});
        put(8, new ArrayList<Integer>() {{add(12); add(3);}});
        put(9, new ArrayList<Integer>() {{add(11); add(2);}});
    }};

    public static int getBonusFor(int card, String role) {
        ArrayList<Integer> bonus = kingAndQuuenBonus.get(card);
        if (role.equals("king")) {return bonus.get(0);}
        return bonus.get(1);
    }

    // Dau id carte ilgala si primesc cartile echivalente
    public Tuple computeIllegal(int id) {
        ArrayList<ArrayList<Integer>> exchange = illegalConversion.get(id);
        int profit = GoodsFactory.getInstance().getGoodsById(id).getProfit();
        return new Tuple(profit, exchange);
    }

    public static Map<Integer, ArrayList<Integer>> cardStats = new HashMap<>() {{
        put(0, new ArrayList<Integer>());
        put(1, new ArrayList<Integer>());
        put(2, new ArrayList<Integer>());
        put(3, new ArrayList<Integer>());
        put(4, new ArrayList<Integer>());
        put(5, new ArrayList<Integer>());
        put(6, new ArrayList<Integer>());
        put(7, new ArrayList<Integer>());
        put(8, new ArrayList<Integer>());
        put(9, new ArrayList<Integer>());
    }};

    public static Map<Integer, ArrayList<Integer>> getKingAndQueen(int numberOfPlayers) {
        Map<Integer, ArrayList<Integer>> cardWithKingAndQueen = new HashMap<>();
        for (int cardAsKey:cardStats.keySet()) {
            ArrayList<Integer> playersForCard = cardStats.get(cardAsKey);
            int king = -1;
            int queen = -1;
            Map <Integer, Integer> corelation = new HashMap<>();
            for (int player:playersForCard) {
                if (!corelation.containsKey(player)) {
                    corelation.put(player, 0);
                }
                corelation.put(player, corelation.get(player) + 1);
            }
            // DACA AVEM PRETENDENTI PT QUUEN SI KING
            if (!corelation.isEmpty()) {
                int maxValueInMap=(Collections.max(corelation.values()));
                for (int i = 0; i < numberOfPlayers; i++) {
                    if(corelation.containsKey(i) && corelation.get(i) == maxValueInMap) {king = i; break;}
                }
                corelation.remove(king);
                if (!corelation.isEmpty()) {
                    maxValueInMap=(Collections.max(corelation.values()));
                    for (int i = 0; i < numberOfPlayers; i++) {
                        if(corelation.containsKey(i) && corelation.get(i) == maxValueInMap) {queen = i; break;}
                    }
                }
            }
            // acum am pe king si pe queen
            ArrayList<Integer> kandq = new ArrayList<>();
            if (king != -1) kandq.add(king);
            if (queen != -1) kandq.add(queen);
            cardWithKingAndQueen.put(cardAsKey, kandq);
        }

        return cardWithKingAndQueen;
    }

    private static class IllegalGoodsIds {
        public static final int SILK = 20;
        public static final int PEPPER = 21;
        public static final int BARREL = 22;
        public static final int BEER = 23;
        public static final int SEAFOOD = 24;
    }

    private static class LegalGoodsIds {
        public static final int APPLE = 0;
        public static final int CHEESE = 1;
        public static final int BREAD = 2;
        public static final int CHICKEN = 3;
        public static final int TOMATO = 4;
        public static final int CORN = 5;
        public static final int POTATO = 6;
        public static final int WINE = 7;
        public static final int SALT = 8;
        public static final int SUGAR = 9;
    }

    private static class AssetsProfit {
        public static final int APPLE_PROFIT = 2;
        public static final int CHEESE_PROFIT = 3;
        public static final int BREAD_PROFIT = 4;
        public static final int CHICKEN_PROFIT = 4;
        public static final int TOMATO_PROFIT = 3;
        public static final int CORN_PROFIT = 2;
        public static final int POTATO_PROFIT = 3;
        public static final int WINE_PROFIT = 5;
        public static final int SALT_PROFIT = 2;
        public static final int SUGAR_PROFIT = 3;

        public static final int SILK_PROFIT = 9;
        public static final int PEPPER_PROFIT = 8;
        public static final int BARREL_PROFIT = 7;
        public static final int BEER_PROFIT = 6;
        public static final int SEAFOOD_PROFIT = 12;
    }

    private static class KingBonus {
        public static final int APPLE = 20;
        public static final int CHEESE = 19;
        public static final int BREAD = 18;
        public static final int CHICKEN = 17;
        public static final int TOMATO = 16;
        public static final int CORN = 15;
        public static final int POTATO = 14;
        public static final int WINE = 13;
        public static final int SALT = 12;
        public static final int SUGAR = 11;
    }

    private static class QueenBonus {
        public static final int APPLE = 10;
        public static final int CHEESE = 9;
        public static final int BREAD = 9;
        public static final int CHICKEN = 8;
        public static final int TOMATO = 7;
        public static final int CORN = 6;
        public static final int POTATO = 5;
        public static final int WINE = 4;
        public static final int SALT = 3;
        public static final int SUGAR = 2;
    }

    private static class IllegalBonus {
        public static final int SILK_BONUS = 3;
        public static final int PEPPER_BONUS = 2;
        public static final int BARREL_BONUS = 2;
        public static final int BEER_BONUS = 4;
        public static final int SEAFOOD_BONUS_TOMATO = 2;
        public static final int SEAFOOD_BONUS_POTATO = 3;
        public static final int SEAFOOD_BONUS_CHICKEN = 1;
    }

    private final Map<Integer, Goods> goodsById;

    private GoodsFactory() {
        goodsById = new HashMap<Integer, Goods>();

        initLegalGoods();
        initIllegalGoods();
    }

    private void initLegalGoods() {
        // create the types of legal goods
        Goods good0 = new LegalGoods(LegalGoodsIds.APPLE,
            AssetsProfit.APPLE_PROFIT, LEGAL_PENALTY,
            KingBonus.APPLE, QueenBonus.APPLE);
        Goods good1 = new LegalGoods(LegalGoodsIds.CHEESE,
            AssetsProfit.CHEESE_PROFIT, LEGAL_PENALTY,
            KingBonus.CHEESE, QueenBonus.CHEESE);
        Goods good2 = new LegalGoods(LegalGoodsIds.BREAD,
            AssetsProfit.BREAD_PROFIT, LEGAL_PENALTY,
            KingBonus.BREAD, QueenBonus.BREAD);
        Goods good3 = new LegalGoods(LegalGoodsIds.CHICKEN,
            AssetsProfit.CHICKEN_PROFIT, LEGAL_PENALTY,
            KingBonus.CHICKEN, QueenBonus.CHICKEN);
        Goods good4 = new LegalGoods(LegalGoodsIds.TOMATO,
            AssetsProfit.TOMATO_PROFIT, LEGAL_PENALTY,
            KingBonus.TOMATO, QueenBonus.TOMATO);
        Goods good5 = new LegalGoods(LegalGoodsIds.CORN,
            AssetsProfit.CORN_PROFIT, LEGAL_PENALTY,
            KingBonus.CORN, QueenBonus.CORN);
        Goods good6 = new LegalGoods(LegalGoodsIds.POTATO,
            AssetsProfit.POTATO_PROFIT, LEGAL_PENALTY,
            KingBonus.POTATO, QueenBonus.POTATO);
        Goods good7 = new LegalGoods(LegalGoodsIds.WINE,
            AssetsProfit.WINE_PROFIT, LEGAL_PENALTY,
            KingBonus.WINE, QueenBonus.WINE);
        Goods good8 = new LegalGoods(LegalGoodsIds.SALT,
            AssetsProfit.SALT_PROFIT, LEGAL_PENALTY,
            KingBonus.SALT, QueenBonus.SALT);
        Goods good9 = new LegalGoods(LegalGoodsIds.SUGAR,
            AssetsProfit.SUGAR_PROFIT, LEGAL_PENALTY,
            KingBonus.SUGAR, QueenBonus.SUGAR);

        // insert legal goods into a hashMap
        goodsById.put(LegalGoodsIds.APPLE, good0);
        goodsById.put(LegalGoodsIds.CHEESE, good1);
        goodsById.put(LegalGoodsIds.BREAD, good2);
        goodsById.put(LegalGoodsIds.CHICKEN, good3);
        goodsById.put(LegalGoodsIds.TOMATO, good4);
        goodsById.put(LegalGoodsIds.CORN, good5);
        goodsById.put(LegalGoodsIds.POTATO, good6);
        goodsById.put(LegalGoodsIds.WINE, good7);
        goodsById.put(LegalGoodsIds.SALT, good8);
        goodsById.put(LegalGoodsIds.SUGAR, good9);
    }

    private void initIllegalGoods() {
        // create LegalGoods - quantity hashMaps
        Map<Goods, Integer> bonus0 = new HashMap<Goods, Integer>();
        Map<Goods, Integer> bonus1 = new HashMap<Goods, Integer>();
        Map<Goods, Integer> bonus2 = new HashMap<Goods, Integer>();
        Map<Goods, Integer> bonus3 = new HashMap<Goods, Integer>();
        Map<Goods, Integer> bonus4 = new HashMap<Goods, Integer>();

        bonus0.put(goodsById.get(LegalGoodsIds.CHEESE),
            IllegalBonus.SILK_BONUS);
        bonus1.put(goodsById.get(LegalGoodsIds.CHICKEN),
            IllegalBonus.PEPPER_BONUS);
        bonus2.put(goodsById.get(LegalGoodsIds.BREAD),
            IllegalBonus.BARREL_BONUS);
        bonus3.put(goodsById.get(LegalGoodsIds.WINE),
            IllegalBonus.BEER_BONUS);
        bonus4.put(goodsById.get(LegalGoodsIds.CHICKEN),
            IllegalBonus.SEAFOOD_BONUS_CHICKEN);
        bonus4.put(goodsById.get(LegalGoodsIds.TOMATO),
            IllegalBonus.SEAFOOD_BONUS_TOMATO);
        bonus4.put(goodsById.get(LegalGoodsIds.POTATO),
            IllegalBonus.SEAFOOD_BONUS_POTATO);

        // create the types of illegal goods
        Goods good0 = new IllegalGoods(IllegalGoodsIds.SILK,
            AssetsProfit.SILK_PROFIT, ILLEGAL_PENALTY, bonus0);
        Goods good1 = new IllegalGoods(IllegalGoodsIds.PEPPER,
            AssetsProfit.PEPPER_PROFIT, ILLEGAL_PENALTY, bonus1);
        Goods good2 = new IllegalGoods(IllegalGoodsIds.BARREL,
            AssetsProfit.BARREL_PROFIT, ILLEGAL_PENALTY, bonus2);
        Goods good3 = new IllegalGoods(IllegalGoodsIds.BEER,
            AssetsProfit.BEER_PROFIT, ILLEGAL_PENALTY, bonus3);
        Goods good4 = new IllegalGoods(IllegalGoodsIds.SEAFOOD,
            AssetsProfit.SEAFOOD_PROFIT, ILLEGAL_PENALTY, bonus4);

        // insert legal goods into a hashMap
        goodsById.put(IllegalGoodsIds.SILK, good0);
        goodsById.put(IllegalGoodsIds.PEPPER, good1);
        goodsById.put(IllegalGoodsIds.BARREL, good2);
        goodsById.put(IllegalGoodsIds.BEER, good3);
        goodsById.put(IllegalGoodsIds.SEAFOOD, good4);
    }

    public static GoodsFactory getInstance() {
        if (instance == null) {
            instance = new GoodsFactory();
        }
        return instance;
    }

    public Goods getGoodsById(final int id) {
        return goodsById.get(id);
    }

    public Map<Integer, Goods> getAllGoods() {
        return goodsById;
    }
}
