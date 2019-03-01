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
public class araba extends JPanel implements ActionListener {

    static Connection con;
    static Statement st;
    JButton eklebut, silbut, guncellebut, temizle;
    JTextField arabaIDtxt, markatxt, modeltxt, vitestxt, yakittxt, renktxt;
    JLabel yazi1, yazi2, yazi3, yazi4, yazi5, yazi6;
    JTable tablo;
    int arabaID, arabayakitID, arabavitesID, arabarenkID;
    String arabamarka, arabamodel;
    JScrollPane sp;

    public void verilerigoster() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM araba";
            ResultSet sonuc = st.executeQuery(sql);
            TabloAraba tbl = new TabloAraba(sonuc);

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
            JOptionPane.showMessageDialog(null, "Araba Kayıtları Gösterilemiyor");
        }
    }

    public void guncelle() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM araba";
            ResultSet sonuc = st.executeQuery(sql);
            TabloAraba tbl = new TabloAraba(sonuc);

            tablo.setModel(tbl);
            add(tablo);

            add(sp);
            sp.setViewportView(tablo);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Araba Kayıtları Gösterilemiyor");
        }
    }

    public void ekle(int ID, String marka, String model, int renkID, int yakitID, int vitesID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            String sql = "INSERT INTO araba VALUES(?,?,?,?,?,?)";
            PreparedStatement p1 = con.prepareStatement(sql);
            p1.setInt(1, ID);
            p1.setString(2, marka);
            p1.setString(3, model);
            p1.setInt(4, renkID);
            p1.setInt(5, yakitID);
            p1.setInt(6, vitesID);
            int sonuc = p1.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Araba Eklendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Araba Eklenemedi");
            }
            p1.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Araba Eklenemedi");
        }
    }

    public void sil(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "DELETE from araba Where arabaID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Araba Silindi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Araba Silinemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Araba Silinemedi");
        }
    }

    public void guncelle(int ID, String marka, String model, int renkID, int yakitID, int vitesID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "UPDATE araba "
                    + "SET arabaMarka= ? , "
                    + "arabaModel=? , "
                    + "arabaRenkID=? , "
                    + "arabaYakitTuruID=? , "
                    + "arabaVitesTuruID=? "
                    + "WHERE arabaID=? ";
            PreparedStatement p = con.prepareStatement(sql);

            p.setString(1, marka);
            p.setString(2, model);
            p.setInt(3, renkID);
            p.setInt(4, yakitID);
            p.setInt(5, vitesID);
            p.setInt(6, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Araba Güncellendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Araba Güncellenemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Araba Güncellenemedi");
        }
    }

    public void temizle() {
        arabaIDtxt.setText("");
        markatxt.setText("");
        modeltxt.setText("");
        renktxt.setText("");
        yakittxt.setText("");
        vitestxt.setText("");
    }

    public araba() {

        setLayout(null);
        setBackground(Color.lightGray);
        yazi1 = new JLabel("ArabaID");
        yazi1.setBounds(30, 20, 50, 20);
        add(yazi1);
        arabaIDtxt = new JTextField(15);
        arabaIDtxt.setBounds(100, 20, 100, 20);
        add(arabaIDtxt);

        yazi2 = new JLabel("Marka");
        yazi2.setBounds(30, 50, 50, 20);
        add(yazi2);
        markatxt = new JTextField(15);
        markatxt.setBounds(100, 50, 100, 20);
        add(markatxt);

        yazi3 = new JLabel("Model");
        yazi3.setBounds(30, 80, 50, 20);
        add(yazi3);
        modeltxt = new JTextField(15);
        modeltxt.setBounds(100, 80, 100, 20);
        add(modeltxt);

        yazi4 = new JLabel("Renk");
        yazi4.setBounds(30, 110, 80, 20);
        add(yazi4);
        renktxt = new JTextField(15);
        renktxt.setBounds(100, 110, 100, 20);
        add(renktxt);

        yazi5 = new JLabel("YakitTuru");
        yazi5.setBounds(30, 140, 80, 20);
        add(yazi5);
        yakittxt = new JTextField(15);
        yakittxt.setBounds(100, 140, 100, 20);
        add(yakittxt);

        yazi6 = new JLabel("Vites");
        yazi6.setBounds(30, 170, 50, 20);
        add(yazi6);
        vitestxt = new JTextField(15);
        vitestxt.setBounds(100, 170, 100, 20);
        add(vitestxt);

        eklebut = new JButton("EKLE");
        eklebut.setActionCommand("ekle");
        eklebut.addActionListener(this);
        eklebut.setBounds(60, 230, 100, 20);
        eklebut.setBackground(Color.decode("#7ec0ee"));
        add(eklebut);

        silbut = new JButton("SİL");
        silbut.setActionCommand("sil");
        silbut.addActionListener(this);
        silbut.setBounds(60, 270, 100, 20);
        silbut.setBackground(Color.decode("#7ec0ee"));
        add(silbut);

        guncellebut = new JButton("GÜNCELLE");
        guncellebut.setActionCommand("guncelle");
        guncellebut.addActionListener(this);
        guncellebut.setBounds(60, 310, 100, 20);
        guncellebut.setBackground(Color.decode("#7ec0ee"));
        add(guncellebut);

        temizle = new JButton("TEMİZLE");
        temizle.setActionCommand("temizle");
        temizle.addActionListener(this);
        temizle.setBounds(60, 350, 100, 20);
        temizle.setBackground(Color.decode("#7ec0ee"));
        add(temizle);

        verilerigoster();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String text = e.getActionCommand();

        if (text.equals("ekle")) {
            arabaID = Integer.parseInt(arabaIDtxt.getText());
            arabamarka = markatxt.getText();
            arabamodel = modeltxt.getText();
            arabarenkID = Integer.parseInt(renktxt.getText());
            arabayakitID = Integer.parseInt(yakittxt.getText());
            arabavitesID = Integer.parseInt(vitestxt.getText());
            ekle(arabaID, arabamarka, arabamodel, arabarenkID, arabayakitID, arabavitesID);
        } else if (text.equals("sil")) {
            arabaID = Integer.parseInt(arabaIDtxt.getText());
            sil(arabaID);
        } else if (text.equals("guncelle")) {
            arabaID = Integer.parseInt(arabaIDtxt.getText());
            arabamarka = markatxt.getText();
            arabamodel = modeltxt.getText();
            arabarenkID = Integer.parseInt(renktxt.getText());
            arabayakitID = Integer.parseInt(yakittxt.getText());
            arabavitesID = Integer.parseInt(vitestxt.getText());
            guncelle(arabaID, arabamarka, arabamodel, arabarenkID, arabayakitID, arabavitesID);
        } else if (text.equals("temizle")) {
            temizle();
        }

    }

}
