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
public class Partitura implements Serializable, Domain.OpstiDomenskiObjekat {

    private Long PartituraID;
    private String PutDoFajla;
    private String Format;
    private Double Cena;
    private String Valuta;
    private Kompozicija KompozicijaID;

    public Partitura() {
    }

    public Long getPartituraID() {
        return PartituraID;
    }

    public void setPartituraID(Long PartituraID) {
        this.PartituraID = PartituraID;
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

    public Partitura(Long PartituraID, String PutDoFajla, String Format, Double Cena, String Valuta, Kompozicija KompozicijaID) {
        this.PartituraID = PartituraID;
        this.PutDoFajla = PutDoFajla;
        this.Format = Format;
        this.Cena = Cena;
        this.Valuta = Valuta;
        this.KompozicijaID = KompozicijaID;
    }

    @Override
    public String dajNazivTabele() {
        return"partitura";
    }

    @Override
    public String dajNaziveAtributa() {
        return "PutDoFajla, Format, Cena, Valuta, KompozicijaID";
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
                switch (brojKolone) {
            case 1:
                return "PartituraID";
            case 2:
                return "PutDoFajla";
            case 3:
                return "Format";
            case 4:
                return "Cena";
            case 5:
                return "Valuta";
            case 6:
                return "KompozicijaID";
        }

        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        return "'" + PutDoFajla + "', '" + Format + "', '" + Cena + "', '" + Valuta + "', '" + KompozicijaID.getKompozicijaID() + "'";
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "PutDoFajla='" + PutDoFajla + "', Format='" + Format + "', Cena='" + Cena + "', Valuta='" + Valuta + "', KompozicijaID='" + KompozicijaID.getKompozicijaID() + "'";
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        PartituraID = (Long)id;
                }

    @Override
    public String getID() {
        return PartituraID.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return"PartituraID";
    }

    @Override
    public List<String> dajAributeZaPretragu() {
          List<String> lista = new ArrayList<>();
        lista.add("PartituraID");
        return lista;}

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
