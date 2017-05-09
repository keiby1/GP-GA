package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lushi on 01.05.2017.
 */
public class DNK {
    public static void out(ArrayList<String> s) {
        for (int i = 0; i < s.size(); i++)
            System.out.print(s.get(i) + " ");
        System.out.println();
    }

    public static void Crossing(Ant a, Ant b) {
        Random r = new Random();
        int index = r.nextInt(a.dnk.size()) + 1;
        ArrayList<String> newDnk1 = new ArrayList<>();
        ArrayList<String> newDnk2 = new ArrayList<>();

        for (int i = 0; i < index; i++)
            newDnk1.add(a.dnk.get(i));
        for (int i = 0; i < index; i++)
            newDnk2.add(b.dnk.get(i));

        for (int i = index; i < b.dnk.size(); i++)
            newDnk1.add(b.dnk.get(i));
        for (int i = index; i < a.dnk.size(); i++)
            newDnk2.add(a.dnk.get(i));
        a.setDnk(newDnk1);
        b.setDnk(newDnk2);
    }

    public static void Cross(Ant a, Ant b) {//берутся две особи, и сравнивется их генофонд.
        int index =0;
        ArrayList<String> newDnk1 = new ArrayList<>();
        ArrayList<String> newDnk2 = new ArrayList<>();

        for(int i =0; i < a.dnk.size() ; i++){
            if(a.dnk.get(i).equals(b.dnk.get(i))){
                index++;
            }
        }

        for (int i = 0; i <= index; i++)
            newDnk1.add(a.dnk.get(i));
        for (int i = 0; i <= index; i++)
            newDnk2.add(b.dnk.get(i));

        for(int i = b.dnk.size()-1 ; i > index; i-- ){
            newDnk1.add(b.dnk.get(i));
        }

        for(int i = a.dnk.size()-1 ; i > index; i-- ){
            newDnk2.add(a.dnk.get(i));
        }

        a.setDnk(newDnk1);
        b.setDnk(newDnk2);
    }

    public static void Mutation(Ant a) {
        Random r = new Random();
        String tmp = "";
        int index = r.nextInt(a.dnk.size());
        do {
            switch (r.nextInt(4) + 1) {
                case 1:
                    tmp = "x++";
                    break;
                case 2:
                    tmp = "y++";
                    break;
                case 3:
                    tmp = "x--";
                    break;
                case 4:
                    tmp = "y--";
                    break;
            }
        } while (tmp.equals(a.dnk.get(index)) && Chk(a, tmp, index));
        a.dnk.set(index, tmp);
    }

    public static void Mutation(Ant a, int index) {
        Random r = new Random();
        String tmp = "";
        do {
            switch (r.nextInt(4) + 1) {
                case 1:
                    tmp = "x++";
                    break;
                case 2:
                    tmp = "y++";
                    break;
                case 3:
                    tmp = "x--";
                    break;
                case 4:
                    tmp = "y--";
                    break;
            }
        } while (tmp.equals(a.dnk.get(index)) && Chk(a, tmp, index));
        a.dnk.set(index, tmp);
    }

    public static void Mutation2(Ant a) {
        for (int i = 0; i < a.dnk.size()-1; i++) {
            if (a.dnk.get(i).equals(GetAntiGene(a.dnk.get(i + 1)))) {
                Mutation(a, i + 1);
            }
        }
    }

    public static void Evolution(Ant iter) {
        Random r = new Random();
        String tmp = "";
        do {
            switch (r.nextInt(4) + 1) {
                case 1:
                    tmp = "x++";
                    break;
                case 2:
                    tmp = "y++";
                    break;
                case 3:
                    tmp = "x--";
                    break;
                case 4:
                    tmp = "y--";
                    break;
            }

        } while (Chk(iter, tmp, iter.dnk.size() - 1));
        iter.dnk.add(tmp);
    }

    public static boolean Chk(Ant ant, String gene, int index) {
        if (ant.dnk.get(index).equals(GetAntiGene(gene)))
            return false;
        else return true;
    }

    public static String GetAntiGene(String gene) {
        if (gene.equals("x++"))
            return "x--";
        else if (gene.equals("x--"))
            return "x++";
        else if (gene.equals("y++"))
            return "y--";
        else if (gene.equals("y--"))
            return "y++";
        else return "0";
    }
}
