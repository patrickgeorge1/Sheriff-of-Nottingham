package com.tema1.main;

import com.tema1.game.Game;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.IllegalGoods;
import com.tema1.helpers.Stash;
import com.tema1.helpers.StashResult;
import com.tema1.players.BasePlayer;
import com.tema1.players.GreedyPlayer;
import com.tema1.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        //TODO implement homework logic
        Game game = new Game(gameInput.getRounds(), gameInput.getPlayerNames().size(), gameInput.getPlayerNames(), gameInput.getAssetIds());
        game.play();
    }
}
