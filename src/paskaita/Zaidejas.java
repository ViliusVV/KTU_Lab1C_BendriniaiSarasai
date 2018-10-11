/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paskaita;

import studijosKTU.Ks;
import studijosKTU.ListKTU;

public class Zaidejas implements Comparable<Zaidejas> {

    private String pavardė;
    private int mesta;
    private int pataikyta;

    public Zaidejas(String pavardė, int mesta, int pataikyta) {
        this.pavardė = pavardė;
        this.mesta = mesta;
        this.pataikyta = pataikyta;
    }

    // ... geteriai ir seteriai 
    public double tikslumas() {
        return mesta == 0 ? 0.0 : 100.0 * pataikyta / mesta;
    }

    @Override
    public int compareTo(Zaidejas o) {
        return 1;
    }

    @Override
    public String toString() {
        return "pavardė=" + pavardė + ", mesta=" + mesta + ", pataikyta=" + pataikyta + "; ";
    }

    //=============================================
    static ListKTU<Zaidejas> komanda = new ListKTU<>();

    static void žaidėjųSąrašoTestas() {
        Zaidejas p1 = new Zaidejas("Seibutis", 9, 9);
        Zaidejas p2 = new Zaidejas("Mačiulis", 7, 8);
        Zaidejas p3 = new Zaidejas("Jankūnas", 6, 4);
        komanda.add(p1);
        komanda.add(p2);
        komanda.add(p3);
        Ks.oun("Komandos žaidėjų skaičius=" + komanda.size());
        Ks.oun("Žaidėjai:\n" + komanda.get(0) + komanda.get(1) + komanda.get(2));
        //-------------------
        Ks.oun("Žaidėjas(0)=" + komanda.get(0));
        Ks.oun("Žaidėjas(1)=" + komanda.get(1));
        Ks.oun("Žaidėjas(2)=" + komanda.get(2));
        komanda.add(new Zaidejas("Gailius", 0, 0));
        komanda.add(new Zaidejas("Javtokas", 3, 0));
        Ks.oun("Žaidėjas(3)=" + komanda.get(3));
        Ks.oun("Žaidėjas(4)=" + komanda.get(4));
        Ks.oun("Komandos žaidėjų skaičius=" + komanda.size());
        //------------------
        p1 = komanda.get(3);  // Gailius ...
        p2 = komanda.get(4);  // Javtokas ...
        p3 = komanda.get(5);  // planuojame gauti null
        Ks.oun("Rinkinys:\n" + p1 + "\n" + p2 + "\n" + p3);
        Ks.oun(komanda.isEmpty() ? "nieko nėra" : "šiek tiek yra");
        komanda.clear();
        Ks.oun(komanda.isEmpty() ? "nieko nėra" : "šiek tiek yra");
    }  // žaidėjųSąrašoTesto pabaiga

    static void printListKTU(ListKTU<Zaidejas> sąr) {
        for (Zaidejas z = sąr.get(0); z != null; z = sąr.getNext()) {
            Ks.oun("Žaidėjas-> " + z);
        }
    } // ---------------------------------------------------------------

    static void antiŠablonas(ListKTU<Zaidejas> sąr) {
        for (int i = 0; i < sąr.size(); i++) {
            Ks.oun("Žaidėjas-> " + sąr.get(i));
        }
    } // ---------------------------------------------------------------

    public static void main(String[] args) {
        žaidėjųSąrašoTestas();
        printListKTU(komanda);
        antiŠablonas(komanda);
    }
}
