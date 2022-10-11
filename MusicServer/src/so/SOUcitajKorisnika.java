/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import Domain.Korisnik;
import Domain.OpstiDomenskiObjekat;

/**
 *
 * @author Leon
 */
public class SOUcitajKorisnika extends OpstaSistemskaOperacija {

    public SOUcitajKorisnika(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        Korisnik k = (Korisnik) odo;
        response = dbbr.getODOUsingPK(odo, k.getKorisnikID());
        
    }
    
}
