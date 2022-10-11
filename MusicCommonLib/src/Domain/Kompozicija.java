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
public class Kompozicija implements Serializable, Domain.OpstiDomenskiObjekat{
    private Long KompozicijaID;
    private String Naziv;
    private GregorianCalendar UbaziOd;
    private Korisnik KorisnikID;

    public Long getKompozicijaID() {
        return KompozicijaID;
    }

    public Kompozicija() {
    }

    public void setKompozicijaID(Long KompozicijaID) {
        this.KompozicijaID = KompozicijaID;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public GregorianCalendar getUbaziOd() {
        return UbaziOd;
    }

    public void setUbaziOd(GregorianCalendar UbaziOd) {
        this.UbaziOd = UbaziOd;
    }

    public Korisnik getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(Korisnik KorisnikID) {
        this.KorisnikID = KorisnikID;
    }

    public Kompozicija(Long KompozicijaID, String Naziv, GregorianCalendar UbaziOd, Korisnik KorisnikID) {
        this.KompozicijaID = KompozicijaID;
        this.Naziv = Naziv;
        this.UbaziOd = UbaziOd;
        this.KorisnikID = KorisnikID;
    }

    @Override
    public String dajNazivTabele() {
        return "kompozicija"; 
    }

    @Override
    public String dajNaziveAtributa() {
        return "Naziv, UBaziOd, KorisnikID";
    //nema KompozicijaId zato sto je autoinctementiran
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "KompozicijaID";
            case 2:
                return "Naziv";
            case 3:
                return "UBaziOd";
            case 4:
                return "KorisnikID";
        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'"+Naziv+"', '"+UbaziOd.get(Calendar.YEAR)+"-"+UbaziOd.get(Calendar.MONTH)+"-"+UbaziOd.get(Calendar.DAY_OF_MONTH)+"', '"+KorisnikID.getID()+"'";
               }

    @Override
    public String postaviVrednostiAtributa() {
        return "Naziv='"+Naziv+"', UbaziOd='"+UbaziOd.get(Calendar.YEAR)+"-"+UbaziOd.get(Calendar.MONTH)+"-"+UbaziOd.get(Calendar.DAY_OF_MONTH)+"', KorisnikID='"+KorisnikID.getID()+"'";
        
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        KompozicijaID=(Long)id; 
    }

    @Override
    public String getID() {
        return KompozicijaID+"";}

    @Override
    public String dajNazivPrimarnogKljuca() {
        return"KompozicijaID";
    }

    @Override
    public List<String> dajAributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("Naziv");
        return lista;}

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
