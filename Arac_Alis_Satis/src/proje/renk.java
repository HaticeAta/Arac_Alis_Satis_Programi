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
public class renk extends JPanel implements ActionListener {

    static Connection con;
    static Statement st;
    String renk;
    int renkID;
    JTextField renktxt, renkIDtxt;
    JButton eklebut, silbut, guncellebut, temizle;
    JLabel yazi, yazi1;
    JTable tablo;
    JScrollPane sp;

    public void ekle(String renk, int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            String sql = "INSERT INTO renk VALUES(?,?)";
            PreparedStatement p1 = con.prepareStatement(sql);
            p1.setInt(1, ID);
            p1.setString(2, renk);
            int sonuc = p1.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Renk Eklendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Renk Eklenemedi");
            }
            p1.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Renk Eklenemedi");
        }
    }

    public void sil(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "DELETE from renk Where renkID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Renk Silindi");
                guncelle();

            } else {
                JOptionPane.showMessageDialog(null, "Renk Silinemedi");
            }

            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Renk Silinemedi");
        }
    }

    public void guncelle(int ID, String guncelrenk) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "UPDATE renk SET renk=? WHERE renkID=?";
            PreparedStatement p = con.prepareStatement(sql);

            p.setString(1, guncelrenk);
            p.setInt(2, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Renk Güncellendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Renk Güncellenemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Renk Güncellenemedi");
        }
    }

    public void verilerigoster() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM renk ";
            ResultSet sonuc = st.executeQuery(sql);

            TabloRenk tbl = new TabloRenk(sonuc);

            tablo = new JTable();
            tablo.setBounds(350, 20, 300, 320);

            sp = new JScrollPane();
            sp.setBounds(350, 20, 300, 320);

            tablo.setModel(tbl);
            add(tablo);

            add(sp);
            sp.setViewportView(tablo);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Renkler Gösterilemiyor");
        }
    }

    public void guncelle() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM renk ";
            ResultSet sonuc = st.executeQuery(sql);

            TabloRenk tbl = new TabloRenk(sonuc);

            tablo.setModel(tbl);
            add(tablo);

            add(sp);
            sp.setViewportView(tablo);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Renkler Gösterilemiyor");
        }
    }

    public renk() {

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        yazi1 = new JLabel("RenkID");
        yazi1.setBounds(60, 40, 50, 20);
        add(yazi1);

        renkIDtxt = new JTextField(20);
        renkIDtxt.setBounds(110, 40, 100, 20);
        add(renkIDtxt);

        yazi = new JLabel("Renk");
        yazi.setBounds(60, 80, 50, 20);
        add(yazi);

        renktxt = new JTextField(20);
        renktxt.setBounds(110, 80, 100, 20);
        add(renktxt);

        eklebut = new JButton("EKLE");
        eklebut.setActionCommand("ekle");
        eklebut.addActionListener(this);
        eklebut.setBounds(90, 140, 100, 20);
        eklebut.setBackground(Color.decode("#7ec0ee"));
        add(eklebut);

        silbut = new JButton("SİL");
        silbut.setActionCommand("sil");
        silbut.addActionListener(this);
        silbut.setBounds(90, 180, 100, 20);
        silbut.setBackground(Color.decode("#7ec0ee"));
        add(silbut);

        guncellebut = new JButton("GÜNCELLE");
        guncellebut.setActionCommand("guncelle");
        guncellebut.addActionListener(this);
        guncellebut.setBounds(90, 220, 100, 20);
        guncellebut.setBackground(Color.decode("#7ec0ee"));
        add(guncellebut);

        temizle = new JButton("TEMİZLE");
        temizle.setActionCommand("temizle");
        temizle.addActionListener(this);
        temizle.setBounds(90, 260, 100, 20);
        temizle.setBackground(Color.decode("#7ec0ee"));
        add(temizle);

        verilerigoster();

    }

    public void temizle() {
        renkIDtxt.setText("");
        renktxt.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();

        if (text.equals("ekle")) {
            renkID = Integer.parseInt(renkIDtxt.getText());
            renk = renktxt.getText();
            ekle(renk, renkID);
        } else if (text.equals("sil")) {
            renkID = Integer.parseInt(renkIDtxt.getText());
            sil(renkID);
        } else if (text.equals("guncelle")) {
            renk = renktxt.getText();
            renkID = Integer.parseInt(renkIDtxt.getText());
            guncelle(renkID, renk);
        } else if (text.equals("temizle")) {
            temizle();
        }
    }

}
