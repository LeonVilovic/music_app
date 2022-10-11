/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import Domain.Korisnik;


/**
 *
 * @author Leon
 */
public class SOZapamtiKorisnika extends OpstaSistemskaOperacija{

    public SOZapamtiKorisnika(Korisnik korisnik) {
        super(korisnik);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        dbbr.insert(odo);
    }
    
}
