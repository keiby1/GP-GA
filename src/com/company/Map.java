package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lushi on 07.05.2017.
 */
public class Map {
    ArrayList<ArrayList<Character>> map;
    int n;
    public Map(String file){
        map = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String t;
            int i = 0;
            int j = 0;
            n =  Integer.parseInt(r.readLine());
            while (i != n) {
                ArrayList<Character> arrayList = new ArrayList<>();
                while (j != n){
                    t = r.readLine();
                    arrayList.add(t.charAt(0));
                    j++;
                }
                map.add(arrayList);
                j = 0;
                i++;
            }
            r.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void outMap() {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                System.out.print(map.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
