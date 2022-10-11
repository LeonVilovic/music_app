/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.view.components;

import Domain.Izvodjac;
import Domain.ZvucniZapis;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Leon
 */
public class TableModelZvucniZapis extends AbstractTableModel {

    boolean editable;
    List<ZvucniZapis> zvucniZapisi;
    List<Izvodjac> izvodjaci;
    String[] columnNames = new String[]{"Duzina", "PutDoFajla", "Format", "Cena", "Valuta", "Izvodjac"};

    public TableModelZvucniZapis(List<ZvucniZapis> zvucniZapisi, List<Izvodjac> izvodjaci) {
        this.zvucniZapisi = zvucniZapisi;
        this.izvodjaci = izvodjaci;
    }

    @Override
    public int getRowCount() {
        return zvucniZapisi.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    public void addZvucniZapisSaIzvodjacem(ZvucniZapis z, Izvodjac i) {
        zvucniZapisi.add(z);
        izvodjaci.add(i);
    }
    public void removeLastZvucniZapis() {
        zvucniZapisi.remove(zvucniZapisi.size()-1);
        izvodjaci.remove(izvodjaci.size()-1);
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ZvucniZapis z = zvucniZapisi.get(rowIndex);
        Izvodjac i = izvodjaci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getDuzina();
            case 1:
                return z.getPutDoFajla();
            case 2:
                return z.getFormat();
            case 3:
                return z.getCena();
            case 4:
                return z.getValuta();
            case 5:
                return i.getNaziv();
            default:
                return "n/a";
        }
    }

    public ZvucniZapis getZvucniZapisAt(int rowindex) {
        return zvucniZapisi.get(rowindex);
    }
    public Izvodjac getIzvodjacAt(int rowindex) {
        return izvodjaci.get(rowindex);
    }
    public List<ZvucniZapis> getAllZvucniZapis(){
        return zvucniZapisi;
    }
    public List<Izvodjac> getAllIzvodjac(){
        return izvodjaci;
    }
    
}
