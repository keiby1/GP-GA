package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by lushi on 01.05.2017.
 */
public class Anthill {
    int MaxPopulation = 20;
    ArrayList<Ant> AntList = new ArrayList<>();
  //  ArrayList<ArrayList<Character>> map;
    Map map;
    int n = 3;          //4x4

    public Anthill() {
        ArrayList<String> s1 = new ArrayList<String>();
        s1.add("x++");
        s1.add("y++");
        s1.add("x++");
        ArrayList<String> s2 = new ArrayList<>();
        s2.add("x--");
        s2.add("y--");
        s2.add("x--");
        Ant a1 = new Ant(s1);
        Ant a2 = new Ant(s2);

        AntList.add(a1);
        AntList.add(a2);
        map = new Map("map4.txt");
    }

    public void Cross() {
        int size = AntList.size();
        for (int i = 0; i < size; i += 2) {
            Ant copy1 = new Ant(AntList.get(i).dnk);
            Ant copy2 = new Ant(AntList.get(i + 1).dnk);
            DNK.Cross(AntList.get(i), AntList.get(i + 1));
            AntList.add(copy1);
            AntList.add(copy2);
        }
    }

    public void Sort() {
        Collections.sort(AntList, new Comparator<Ant>() {
            @Override
            public int compare(Ant o1, Ant o2) {
                return o1.getWay() - o2.getWay();
            }
        });
    }

    public void outAntList() {
        for (Ant iter : AntList) {
            System.out.println(iter.dnk + " " + iter.getWay());
        }
    }

    public void outAntListMax() {
        for (Ant iter : AntList) {
            System.out.println(iter.dnk + " " + iter.getWay() + " x:" + iter.x + " y:" + iter.y);
        }
    }

    public void CreateMap() {
      /*  ArrayList<Character> s1 = new ArrayList<>();
        s1.add('0');
        s1.add('0');
        s1.add('1');
        s1.add('0');
        ArrayList<Character> s2 = new ArrayList<>();
        s2.add('1');
        s2.add('2');
        s2.add('0');
        s2.add('1');
        ArrayList<Character> s3 = new ArrayList<>();
        s3.add('0');
        s3.add('0');
        s3.add('0');
        s3.add('1');
        ArrayList<Character> s4 = new ArrayList<>();
        s4.add('1');
        s4.add('1');
        s4.add('0');
        s4.add('0');
        map = new ArrayList<>();
        map.add(s1);
        map.add(s2);
        map.add(s3);
        map.add(s4);*/
        map = new Map("map4.txt");
    }

    public void outMap() {
        for (int i = 0; i < map.map.size(); i++) {
            for (int j = 0; j < map.map.get(i).size(); j++) {
                System.out.print(map.map.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public boolean Run() throws Exception {
        Restart();
        Evolution();
        Cross();
        Mutation();
        for (Ant aAntList : AntList) {
            Step(aAntList);
        }
        Sort();
        DelAnt();
           outAntListMax();
           System.out.println("____________________________");
          outMap();

        return Fitness();
    }

    private void Restart() {
        for (Ant iter : AntList) {
            iter.x = 0;
            iter.y = 0;
            iter.food=0;
            iter.setWay(0);
        }
    }

    public void Step(Ant a) throws Exception {
        CreateMap();
        for (int i = 0; i < a.dnk.size(); i++) {
            if (a.dnk.get(i).equals("x++")) {
                if (a.x + 1 > n)
                    break;
                else {
                    if (map.map.get(a.y).get(a.x + 1).equals('0')) {
                        a.x++;
                    }
                    else if(map.map.get(a.y).get(a.x+1).equals('2')){
                        a.food++;
                        map.map.get(a.y).set(a.x+1, '0');
                        a.x++;
                    }
                    else
                        break;
                }

            }
            //------------x--
            else if (a.dnk.get(i).equals("x--")) {
                if (a.x - 1 < 0)
                    break;
                else {
                    if (map.map.get(a.y).get(a.x - 1).equals('0')) {
                        a.x--;
                    }
                    else if(map.map.get(a.y).get(a.x-1).equals('2')){
                        a.food++;
                        map.map.get(a.y).set(a.x-1, '0');
                        a.x--;
                    }
                    else
                        break;

                }

            }
            //+++++++++++y++++
            else if (a.dnk.get(i).equals("y++")) {
                if (a.y + 1 > n)
                    break;
                else {
                    if (map.map.get(a.y + 1).get(a.x).equals('0')) {
                        a.y++;
                    }
                    else if(map.map.get(a.y+1).get(a.x).equals('2')){
                        a.food++;
                        map.map.get(a.y+1).set(a.x, '0');
                        a.y++;
                    }
                    else
                        break;

                }
            }
            //---------y-------
            else if (a.dnk.get(i).equals("y--")) {
                if (a.y - 1 < 0)
                    break;
                else {
                    if (map.map.get(a.y - 1).get(a.x).equals('0')) {
                        a.y--;
                    }
                    else if(map.map.get(a.y-1).get(a.x).equals('2')){
                        a.food++;
                        map.map.get(a.y-1).set(a.x, '0');
                        a.y--;
                    }
                    else
                        break;

                }
            }
        }
        a.setWay((n - a.x) + (n - a.y));
       // System.out.println("log " + a.dnk + " " + a.getWay() + " x:" + a.x + " y:" + a.y);
    }

    public void DelAnt() {
        if (AntList.size() > MaxPopulation) {
            while (AntList.size() != MaxPopulation) {
                AntList.remove(AntList.size() - 1);
            }
        }
    }

    public void Mutation() {

        for (int j = 0; j < AntList.size(); j++)
            for (int i = 0; i < AntList.size(); i++) {
                if (AntList.get(i).equals(AntList.get(j)) && i != j) {
                    ArrayList<String> tmp = new ArrayList<>(AntList.get(j).dnk);
                    DNK.Mutation2(AntList.get(j));
                    //    System.out.println("Mut " + tmp + " -> " + AntList.get(j).dnk);
                }
            }
    }

    public boolean Fitness() {
        for (Ant iter : AntList) {
            if (iter.getWay() == 0) {
                System.out.println("Win" + iter.dnk + " way:" + iter.getWay() + " x:" + iter.x + " y:" + iter.y + " food:" + iter.food);
                return true;
            }
        }
        return false;
    }

    void Evolution() {
        for (Ant iter : AntList)
            DNK.Evolution(iter);
    }
}
