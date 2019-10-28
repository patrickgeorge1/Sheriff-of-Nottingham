package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    protected int id;
    protected int money = 80;
    protected int type;
    protected ArrayList<Goods> cards = new ArrayList<Goods>();
    protected ArrayList<Goods> shop = new ArrayList<Goods>();

    public int getMoney() {
        return money;
    }
    public int getType() { return type; }

    public void setMoney(int money) {
        this.money = money;
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
}
