package com.tema1.helpers;

import com.tema1.goods.Goods;

import java.util.ArrayList;

public class StashResult {
    public int cProfit;
    public int cPenalty;
    public int sProfit;
    public int sPenalty;
    public ArrayList<Goods> allowed;
    public ArrayList<Goods> confiscated;

    public StashResult(int cProfit, int cPenalty, int sProfit, int sPenalty, ArrayList<Goods> allowed, ArrayList<Goods> confiscated) {
        this.cProfit = cProfit;
        this.cPenalty = cPenalty;
        this.sProfit = sProfit;
        this.sPenalty = sPenalty;
        this.allowed = allowed;
        this.confiscated = confiscated;
    }

    public int getcProfit() {
        return cProfit;
    }

    public void setcProfit(int cProfit) {
        this.cProfit = cProfit;
    }

    public int getcPenalty() {
        return cPenalty;
    }

    public void setcPenalty(int cPenalty) {
        this.cPenalty = cPenalty;
    }

    public int getsProfit() {
        return sProfit;
    }

    public void setsProfit(int sProfit) {
        this.sProfit = sProfit;
    }

    public int getsPenalty() {
        return sPenalty;
    }

    public void setsPenalty(int sPenalty) {
        this.sPenalty = sPenalty;
    }

    public ArrayList<Integer> getAllowed() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Goods good:this.allowed) {result.add(good.getId());}
        return result;
    }

    public void setAllowed(ArrayList<Goods> allowed) {
        this.allowed = allowed;
    }

    public ArrayList<Integer> getConfiscated() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Goods good:this.confiscated) {result.add(good.getId());}
        return result;
    }

    public void setConfiscated(ArrayList<Goods> confiscated) {
        this.confiscated = confiscated;
    }
}