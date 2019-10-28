package com.tema1.players;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.helpers.HashtableElement;
import com.tema1.helpers.HashtableElementComparator;
import com.tema1.helpers.Stash;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class GreedyPlayer extends Player {
    public GreedyPlayer(int id) {
        super(id);
    }

    public Stash prepareStash(int roundNumber) {
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
        int declaration = 0; boolean addedIllegal = false;
        if (cardsParsed.size() == 0) {cardsForStash.add(GoodsFactory.getInstance().getGoodsById(bestIllegalID)); addedIllegal = true;}
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
        // daca in rundele pare are si o carte ilegala, o baga
        if (!addedIllegal && cardsForStash.size() < 8 && roundNumber % 2 == 0 && bestIllegalID != -1) cardsForStash.add(GoodsFactory.getInstance().getGoodsById(bestIllegalID));
        return new Stash(0, cardsForStash, declaration);
    }

    public boolean isBriber() {return false;}
}
