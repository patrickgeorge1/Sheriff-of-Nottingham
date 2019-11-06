package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.helpers.Tuple;

import java.util.*;

public class Player {
    protected int id;
    protected int money = 80;
    protected int type;
    protected ArrayList<Goods> cards = new ArrayList<Goods>();
    protected ArrayList<Goods> shop = new ArrayList<Goods>();
    protected int profitWithouBonus;
    public static Map<Integer, String> typeConversion = new HashMap<>() {{
        put(0, "BASE");
        put(1, "GREEDY");
        put(2, "BRIBE");
    }};

    public int getMoney() {
        return money;
    }
    public int getType() { return type; }
    public ArrayList<Goods> getShop() {
        String res = "";
        for (Goods g:shop) {
            res += g.getId() + " ";
        }
        System.out.println("Shopul lui " + id + ":" + res);
        return shop; }

    public void setMoney(int money) {
        this.money = money;
    }

    public void updateProfitByBonus(int money) {
        this.profitWithouBonus = money;
    }
    public Integer getProfitByBonus() {
        return this.profitWithouBonus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getCards() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Goods good:this.cards) {result.add(good.getId());}
        return result;
    }

    public void setCards(ArrayList<Goods> cards) {
        this.cards = cards;
    }

    public Player(int id, int type) {
        this.id = id;
        this.type = type;
    }

    public void pick10cards(ArrayList<Integer> deck) {
        GoodsFactory goodsFactory = GoodsFactory.getInstance();
        for (int i = 1; i <= 10; i++) {
            this.cards.add(goodsFactory.getGoodsById(deck.get(0)));
            deck.remove(0);
        }
    }

    public void burnCards() {
        this.cards.clear();
    }

    public int getId() {return id;}

    public ArrayList<Integer> getBriberNeigbours(int numberOfPlayers) {
        int next = (this.getId() + 1) % numberOfPlayers;
        int previous = this.getId() - 1;
        if (previous < 0) previous = numberOfPlayers - 1;
        return new ArrayList<Integer>(Arrays.asList(previous, next));
    }

    public boolean isProfitLower16() {
        return this.money < 16;
    }

    public boolean isProfitZero() {
        return this.money == 0;
    }

    public boolean isProfitLowerX(int x) {
        return this.money < x;
    }


    public int computeProfit() {
        ArrayList<Goods> newShop = new ArrayList<Goods>();
        int profit = 0;
        for (Goods good:shop) {
            // carte ilgala
            if (good.getId() >= 20) {
                Tuple tuple = GoodsFactory.getInstance().computeIllegal(good.getId());
                //                Profit carte illegala
                profit += tuple.first;
                ArrayList<ArrayList<Integer>> pairOfCars = tuple.second;
                //                Convertesc cartea ilegala in carti legale
                for (ArrayList<Integer> pair:pairOfCars) {
                    int multiplier = pair.get(0);
                    int card = pair.get(1);

                    for (int i = 1; i<= multiplier; i++) {
                        newShop.add(GoodsFactory.getInstance().getGoodsById(card));
                        // Bag player in clasament
                        ArrayList<Integer> cardStat = GoodsFactory.cardStats.get(card);
                        cardStat.add(id);
                        GoodsFactory.cardStats.put(card, cardStat);

                        profit += GoodsFactory.getInstance().getGoodsById(card).getProfit();
                    }

                }
            }
            else {
                profit += good.getProfit();
                newShop.add(good);
                // Bag player in clasament
                ArrayList<Integer> cardStat = GoodsFactory.cardStats.get(good.getId());
                cardStat.add(id);
                GoodsFactory.cardStats.put(good.getId(), cardStat);
            }
        }
        profit += money;
        this.shop = newShop;
        profitWithouBonus = profit;
        return profit;
    }

}
