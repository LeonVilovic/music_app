/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Domain.Kompozicija;
import Domain.Korisnik;
import Domain.Kupovina;
import Domain.ZvucniZapis;
import Domain.OpstiDomenskiObjekat;
import java.io.File;
import java.util.List;
import so.OpstaSistemskaOperacija;
import so.SOIzmeniKompoziciju;
import so.SOIzmeniKorisnika;
import so.SOKupiKompoziciju;
import so.SOBrisiKorisnika;
import so.SOPreslusajZvucniZapis;
import so.SOPretraziKompozicije;
import so.SOPretraziKorisnike;
import so.SOUcitajKorisnika;
import so.SOUlogujKorisnika;
import so.SOZapamtiKompoziciju;
import so.SOZapamtiKorisnika;
import validator.impl.ValidatorKupovinaKompozicije;
import validator.impl.ValidatorUlogujKorisnka;
import validator.impl.ValidatorZapamtiKompoziciju;
import validator.impl.ValidatorZapamtiKorisnika;

/**
 *
 * @author student1
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Korisnik logIn(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOUlogujKorisnika(korisnik);
        so.setValidator(new ValidatorUlogujKorisnka());
        so.opsteIzvrsenje();
        return (Korisnik) so.getResponse();
    }
    public Korisnik getKorisnik(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOUcitajKorisnika(korisnik);
        so.opsteIzvrsenje();
        return (Korisnik) so.getResponse();
    }
    

    public void saveKorisnik(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOZapamtiKorisnika(korisnik);
        so.setValidator(new ValidatorZapamtiKorisnika());
        so.opsteIzvrsenje();
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOBrisiKorisnika(korisnik);
        so.opsteIzvrsenje();
    }

    public void deleteKompozicija(Kompozicija kompozicija) throws Exception {
        OpstaSistemskaOperacija so = new SOBrisiKorisnika(kompozicija);
        so.opsteIzvrsenje();
    }

    public void updateKorisnik(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOIzmeniKorisnika(korisnik);
        so.setValidator(new ValidatorZapamtiKorisnika());
        so.opsteIzvrsenje();
    }

    public void updateKompozicija(Kompozicija kompozicija) throws Exception {
        OpstaSistemskaOperacija so = new SOIzmeniKompoziciju(kompozicija);
        so.opsteIzvrsenje();
    }

    public List<Korisnik> searchKorisnik(Korisnik korisnik, String kriterijumPretrage) throws Exception {
        OpstaSistemskaOperacija so = new SOPretraziKorisnike(korisnik, kriterijumPretrage);
        so.opsteIzvrsenje();
        return (List<Korisnik>) so.getResponse();
    }

    public void saveKompozicija(Kompozicija kompozicija, List<OpstiDomenskiObjekat> zvucniZapisi, List<OpstiDomenskiObjekat> izvodjaci, List<OpstiDomenskiObjekat> partiture) throws Exception {
        OpstaSistemskaOperacija so = new SOZapamtiKompoziciju(kompozicija, zvucniZapisi, izvodjaci, partiture);
        so.setValidator(new ValidatorZapamtiKompoziciju());
        so.opsteIzvrsenje();
    }

    public List<OpstiDomenskiObjekat> searchKompozicija(Kompozicija kompozicija, String kriterijumPretrage) throws Exception {
        OpstaSistemskaOperacija so = new SOPretraziKompozicije(kompozicija, kriterijumPretrage);
        so.opsteIzvrsenje();
        return (List<OpstiDomenskiObjekat>) so.getResponse();
    }
    
    public File getFileZvucniZapis(ZvucniZapis zvucniZapis) throws Exception{
       OpstaSistemskaOperacija so = new SOPreslusajZvucniZapis(zvucniZapis);
        so.opsteIzvrsenje();
        return (File) so.getResponse();
    }
    
        public void kupiKompoziciju(Kupovina kupovina) throws Exception {
        OpstaSistemskaOperacija so = new SOKupiKompoziciju(kupovina);
        so.setValidator(new ValidatorKupovinaKompozicije());
        so.opsteIzvrsenje();
    }
        

}
