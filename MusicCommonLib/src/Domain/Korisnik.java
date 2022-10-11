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
public class Korisnik implements Serializable, Domain.OpstiDomenskiObjekat {

    private Long KorisnikID;
    private String Ime;
    private String Prezime;
    private GregorianCalendar ClanOd;
    private String Username;
    private String Password;
    private String Email;

    public Long getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(Long KorisnikID) {
        this.KorisnikID = KorisnikID;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String Ime) {
        this.Ime = Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String Prezime) {
        this.Prezime = Prezime;
    }

    public GregorianCalendar getClanOd() {
        return ClanOd;
    }

    public void setClanOd(GregorianCalendar ClanOd) {
        this.ClanOd = ClanOd;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Korisnik(Long KorisnikID, String Ime, String Prezime, GregorianCalendar ClanOd, String Username, String Password, String Email) {
        this.KorisnikID = KorisnikID;
        this.Ime = Ime;
        this.Prezime = Prezime;
        this.ClanOd = ClanOd;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
    }

    public Korisnik() {
    }

    @Override
    public String dajNazivTabele() {
        return "korisnik";
    }

    @Override
    public String dajNaziveAtributa() {
        return "Ime, Prezime, ClanOd, Username, Password, Email";
        //nema KosinikId zato sto je autoinctementiran
    }

    /**
     * prva kolona ima index 1
     */
    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "KorisnikID";
            case 2:
                return "Ime";
            case 3:
                return "Prezime";
            case 4:
                return "ClanOd";
            case 5:
                return "Username";
            case 6:
                return "Password";
            case 7:
                return "Email";
        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'" + Ime + "', '" + Prezime + "', '" + ClanOd.get(Calendar.YEAR) + "-" + ClanOd.get(Calendar.MONTH) + "-" + ClanOd.get(Calendar.DAY_OF_MONTH) + "', '" + Username + "', '" + Password + "', '" + Email + "'";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        KorisnikID = (Long) id;
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "KorisnikID";
    }

    @Override
    public String getID() {
        return KorisnikID.toString();
    }

    @Override
    public List<String> dajAributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("Ime");
        lista.add("Prezime");
        lista.add("Username");
        return lista;
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "Ime='" + Ime + "', Prezime='" + Prezime + "', Username='" + Username + "', Password='" + Password + "', Email='" + Email + "'";
    }

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public String toString(){
        return Ime+" "+Prezime;
    }
}
