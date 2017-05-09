package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Anthill anthill = new Anthill();
        while (!anthill.Run());
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

   /*     Map map = new Map("map11.txt");
        map.outMap();*/
    }
}
