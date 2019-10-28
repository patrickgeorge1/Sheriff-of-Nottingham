package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.helpers.HashtableElement;
import com.tema1.helpers.HashtableElementComparator;
import com.tema1.helpers.Stash;
import com.tema1.helpers.StashResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;

public class BasePlayer extends Player {
    public BasePlayer(int id) {
        super(id, 0);
    }

    public Stash prepareStash() {
        Hashtable<Integer, HashtableElement> cardsParsed = new Hashtable<>();
        int bestIllegalID = -1;
        int bestIllegalProfit = -1;
        // sortez ce i legal si caut si ilegalitatea maxima
        for (Goods good:this.cards) {
            if (good.isLegal()) {
                if (!cardsParsed.containsKey(good.getId())) {
                    cardsParsed.put(good.getId(), new HashtableElement(good.getId()));
                }
                HashtableElement elem = cardsParsed.get(good.getId());
                elem.incrementFq();
                cardsParsed.put(good.getId(), elem);
            } else {
                if (good.getProfit() > bestIllegalProfit) {
                    bestIllegalID = good.getId();
                    bestIllegalProfit = good.getProfit();
                }
            }
        }

        ArrayList<Goods> cardsForStash = new ArrayList<Goods>();
        int declaration = 0;
        if (cardsParsed.size() == 0) {cardsForStash.add(GoodsFactory.getInstance().getGoodsById(bestIllegalID));}
        else {
            HashtableElement bestCard  = cardsParsed.get(cardsParsed.keySet().toArray()[0]);
            declaration = bestCard.getCardID();
            HashtableElementComparator comparator = new HashtableElementComparator();
            Set<Integer> keys = cardsParsed.keySet();
            for(int key: keys){
                HashtableElement currentCard = cardsParsed.get(key);
                if (comparator.compare(bestCard, currentCard) < 0) bestCard = currentCard;
            }
            for (int i = 1; i <= bestCard.getFrequency() && i <= 8; i++) {cardsForStash.add(GoodsFactory.getInstance().getGoodsById(bestCard.getCardID())); declaration = bestCard.getCardID();}
        }
        return new Stash(0, cardsForStash, declaration);
    }

    public StashResult takeTurn(ArrayList<Integer> deck, int roundNumber) {
        this.pick10cards(deck);
        Stash stash = this.prepareStash();
        StashResult stashResult = stash.checkOrCompute(false);
        for (Goods good:stashResult.allowed) {
            this.shop.add(good);
        }
        this.burnCards();
        return stashResult;
    }

    public boolean isBriber() {return false;}
}
