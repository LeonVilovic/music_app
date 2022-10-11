/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.view.components;

import Domain.Partitura;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Leon
 */
public class TableModelPartitura extends AbstractTableModel {

    boolean editable;
    List<Partitura> partiture;
    String[] columnNames = new String[]{"PutDoFajla", "Format", "Cena", "Valuta"};

    public TableModelPartitura(List<Partitura> partiture) {
        this.partiture = partiture;
    }

    @Override
    public int getRowCount() {
        return partiture.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Partitura p = partiture.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPutDoFajla();
            case 1:
                return p.getFormat();
            case 2:
                return p.getCena();
            case 3:
                return p.getValuta();

            default:
                return "n/a";
        }
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    public void addPartitura(Partitura partitura) {

        partiture.add(partitura);
    }
    public void removeLastPartitura(){
        partiture.remove(partiture.size()-1);
    }
    
    
    public Partitura getPartituraAt(int rowindex){
        return partiture.get(rowindex);
    
    }
    public List<Partitura> getAllPartiture(){
    return partiture;
    }
}
