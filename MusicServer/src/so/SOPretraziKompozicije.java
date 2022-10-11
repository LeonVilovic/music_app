/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import Domain.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Leon
 */
public class SOPretraziKompozicije extends OpstaSistemskaOperacija{

    String kriterijumPretrage;
    List<OpstiDomenskiObjekat> rezultatPretrage;
    
    public SOPretraziKompozicije(OpstiDomenskiObjekat odo, String kriterijumPretrage) {
        super(odo);
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        rezultatPretrage = dbbr.searchKompozicijaComplex(odo, kriterijumPretrage);
        response = rezultatPretrage;
    }
    
}
