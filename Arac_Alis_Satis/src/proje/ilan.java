/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Hatice
 */
public class ilan extends JPanel implements ActionListener {

    static Connection con;
    static Statement st;
    JButton eklebut, silbut, guncellebut, temizle;
    JTextField ilanIDtxt, ilanaditxt, ilanfiyattxt, ilankmtxt, ilantarihtxt, ilanarabaidtxt, ilansehirtxt;
    JLabel yazi1, yazi2, yazi3, yazi4, yazi5, yazi6, yazi7;
    JTable tablo;
    int ilanID, ilanfiyat, ilanKM, ilansehirID, ilanarabaID;
    String ilanadi, ilantarih;
    JScrollPane sp;

    public void verilerigoster() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM ilan";
            ResultSet sonuc = st.executeQuery(sql);
            TabloIlan tbl = new TabloIlan(sonuc);

            tablo = new JTable();
            tablo.setBounds(250, 20, 500, 400);

            sp = new JScrollPane();
            sp.setBounds(250, 20, 500, 400);

            tablo.setModel(tbl);
            add(tablo);

            add(sp);
            sp.setViewportView(tablo);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "İlanlar Gösterilemiyor");
        }
    }

    public void guncelle() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM ilan";
            ResultSet sonuc = st.executeQuery(sql);
            TabloIlan tbl = new TabloIlan(sonuc);

            tablo.setModel(tbl);
            add(tablo);

            add(sp);
            sp.setViewportView(tablo);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "İlanlar Gösterilemiyor");
        }
    }

    public void ekle(int ID, String ad, int fiyat, int KM, String tarih, int sehirID, int arabaID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            String sql = "INSERT INTO ilan VALUES(?,?,?,?,?,?,?)";
            PreparedStatement p1 = con.prepareStatement(sql);

            p1.setInt(1, ID);
            p1.setString(2, ad);
            p1.setInt(3, fiyat);
            p1.setInt(4, KM);
            p1.setString(5, tarih);
            p1.setInt(6, sehirID);
            p1.setInt(7, arabaID);
            int sonuc = p1.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "İlan Eklendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "İlan Eklenemedi");
            }
            p1.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "İlan Eklenemedi");
        }
    }

    public void sil(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "DELETE from ilan Where ilanID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "İlan Silindi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "İlan Silinemedi");
            }

            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "İlan Silinemedi");
        }
    }

    public void guncelle(int ID, String ad, int fiyat, int KM, String tarih, int sehirID, int arabaID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "UPDATE ilan "
                    + "SET ilanAdi= ? , "
                    + "ilanFiyat=? , "
                    + "ilanKM=? , "
                    + "ilanTarih=? , "
                    + "ilanSehirID=? ,"
                    + "ilanArabaID=? "
                    + "WHERE ilanID=? ";
            PreparedStatement p = con.prepareStatement(sql);

            p.setString(1, ad);
            p.setInt(2, fiyat);
            p.setInt(3, KM);
            p.setString(4, tarih);
            p.setInt(5, sehirID);
            p.setInt(6, arabaID);
            p.setInt(7, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "İlan Güncellendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "İlan Güncellenemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "İlan Güncellenemedi");
        }
    }

    public ilan() {

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        yazi1 = new JLabel("IlanID");
        yazi1.setBounds(30, 20, 50, 20);
        add(yazi1);
        ilanIDtxt = new JTextField(15);
        ilanIDtxt.setBounds(100, 20, 100, 20);
        add(ilanIDtxt);

        yazi2 = new JLabel("IlanAdi");
        yazi2.setBounds(30, 50, 50, 20);
        add(yazi2);
        ilanaditxt = new JTextField(15);
        ilanaditxt.setBounds(100, 50, 100, 20);
        add(ilanaditxt);

        yazi3 = new JLabel("Fiyat");
        yazi3.setBounds(30, 80, 50, 20);
        add(yazi3);
        ilanfiyattxt = new JTextField(15);
        ilanfiyattxt.setBounds(100, 80, 100, 20);
        add(ilanfiyattxt);

        yazi4 = new JLabel("KM");
        yazi4.setBounds(30, 110, 50, 20);
        add(yazi4);
        ilankmtxt = new JTextField(15);
        ilankmtxt.setBounds(100, 110, 100, 20);
        add(ilankmtxt);

        yazi5 = new JLabel("Tarih");
        yazi5.setBounds(30, 140, 50, 20);
        add(yazi5);
        ilantarihtxt = new JTextField(15);
        ilantarihtxt.setBounds(100, 140, 100, 20);
        add(ilantarihtxt);

        yazi7 = new JLabel("SehirID");
        yazi7.setBounds(30, 170, 50, 20);
        add(yazi7);
        ilansehirtxt = new JTextField(15);
        ilansehirtxt.setBounds(100, 170, 100, 20);
        add(ilansehirtxt);

        yazi6 = new JLabel("ArabaID");
        yazi6.setBounds(30, 200, 50, 20);
        add(yazi6);
        ilanarabaidtxt = new JTextField(15);
        ilanarabaidtxt.setBounds(100, 200, 100, 20);
        add(ilanarabaidtxt);

        eklebut = new JButton("EKLE");
        eklebut.setActionCommand("ekle");
        eklebut.addActionListener(this);
        eklebut.setBounds(60, 260, 100, 20);
        eklebut.setBackground(Color.decode("#7ec0ee"));
        add(eklebut);

        silbut = new JButton("SİL");
        silbut.setActionCommand("sil");
        silbut.addActionListener(this);
        silbut.setBackground(Color.decode("#7ec0ee"));
        silbut.setBounds(60, 300, 100, 20);
        add(silbut);

        guncellebut = new JButton("GÜNCELLE");
        guncellebut.setActionCommand("guncelle");
        guncellebut.addActionListener(this);
        guncellebut.setBounds(60, 340, 100, 20);
        guncellebut.setBackground(Color.decode("#7ec0ee"));
        add(guncellebut);

        temizle = new JButton("TEMİZLE");
        temizle.setActionCommand("temizle");
        temizle.addActionListener(this);
        temizle.setBounds(60, 380, 100, 20);
        temizle.setBackground(Color.decode("#7ec0ee"));
        add(temizle);

        verilerigoster();
    }

    public void temizle() {
        ilanIDtxt.setText("");
        ilanaditxt.setText("");
        ilanfiyattxt.setText("");
        ilankmtxt.setText("");
        ilantarihtxt.setText("");
        ilanarabaidtxt.setText("");
        ilansehirtxt.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("ekle")) {
            ilanID = Integer.parseInt(ilanIDtxt.getText());
            ilanadi = ilanaditxt.getText();
            ilanfiyat = Integer.parseInt(ilanfiyattxt.getText());
            ilanKM = Integer.parseInt(ilankmtxt.getText());
            ilantarih = ilantarihtxt.getText();
            ilansehirID = Integer.parseInt(ilansehirtxt.getText());
            ilanarabaID = Integer.parseInt(ilanarabaidtxt.getText());
            ekle(ilanID, ilanadi, ilanfiyat, ilanKM, ilantarih, ilansehirID, ilanarabaID);
        } else if (text.equals("sil")) {
            ilanID = Integer.parseInt(ilanIDtxt.getText());
            sil(ilanID);
        } else if (text.equals("guncelle")) {
            ilanID = Integer.parseInt(ilanIDtxt.getText());
            ilanadi = ilanaditxt.getText();
            ilanfiyat = Integer.parseInt(ilanfiyattxt.getText());
            ilanKM = Integer.parseInt(ilankmtxt.getText());
            ilantarih = ilantarihtxt.getText();
            ilansehirID = Integer.parseInt(ilansehirtxt.getText());
            ilanarabaID = Integer.parseInt(ilanarabaidtxt.getText());
            guncelle(ilanID, ilanadi, ilanfiyat, ilanKM, ilantarih, ilansehirID, ilanarabaID);
        } else if (text.equals("temizle")) {
            temizle();
        }
    }

}
