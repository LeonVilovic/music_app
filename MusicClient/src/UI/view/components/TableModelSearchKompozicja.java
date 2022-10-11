/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.view.components;

import Domain.Izvodjac;
import Domain.Kompozicija;
import Domain.Korisnik;
import Domain.Partitura;
import Domain.ZvucniZapis;
import Domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Leon
 */
public class TableModelSearchKompozicja extends AbstractTableModel {

    boolean editable;
    List<Kompozicija> kompozicije;
    List<Partitura> partiture;
    List<ZvucniZapis> zvucniZapisi;
    List<Izvodjac> izvodjaci;
    List<Korisnik> korisnikci;

    String[] columnNames = new String[]{"Naziv Kompozicije", "Kompozitor", "Partitura", "Zvucni Zapis", "Izvodjac"};

    public TableModelSearchKompozicja(List<Kompozicija> kompozicije, List<Partitura> partiture, List<ZvucniZapis> zvucniZapisi, List<Izvodjac> izvodjaci, List<Korisnik> korisnikci) {
        this.kompozicije = kompozicije;
        this.partiture = partiture;
        this.zvucniZapisi = zvucniZapisi;
        this.izvodjaci = izvodjaci;
        this.korisnikci = korisnikci;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public int getRowCount() {
        return kompozicije.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kompozicija kompozicija = kompozicije.get(rowIndex);
        Partitura partitura = partiture.get(rowIndex);
        ZvucniZapis zvucniZapis = zvucniZapisi.get(rowIndex);
        Izvodjac izvodjac = izvodjaci.get(rowIndex);
        Korisnik korisnik = korisnikci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kompozicija.getNaziv();
            case 1:
                return korisnik.getIme() + " " + korisnik.getPrezime();
            case 2:
                return partitura.getPutDoFajla();
            case 3:
                return zvucniZapis.getPutDoFajla();
            case 4:
                return izvodjac.getNaziv();
            default:
                return "n/a";
        }
    }

    /**
     * vraca Listu Opstih Domenskih Objekata pri cemu je prvi element tipa
     * Kompozicija drugi tipa Partitura treci tipa ZvuciZapis cetvrti tipa
     * Izvodjac i peti tipa Korisnik
     *
     * @param rowIndex
     * @return
     */
    public List<OpstiDomenskiObjekat> getKopozicijaDataAt(int rowIndex) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        lista.add(kompozicije.get(rowIndex));
        lista.add(partiture.get(rowIndex));
        lista.add(zvucniZapisi.get(rowIndex));
        lista.add(izvodjaci.get(rowIndex));
        lista.add(korisnikci.get(rowIndex));
        return lista;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }
}
