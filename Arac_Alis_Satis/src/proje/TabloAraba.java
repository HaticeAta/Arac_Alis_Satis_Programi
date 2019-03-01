/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hatice
 */
public class TabloAraba extends AbstractTableModel {

    private int satirsayisi;
    private int sutunsayisi;
    private ResultSet res;
    private ArrayList veriler = new ArrayList();

    public TabloAraba(ResultSet res) throws SQLException {
        this.res = res;
        ResultSetMetaData rsmd = res.getMetaData();
        satirsayisi = 0;
        sutunsayisi = rsmd.getColumnCount();

        while (res.next()) {
            Object[] satirbilgi = new Object[sutunsayisi];
            for (int i = 0; i < sutunsayisi; i++) {
                satirbilgi[i] = res.getObject(i + 1);
            }
            veriler.add(satirbilgi);
            satirsayisi++;
        }
    }

    @Override
    public int getRowCount() {
        return satirsayisi;
    }

    @Override
    public int getColumnCount() {
        return sutunsayisi;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Object[] satirbilgi = (Object[]) veriler.get(i);
        return satirbilgi[i1];
    }
    String[] isim = {"ArabaID", "Marka", "Model", "RenkID", "YakitID", "VitesID"};

    @Override
    public String getColumnName(int sutunindex) {

        return isim[sutunindex];
    }

}
