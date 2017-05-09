package com.company;

import java.util.ArrayList;

/**
 * Created by lushi on 01.05.2017.
 */
public class Ant implements Core {
    int x, y;
    ArrayList<String> dnk;
    int food;
    private int way;
    final int MaxWay = 10;
    public Ant(ArrayList<String> dnk) {
        x = 0;
        y = 0;
        food = 0;
        setWay(MaxWay);            //3x3 => 3+3=6
        this.dnk = dnk;
    }

    public void setDnk(ArrayList<String> d) {
        dnk = d;
    }

    @Override
    public boolean equals(Ant a) {
        for(int i =0; i < dnk.size(); i++){
            if(!dnk.get(i).equals(a.dnk.get(i)))
                return false;
        }
        return true;
    }

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }
}
