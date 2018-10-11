package Lab2Valinskis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import studijosKTU.*;
/*
 */
public class Greitaveika {
    ListKTU<GPU> aSeries = new ListKTU<>();
    Random ag = new Random();  // atsitiktinių generatorius
    //int[] tiriamiKiekiai = {2_000_00, 4_000_00, 8_000_00, 16_000_00};
    static int[] tiriamiKiekiai = {8_000, 16_000, 32_000, 64_000};
    
    void paprastasTyrimas(int elementųKiekis){
        // Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        ListKTU<GPU> aSeries2 = aSeries.clone();
        ListKTU<GPU> aSeries3 = aSeries.clone();
        ListKTU<GPU> aSeries4 = aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        aSeries.sortSystem();
        long t3=System.nanoTime();
        aSeries2.sortSystem(GPU.pagalKainą);
        long t4=System.nanoTime();
        aSeries3.sortBuble();
        long t5=System.nanoTime();
        aSeries4.sortBuble(GPU.pagalKainą);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }
    
 
    void paprastasTyrimasMathPow(int elementųKiekis){
        // Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        int[] arr = generuotiNumerius(elementųKiekis);
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        for(int a : arr)
           {
               Math.pow(a, 1.0/3);
           }
        long t3=System.nanoTime();
        for(int a : arr)
           {
               Math.cbrt(a);
           }
        long t4=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f\n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9);
    }
    
    void paprastasTyrimasIdx(int elementųKiekis){
        // Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        ArrayList<Integer> arrLst = generuotiNumeriusArrLst(elementųKiekis);
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        for(int i = 0 ; i < elementųKiekis; i++)
           {
               int a = arrLst.indexOf(i);
           }
        long t3=System.nanoTime();
        for(int i = 0 ; i < elementųKiekis; i++)
           {
               int a = arrLst.lastIndexOf(i);
           }
        long t4=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f\n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9);
    }
    
    
        void sisteminisTyrimasIdx(){
        // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);

        for (int kiekis : tiriamiKiekiai) {
            ArrayList<Integer> arrLst = generuotiNumeriusArrLst(kiekis);
            tk.start();
            for(int i = 0 ; i < kiekis; i++)
            {
               int a = arrLst.indexOf(i);
            }
            tk.finish("IdxOf");
            for(int i = 0 ; i < kiekis; i++)
            {
               int a = arrLst.lastIndexOf(i);
            }
            tk.finish("lasdIdx");
            tk.seriesFinish();
        }
    }
        
    void tyrimoPasirinkimasIdx(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("Tiriamsas metodas indexOf(Object o)<->lastIndexOf(Object o)");
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        Ks.oun("0 - Kiekis");
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Operacija ArrayList.indexOf(i))");
        Ks.oun("4 - Operacija ArrayList.lastIndexOf(i);");

        Ks.ouf("%6d %7d %7d %7d %7d \n", 0,1,2,3,4);
        for(int n: tiriamiKiekiai) 
            paprastasTyrimasIdx(n);

          sisteminisTyrimasIdx();
    }
        
    ArrayList<Integer> generuotiNumeriusArrLst(int kiekis){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(Integer i=0;i<kiekis;i++)
        {
            arr.add(i);
        }
        return arr;
    }
    
    int[] generuotiNumerius(int kiekis){
        int[] arr = new int[kiekis];
        for(int i=0;i<kiekis;i++)
        {
            arr[i] = i;
        }
        return arr;
    }
    
    
     void sisteminisTyrimasMathPow(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);

        for (int kiekis : tiriamiKiekiai) {
           int[] arr = generuotiNumerius(kiekis);
           tk.start();
           for(int a : arr)
           {
               Math.pow(a, 1.0/3);
           }
           tk.finish("Math.pow(x, 1.0/3)");
           for(int a : arr)
           {
               Math.cbrt(a);
           }
           tk.finish("Math.cbrt(x)");
           tk.seriesFinish();
        }
    }
    
    
    void tyrimoPasirinkimasMathPow(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("Tiriamsas Math.pow(x, 1.0/3)<->Math.cbrt(x)");
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        Ks.oun("0 - Kiekis");
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Operacija Math.pow(x, 1.0/3)");
        Ks.oun("4 - Operacija Math.cbrt(a);");

        Ks.ouf("%6d %7d %7d %7d %7d\n", 0,1,2,3, 4);
        for(int n: tiriamiKiekiai) 
            paprastasTyrimasMathPow(n);

          sisteminisTyrimasMathPow();
    }
     
    void tyrimoPasirinkimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        for(GPU a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n: tiriamiKiekiai) 
            paprastasTyrimas(n);
//        // sekančio tyrimo metu gaunama normalizuoti įvertinimai
//        sisteminisTyrimas();
          sisteminisTyrimasMathPow();
    }
    
   public static void main(String[] args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new Greitaveika().tyrimoPasirinkimasIdx();
        for(int i = 0; i < tiriamiKiekiai.length; i++)
        {
            tiriamiKiekiai[i] = tiriamiKiekiai[i] * 100;
        }
        new Greitaveika().tyrimoPasirinkimasMathPow();
   } 
}   