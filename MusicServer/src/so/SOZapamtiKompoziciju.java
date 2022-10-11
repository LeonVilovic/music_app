/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import Domain.Izvodjac;
import Domain.Kompozicija;
import Domain.Partitura;
import Domain.ZvucniZapis;
import Domain.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Leon
 */
public class SOZapamtiKompoziciju extends OpstaSistemskaOperacija {

    List<OpstiDomenskiObjekat> zvucniZapisi;
    List<OpstiDomenskiObjekat> izvodjaci;
    List<OpstiDomenskiObjekat> partiture;

    public SOZapamtiKompoziciju(OpstiDomenskiObjekat odo, List<OpstiDomenskiObjekat> zvucniZapisi, List<OpstiDomenskiObjekat> izvodjaci, List<OpstiDomenskiObjekat> partiture) {
        super(odo);
        this.zvucniZapisi = zvucniZapisi;
        this.izvodjaci = izvodjaci;
        this.partiture = partiture;
    }

    public SOZapamtiKompoziciju(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {

        Kompozicija kompozicija = (Kompozicija) dbbr.insert(odo);

        for (int i = 0; i < partiture.size(); i++) {
            Partitura p = (Partitura) partiture.get(i);
            p.setKompozicijaID(kompozicija);
            dbbr.insert(p);
        }

        if (zvucniZapisi.size() == izvodjaci.size()) {

            for (int i = 0; i < zvucniZapisi.size(); i++) {
                Izvodjac izv = (Izvodjac) izvodjaci.get(i);

                if (!dbbr.search(izv, izv.getNaziv()).isEmpty()) 
                {
                    // U Bazi vec postoji izvodjac sa slicnim imenom
                    List<OpstiDomenskiObjekat> lista = dbbr.search(izv, izv.getNaziv());
                    boolean istiNaziv = false;
                    for (int j = 0; j < lista.size(); j++) {
                        Izvodjac izvBaza = (Izvodjac) lista.get(j);
                        if (izvBaza.getNaziv().equals(izv.getNaziv())) {
                            izv = (Izvodjac) lista.get(j);
                            istiNaziv = true;
                            // U Bazi postoji izvodjac sa istim imenom
                            //izv = (Izvodjac) lista.get(0);
                        }
                    }
                    if (istiNaziv) {
                      ZvucniZapis zapis = (ZvucniZapis) zvucniZapisi.get(i);
                    zapis.setIzvodjacID(izv);
                    zapis.setKompozicijaID(kompozicija);
                    dbbr.insert(zapis);  
                    }else{
                        // U Bazi ne postoji izvodjac sa istim imenom
                    izv = (Izvodjac) dbbr.insert(izvodjaci.get(i));
                    ZvucniZapis zapis = (ZvucniZapis) zvucniZapisi.get(i);
                    zapis.setIzvodjacID(izv);
                    zapis.setKompozicijaID(kompozicija);
                    dbbr.insert(zapis);
                    }
                    
                }
                 else {
                    // U Bazi ne postoji izvodjac
                    izv = (Izvodjac) dbbr.insert(izvodjaci.get(i));
                    ZvucniZapis zapis = (ZvucniZapis) zvucniZapisi.get(i);
                    zapis.setIzvodjacID(izv);
                    zapis.setKompozicijaID(kompozicija);
                    dbbr.insert(zapis);

                }
            }
        }
    }

}
