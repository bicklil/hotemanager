/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modèle de JTable pour le composant tableResa de la VuePrincipale
 *
 * @author riviere
 */
public class TableReservations extends AbstractTableModel {

    private List<Date> datesDebut;
    private List<Integer> nbNuits;
    private List<Integer> idsClient;
    private HashMap<Integer, List<Integer>> resa;

    public TableReservations() {
        datesDebut = new ArrayList<>();
        nbNuits = new ArrayList<>();
        idsClient = new ArrayList<>();
        resa = new HashMap<Integer, List<Integer>>();
    }

    @Override
    public int getRowCount() {
        return nbNuits.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID Réservation";
            case 1:
                return "Date de début";
            case 2:
                return "Nombre de jours";
            case 3:
                return "ID Client associé";
            default:
                return "Numéro Chambre";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Date.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return resa.keySet().toArray()[rowIndex];
            case 1:
                return datesDebut.get(rowIndex);
            case 2:
                return nbNuits.get(rowIndex);
            case 3:
                return idsClient.get(rowIndex);
            default:
                return chambresReserveesToString(resa.get(rowIndex));
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                List<Integer> v = resa.get(rowIndex);
                resa.remove(rowIndex);
                resa.put((Integer) aValue, v);
            case 1:
                datesDebut.set(rowIndex, (Date) aValue);
            case 2:
                nbNuits.set(rowIndex, (Integer) aValue);
            case 3:
                idsClient.set(rowIndex, (Integer) aValue);
            default:
                resa.put(rowIndex, (List<Integer>) aValue);
        }
    }

    private String chambresReserveesToString(List<Integer> ch) {
        String r = "";
        for (int i = 0; i < ch.size(); i++) {
            if (i == ch.size() - 1) {
                r = r.concat(ch.get(i) + "");
            } else {
                r = r.concat(ch.get(i) + "-");
            }
        }
        return r;
    }

    /**
     * Mettre à jour le contenu de la table
     *
     * @param resa Liste des identifiants des réservations existantes ainsi que
     * la liste des chambres associées
     * @param datesDebut Liste des différentes dates de débuts de chacune des
     * réservations existantes
     * @param nbNuits Liste des différents nombres de nuits de chacune des
     * réservations existantes
     * @param idsClient Liste des différents identifiants des clients ayant
     * passé les réservations existantes
     */
    public void update(HashMap<Integer, List<Integer>> resa, List<Date> datesDebut, List<Integer> nbNuits, List<Integer> idsClient) {
        this.resa = resa;
        this.datesDebut = datesDebut;
        this.nbNuits = nbNuits;
        this.idsClient = idsClient;

        this.fireTableDataChanged();
    }

}
