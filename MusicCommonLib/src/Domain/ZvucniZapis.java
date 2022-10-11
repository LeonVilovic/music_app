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
public class ZvucniZapis implements Serializable, Domain.OpstiDomenskiObjekat {

    private Long ZvucniZapisID;
    private int Duzina;
    private String PutDoFajla;
    private String Format;
    private Double Cena;
    private String Valuta;
    private Kompozicija KompozicijaID;
    private Izvodjac IzvodjacID;

    public Long getZvucniZapisID() {
        return ZvucniZapisID;
    }

    public void setZvucniZapisID(Long ZvucniZapisID) {
        this.ZvucniZapisID = ZvucniZapisID;
    }

    public int getDuzina() {
        return Duzina;
    }

    public void setDuzina(int Duzina) {
        this.Duzina = Duzina;
    }

    public String getPutDoFajla() {
        return PutDoFajla;
    }

    public void setPutDoFajla(String PutDoFajla) {
        this.PutDoFajla = PutDoFajla;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String Format) {
        this.Format = Format;
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

    public Kompozicija getKompozicijaID() {
        return KompozicijaID;
    }

    public void setKompozicijaID(Kompozicija KompozicijaID) {
        this.KompozicijaID = KompozicijaID;
    }

    public Izvodjac getIzvodjacID() {
        return IzvodjacID;
    }

    public void setIzvodjacID(Izvodjac IzvodjacID) {
        this.IzvodjacID = IzvodjacID;
    }

    public ZvucniZapis(Long ZvucniZapisID, int Duzina, String PutDoFajla, String Format, Double Cena, String Valuta, Kompozicija KompozicijaID, Izvodjac IzvodjacID) {
        this.ZvucniZapisID = ZvucniZapisID;
        this.Duzina = Duzina;
        this.PutDoFajla = PutDoFajla;
        this.Format = Format;
        this.Cena = Cena;
        this.Valuta = Valuta;
        this.KompozicijaID = KompozicijaID;
        this.IzvodjacID = IzvodjacID;
    }

    public ZvucniZapis() {
    }

    @Override
    public String dajNazivTabele() {
        return "zvucni_zapis";
    }

    @Override
    public String dajNaziveAtributa() {
        return "Duzina, PutDoFajla, Format, Cena, Valuta, KompozicijaID, IzvodjacID";
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "ZvucniZapisID";
            case 2:
                return "Duzina";
            case 3:
                return "PutDoFajla";
            case 4:
                return "Format";
            case 5:
                return "Cena";
            case 6:
                return "Valuta";
            case 7:
                return "KompozicijaID";
            case 8:
                return "IzvodjacID";

        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'" + Duzina + "', '" + PutDoFajla + "', '" + Format + "', '" + Cena + "', '" + Valuta + "', '" + KompozicijaID.getKompozicijaID() + "', '" + IzvodjacID.getIzvodjacID() + "'";
    }

    @Override
    public String postaviVrednostiAtributa() {
         return "Duzina='" + Duzina + "', PutDoFajla='" + PutDoFajla + "', Format='" + Format + "', Cena='" + Cena + "', Valuta='" + Valuta + "', KompozicijaID='" + KompozicijaID.getKompozicijaID() + "', IzvodjacID='" + IzvodjacID.getIzvodjacID() + "'";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        ZvucniZapisID = (Long) id;
    }

    @Override
    public String getID() {
        return ZvucniZapisID.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "ZvucniZapisID";
    }

    @Override
    public List<String> dajAributeZaPretragu() {
            List<String> lista = new ArrayList<>();
        lista.add("ZvucniZapisID");
        return lista;}

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
