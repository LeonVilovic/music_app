/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leon
 */
public class Izvodjac implements Serializable, Domain.OpstiDomenskiObjekat {

    private Long IzvodjacID;
    private String Naziv;

    public Long getIzvodjacID() {
        return IzvodjacID;
    }

    public void setIzvodjacID(Long IzvodjacID) {
        this.IzvodjacID = IzvodjacID;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public Izvodjac(Long IzvodjacID, String Naziv) {
        this.IzvodjacID = IzvodjacID;
        this.Naziv = Naziv;
    }

    public Izvodjac() {
    }
    
    @Override
    public String dajNazivTabele() {
        return "izvodjac";
    }

    @Override
    public String dajNaziveAtributa() {
        return "Naziv";
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "IzvodjacID";
            case 2:
                return "Naziv";
        }
        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'" + Naziv + "'";
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "Naziv='" + Naziv + "'";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
         IzvodjacID = (Long) id;
    }

    @Override
    public String getID() {
       return IzvodjacID.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "IzvodjacID";
    }

    @Override
    public List<String> dajAributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("Naziv");
        return lista;
    }

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
