/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Valinskis;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;
/**
 *
 * @author Vilius
 */
public class GPU implements KTUable<GPU> {
    final static private int MIN_GB = 1;
    final static private double MIN_KAINA = 50;
    final static private double MAX_KAINA = 12000;
    
    private String gamintojas;
    private String modelis;
    private int atmintiesKiekisGb;
    private double branduolioDaznis;
    private double kaina;
    
    public GPU(){   
    }
    
    
    public GPU(String gamintojas, String modelis, int atmintiesKiekis, double bazinisDažnisMHz, double kaina)
    {
        this.gamintojas = gamintojas;
        this.modelis = modelis;
        this.atmintiesKiekisGb = atmintiesKiekis;
        this.branduolioDaznis = bazinisDažnisMHz;
        this.kaina = kaina;
    }
    public GPU(String dataString){
        this.parse(dataString);
    }
    
    
    @Override
    public GPU create(String dataString) {
        GPU a = new GPU();
        a.parse(dataString);
        return a;
    }
    
    
    @Override
    public final void parse(String dataString) {
        try {   
            // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            gamintojas = ed.next();
            modelis = ed.next();
            atmintiesKiekisGb = ed.nextInt();
            branduolioDaznis = ed.nextDouble();
            setKaina(ed.nextDouble());
        } catch (InputMismatchException  e) {
            Ks.ern("Blogas duomenų formatas apie GPU -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie GPU -> " + dataString);
        }
    }
    
    
    @Override
    public String validate() {
        String klaidosTipas = "";
        if (atmintiesKiekisGb < MIN_GB)
           klaidosTipas = "Netinkamas atminties, turi būti [" +
                   MIN_GB + "]";
        if (kaina < MIN_KAINA || kaina > MAX_KAINA )
            klaidosTipas += " Kaina už leistinų ribų [" + MIN_KAINA +
                            ":" + MAX_KAINA  + "]";
        return klaidosTipas;
    }
    
    
    @Override
    public String toString(){  // surenkama visa reikalinga informacija
        return String.format("%-6s %-15s %3d %6.1f %8.2f %s",
               gamintojas, modelis, atmintiesKiekisGb, branduolioDaznis, kaina, validate());
    };
    
    
    @Override
    public int compareTo(GPU a) { 
        // lyginame pagal svarbiausią požymį - kainą
        double kainaKita = a.getKaina();
        if (kaina < kainaKita) return -1;
        if (kaina > kainaKita) return +1;
        return 0;
    }
    
    public boolean similar(Object b)
    {
        GPU a = (GPU)b;
        if(        this.gamintojas == a.gamintojas 
                && this.modelis == a.modelis
                && this.atmintiesKiekisGb == a.atmintiesKiekisGb)
        {
            return true;
        }
        else
            return false;
    }
    
    
    // sarankiškai priderinkite prie Lambda funkcijų
    public final static Comparator<GPU> pagalGamintojąModelį =
              (GPU a1, GPU a2) -> {
                  // pradžioje pagal markes, o po to pagal modelius
                  int cmp = a1.getGamintojas().compareTo(a2.getGamintojas());
                  if(cmp != 0) return cmp;
                  return a1.getModelis().compareTo(a2.getModelis());
    };
    
    
    //palygina pagal kainą
    public final static Comparator pagalKainą = (Comparator) (Object o1, Object o2) -> {
        GPU a1 = (GPU) o1;
        GPU a2 = (GPU) o2;
        // metai mažėjančia tvarka, esant vienodiems lyginama kaina
        if(a1.getKaina() < a2.getKaina()) return 1;
        if(a1.getKaina() > a2.getKaina()) return -1;
        return 0;
    }; 
    
    
    // palygina GPU pagal bazini dazni ir tada pagal kainą
    public final static Comparator pagalDazniKaina = (Comparator) (Object o1, Object o2) -> {
        GPU a1 = (GPU) o1;
        GPU a2 = (GPU) o2;
        if(a1.getAtmintiesKiekisGb()< a2.getAtmintiesKiekisGb()) return 1;
        if(a1.getAtmintiesKiekisGb() > a2.getAtmintiesKiekisGb()) return -1;
        if(a1.getKaina() > a2.getKaina()) return 1;
        if(a1.getKaina() < a2.getKaina()) return -1;
        return 0;
    };
    
        
    public String getGamintojas() {
        return gamintojas;
    }

    public String getModelis() {
        return modelis;
    }

    public int getAtmintiesKiekisGb() {
        return atmintiesKiekisGb;
    }

    public double getBranduolioDaznis() {
        return branduolioDaznis;
    }

    public double getKaina() {
        return kaina;
    }
    public void setKaina(double kaina) {
        this.kaina = kaina;
    }
    
}
