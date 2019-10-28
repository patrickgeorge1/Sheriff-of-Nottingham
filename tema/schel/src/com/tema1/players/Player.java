package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected int id;
    protected int money = 80;
    protected ArrayList<Goods> cards = new ArrayList<Goods>();

    public int getMoney() {
        return money;
    }

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

    public Player(int id) {
        this.id = id;
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
}
