/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Valinskis;

import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTUx;

/**
 *
 * @author Vilius
 */
//perziura ir atranka
public class GPUApskaita {
    public ListKTUx<GPU> visiGPU = new ListKTUx<>(new GPU());
    private static final GPU bazinisEgz = new GPU();

    
    // suformuojamas sąrašas GPU, kurie turi daugiau atminties nei riba
    public ListKTUx<GPU> atrinktiPagalGbint(int riba) {
        ListKTUx<GPU> naujiAuto = new ListKTUx<>(bazinisEgz);
        for (GPU a : visiGPU) {
            if (a.getAtmintiesKiekisGb()>= riba) {
                naujiAuto.add(a);
            }
        }
        return naujiAuto;
    }
    
    
    // suformuojamas sąrašas automobilių, kurių kaina yra tarp ribų
    public ListKTUx<GPU> atrinktiPagalKainą(int riba1, int riba2) {
        ListKTUx<GPU> vidutiniaiGpu = new ListKTUx(bazinisEgz);
        for (GPU a : visiGPU) {
            if (a.getKaina() >= riba1 && a.getKaina() <= riba2) {
                vidutiniaiGpu.add(a);
            }
        }
        return vidutiniaiGpu;
    }
    
    
    // suformuojamas sąrašas GPU, atitinkančių nurodytą modelį
    public ListKTUx<GPU> atrinktiPagalModelį(String modelis) {
        ListKTUx<GPU> vidutiniaiGpu = new ListKTUx(bazinisEgz);
        for (GPU a : visiGPU) {
            if (a.getModelis().contains(modelis)) {
                vidutiniaiGpu.add(a);
            }
        }
        return vidutiniaiGpu;
    }
    
    
    public ListKTUx<GPU> atrinktiPagalAtmintiGamintoja(int atmintis, String gamintojas)
    {
        ListKTUx<GPU> proc = new ListKTUx(bazinisEgz);
        for(GPU a : visiGPU)
        {
            if(a.getAtmintiesKiekisGb() == atmintis && a.getGamintojas().compareTo(gamintojas) == 0)
            {
                proc.add(a);
            }
        }
        return proc;
    }
    
    
    // suformuojamas sąrašas GPU, turinčių max kainą
    public ListKTUx<GPU> maksimaliosKainosGpu() {
        ListKTUx<GPU> brangiausiGpu = new ListKTUx(bazinisEgz);
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        double maxKaina = 0;
        for (GPU a : visiGPU) {
            double kaina = a.getKaina();
            if (kaina >= maxKaina) {
                if (kaina > maxKaina) {
                    brangiausiGpu.clear();
                    maxKaina = kaina;
                }
                brangiausiGpu.add(a);
            }
        }
        return brangiausiGpu;
    }
    
    
    // suformuojams sąrašas Gpu, kurių gamintojas atitinka nurodytą
    public ListKTUx<GPU> atrinktiGamintoją(String gamintojas) {
        ListKTUx<GPU> firminiaiGpu = new ListKTUx(bazinisEgz);
        for (GPU a : visiGPU) {
            if (a.getGamintojas().compareTo(gamintojas) == 0) {
                firminiaiGpu.add(a);
            }
        }
        return firminiaiGpu;
    }
    // metodo main nėra -> demo bandymai klasėje GPUTestai
}
