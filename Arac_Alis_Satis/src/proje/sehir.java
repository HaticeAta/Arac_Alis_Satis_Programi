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
public class sehir extends JPanel implements ActionListener {

    static Connection con;
    static Statement st;
    String sehir;
    int sehirID;
    JTextField sehirtxt, sehirIDtxt;
    JButton eklebut, silbut, guncellebut, temizle;
    JLabel yazi, yazi1;
    JTable tablo;
    JScrollPane sp;
    TabloSehir tbl;

    public void verilerigoster() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM sehir ";
            ResultSet sonuc = st.executeQuery(sql);
            tbl = new TabloSehir(sonuc);

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
            JOptionPane.showMessageDialog(null, "Sehirler Gösterilemiyor");
        }
    }

    public void guncelle() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM sehir ";
            ResultSet sonuc = st.executeQuery(sql);
            TabloSehir tbl = new TabloSehir(sonuc);

            tablo.setModel(tbl);
            add(tablo);
            tablo.setCellSelectionEnabled(false);
            add(sp);
            sp.setViewportView(tablo);

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sehirler Gösterilemiyor");
        }
    }

    public void ekle(String sehir, int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            String sql = "INSERT INTO sehir VALUES(?,?)";
            PreparedStatement p1 = con.prepareStatement(sql);
            p1.setInt(1, ID);
            p1.setString(2, sehir);
            int sonuc = p1.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Sehir Eklendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Sehir Eklenemedi");
            }
            p1.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sehir Eklenemedi");
        }
    }

    public void sil(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "DELETE from sehir Where sehirID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Sehir Silindi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Sehir Silinemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sehir Silinemedi");
        }
    }

    public void guncelle(int ID, String guncelsehir) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "UPDATE sehir SET sehir=? WHERE sehirID=?";
            PreparedStatement p = con.prepareStatement(sql);

            p.setString(1, guncelsehir);
            p.setInt(2, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Sehir Güncellendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Sehir Güncellenemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sehir Güncellenemedi");
        }
    }

    public sehir() {

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        yazi1 = new JLabel("SehirID");
        yazi1.setBounds(60, 40, 50, 20);
        add(yazi1);

        sehirIDtxt = new JTextField(20);
        sehirIDtxt.setBounds(110, 40, 100, 20);
        add(sehirIDtxt);

        yazi = new JLabel("Sehir");
        yazi.setBounds(60, 80, 50, 20);
        add(yazi);

        sehirtxt = new JTextField(20);
        sehirtxt.setBounds(110, 80, 100, 20);
        add(sehirtxt);

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
        sehirIDtxt.setText("");
        sehirtxt.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("ekle")) {
            sehir = sehirtxt.getText();
            sehirID = Integer.parseInt(sehirIDtxt.getText());
            ekle(sehir, sehirID);
        } else if (text.equals("sil")) {
            sehirID = Integer.parseInt(sehirIDtxt.getText());
            sil(sehirID);
        } else if (text.equals("guncelle")) {
            sehir = sehirtxt.getText();
            sehirID = Integer.parseInt(sehirIDtxt.getText());
            guncelle(sehirID, sehir);
        } else if (text.equals("temizle")) {
            temizle();
        }

    }

}
