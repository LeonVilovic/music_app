/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import Domain.ZvucniZapis;
import Domain.OpstiDomenskiObjekat;
import files.FileFetcher;

/**
 *
 * @author Leon
 */
public class SOPreslusajZvucniZapis extends OpstaSistemskaOperacija{

    public SOPreslusajZvucniZapis(OpstiDomenskiObjekat odo) {
        super(odo);
    } 

    @Override
    public void izvrsenjeOperacije() throws Exception {
       // ZvucniZapis z = (ZvucniZapis) odo;
        
        ZvucniZapis z = (ZvucniZapis) dbbr.getODOUsingPK(odo, Long.valueOf(odo.getID()));
        FileFetcher f = new FileFetcher();
        response = f.fetchAudioFile(z.getPutDoFajla());
    }
    
}
