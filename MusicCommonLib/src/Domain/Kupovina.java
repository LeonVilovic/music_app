/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Leon
 */
public class Kupovina implements Serializable, Domain.OpstiDomenskiObjekat {

    private Korisnik KorisnikID;
    private Kompozicija KompozicijaID;
    private GregorianCalendar DatumVremeKupovine;
    private Double Cena;
    private String Valuta;
    private Double Popust;

    public Korisnik getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(Korisnik KorisnikID) {
        this.KorisnikID = KorisnikID;
    }

    public Kompozicija getKompozicijaID() {
        return KompozicijaID;
    }

    public void setKompozicijaID(Kompozicija KompozicijaID) {
        this.KompozicijaID = KompozicijaID;
    }

    public GregorianCalendar getDatumVremeKupovine() {
        return DatumVremeKupovine;
    }

    public void setDatumVremeKupovine(GregorianCalendar DatumVremeKupovine) {
        this.DatumVremeKupovine = DatumVremeKupovine;
    }

    public Double getCena() {
        return Cena;
    }

    public void setCena(Double Cena) {
        this.Cena = Cena;
    }

    public String getValuta() {
        return Valuta;
    }

    public void setValuta(String Valuta) {
        this.Valuta = Valuta;
    }

    public Double getPopust() {
        return Popust;
    }

    public void setPopust(Double Popust) {
        this.Popust = Popust;
    }

    public Kupovina(Korisnik KorisnikID, Kompozicija KompozicijaID, GregorianCalendar DatumVremeKupovine, Double Cena, String Valuta, Double Popust) {
        this.KorisnikID = KorisnikID;
        this.KompozicijaID = KompozicijaID;
        this.DatumVremeKupovine = DatumVremeKupovine;
        this.Cena = Cena;
        this.Valuta = Valuta;
        this.Popust = Popust;
    }

    @Override
    public String dajNazivTabele() {
        return "kupovina";
    }

    @Override
    public String dajNaziveAtributa() {
        return "KorisnikID, KompozicijaID, DatumVremeKupovine, Cena, Valuta, Popust";
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "KorisnikID";
            case 2:
                return "KompozicijaID";
            case 3:
                return "DatumVremeKupovine";
            case 4:
                return "Cena";
            case 5:
                return "Valuta";
            case 6:
                return "Popust";
        }
        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'" + KorisnikID.getKorisnikID() + "', '" + KompozicijaID.getKompozicijaID() + "', '" + DatumVremeKupovine.get(Calendar.YEAR) + "-" + DatumVremeKupovine.get(Calendar.MONTH) + "-" + DatumVremeKupovine.get(Calendar.DAY_OF_MONTH) + "', '" + Cena + "', '" + Valuta + "', '" + Popust + "'";

    }

    @Override
    public String postaviVrednostiAtributa() {
        return "KorisnikID='" + KorisnikID.getKorisnikID() + "', KompozicijaID='" + KompozicijaID.getKompozicijaID() + "', UbaziOd='" + DatumVremeKupovine.get(Calendar.YEAR) + "-" + DatumVremeKupovine.get(Calendar.MONTH) + "-" + DatumVremeKupovine.get(Calendar.DAY_OF_MONTH) + "', KorisnikID='" + KorisnikID.getID() + "'";
    }

    @Override
    public boolean isAutoincrement() {
        return false;
    }

    @Override
    public void setId(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //ovo je samo za autoincrementalne objekte
    }

    @Override
    public String getID() {
        return "'" + KorisnikID.getKorisnikID() + "', '" + KompozicijaID.getKompozicijaID() + "'";
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        throw new UnsupportedOperationException("Not supported yet. This object has no primary key");
        //nema primarni kljuc
    }
    
    

    @Override
    public List<String> dajAributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("KorisnikID");
        lista.add("KompozicijaID");
        return lista;
    }

    @Override
    public List<String> dajKompleksniKljuc() {
        List<String> lista = new ArrayList<>();
        lista.add("KorisnikID");
        lista.add("KompozicijaID");
        return lista;
    }

}
