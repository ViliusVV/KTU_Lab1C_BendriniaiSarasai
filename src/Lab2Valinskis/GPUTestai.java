/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Valinskis;
import java.util.List;
import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.ListKTUx;

/**
 *
 * @author Vilius
 */
public class GPUTestai {
    ListKTUx<GPU> bandomieji = new ListKTUx<>(new GPU());

    public static void main(String... args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT")); 
        new GPUTestai().metodoParinkimas();
        
    }
    
    
    public void metodoParinkimas()
    {
        bandymas5();
    }
        
    
    //paprastas gpuesoriaus klasės testavimas
    void bandymas1()
    {
        GPU p1 = new GPU("Nvidia", "GTX1050", 4, 1.2, 330.31);
        GPU p2 = new GPU("Nvidia", "GTX950", 4, 1.2, 350.31);
        GPU p3 = new GPU("Nvidia", "GTX970", 3, 1.2, 400.31);
        GPU p4 = new GPU("AMD", "RX290", 8, 1.8, 999.0);
        GPU p5 = new GPU();
        GPU p6 = new GPU("AMD", "RX280", 4, 1.6, 888.31);
        p5.parse("Nvidia TitanZ 12 1.5 2000");
        Ks.oun(p1);
        Ks.oun(p2);
        Ks.oun(p3);
        Ks.oun(p4);
        Ks.oun(p5);
    }
    
    
    //įvedimo, konstruktorių, sąrašo testavimas
    void bandymas2()
    {
        GPU p1 = new GPU("Nvidia", "GTX950", 6, 3700, 330.31);
        GPU p2 = new GPU("Nvidia", "GTX950", 6, 3700, 330.31);
        GPU p3 = new GPU("Nvidia", "GTX950", 6, 3699, 320.31);
        GPU p4 = new GPU("AMD", "RX390", 8, 2800, 2);
        GPU p5 = new GPU();
        GPU p6 = new GPU("AMD", "RX270", 6, 3600, 310.31);
        p5.parse("AMD RX250 0 1 666");
        bandomieji.add(p1);
        bandomieji.add(p2);
        bandomieji.add(p3);
        bandomieji.add(p4);
        bandomieji.add(p5);
        bandomieji.add(p6);
        for (int i = 0; i < 10; i++) {
            bandomieji.add(new GPU("AMD", "RX25"+ i, i+1, 2000+i*150, i*100));
        }
        bandomieji.add(new GPU("AMD", "Radeon 5850", 10, 3500, 555));
        bandomieji.add(new GPU("AMD", "Radeon 5850", 0, 3500, 9555500));
        bandomieji.add(new GPU("AMD", "Radeon 5850", 0, 3500, 900));
        bandomieji.add(new GPU("AMD", "Radeon 5850", 0, 3500, 555555555));
        bandomieji.println();
    }
    
    
    //įvairi gpuesorių atranka
    void bandymas3(){
        GPUApskaita gpua = new GPUApskaita();
        gpua.visiGPU.load("gpu.txt");
        gpua.visiGPU.println("Pradiniai");
        bandomieji = gpua.atrinktiGamintoją("AMD");
        bandomieji.println("AMD GPU");
        bandomieji = gpua.atrinktiPagalGbint(5);
        bandomieji.println("5 ar daugiau Gb turintys");
        bandomieji = gpua.atrinktiPagalKainą(100, 6000);
        bandomieji.println("kaina tarp 100 ir 1000");
        bandomieji = gpua.maksimaliosKainosGpu();
        bandomieji.println("maksimalios kainos GPU");
        bandomieji = gpua.atrinktiPagalModelį("Radeon_5850");
        bandomieji.println("Radeon 5850 GPU");
    }
    
    
    //GPU atranką pagal 2 savybes
    void bandymas4(){
        GPUApskaita gpua = new GPUApskaita();
        gpua.visiGPU.load("gpu.txt");
        gpua.visiGPU.println("Pradiniai");
        
        bandomieji = gpua.atrinktiPagalAtmintiGamintoja(1, "AMD");
        bandomieji.println("1 Gb turintys AMD GPU ");
    }
    
    
    //individualių metodų testavimas
    void bandymas5(){
        ListKTUx<GPU> testiniai = new ListKTUx<GPU>(new GPU());
        for (int i = 0; i < 10; i++) {
            bandomieji.add(new GPU("AMD", "RX35"+ i, i+1, i, i*100));
        }
        bandomieji.println("pradiniai");
        bandomieji.add(8, new GPU("AMD", "RX280"+ 5, 6, 2750, 500));
        bandomieji.println("Iskvietus add(8, obj)");
        bandomieji.remove(0);
        bandomieji.println("Iskvietus remove(0)");
        bandomieji.addLast(new GPU("AMD", "RX9000", 6, 2750, 500));
        bandomieji.println("Iskvietus addLast");
        bandomieji.set(1, new GPU("AMD", "RX280"+ 5, 6, 9999, 999));
        bandomieji.println("Iskvietus set(0)");
        ListKTU<GPU> sub = bandomieji.subList(1, 5);
        ListKTUx<GPU> subx = new ListKTUx<>(new GPU());
        for (int i = 0; i < 10; i++) {
            subx.add(sub.get(i));
        }
        subx.println("Iskvietus subList(1, 5)");
        subx.removeLastOccurance(subx.get(0));
        subx.println("Iskvietus removeLastOcurence(get(0)");
    }
    
    
    void bandymas6(){
        GPUApskaita gpua = new GPUApskaita();
        gpua.visiGPU.load("gpu.txt");
        gpua.visiGPU.println("Pradiniai");
        gpua.visiGPU.sortBuble(GPU.pagalDazniKaina);
        gpua.visiGPU.println("pagal atminti, kaina");
        gpua.visiGPU.sortSystem(GPU.pagalGamintojąModelį);
        gpua.visiGPU.println("pagal gamintoja, modeli");
    }
}
