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
public class yakit extends JPanel implements ActionListener {

    static Connection con;
    static Statement st;
    String yakit;
    int yakitID;
    JTextField yakittxt, yakitIDtxt;
    JButton eklebut, silbut, guncellebut, temizle;
    JLabel yazi, yazi1;
    JTable tablo;
    JScrollPane sp;

    public void verilerigoster() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM yakitturu ";
            ResultSet sonuc = st.executeQuery(sql);
            TabloYakit tbl = new TabloYakit(sonuc);

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
            JOptionPane.showMessageDialog(null, "Yakıtlar Gösterilemiyor");
        }
    }

    public void guncelle() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = "SELECT * FROM yakitturu ";
            ResultSet sonuc = st.executeQuery(sql);
            TabloYakit tbl = new TabloYakit(sonuc);

            tablo.setModel(tbl);
            add(tablo);

            add(sp);
            sp.setViewportView(tablo);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Yakıtlar Gösterilemiyor");
        }
    }

    public void ekle(String yakit, int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            String sql = "INSERT INTO yakitturu VALUES(?,?)";
            PreparedStatement p1 = con.prepareStatement(sql);

            p1.setInt(1, ID);
            p1.setString(2, yakit);

            int sonuc = p1.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Yakıt Eklendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Yakıt Eklenemedi");
            }

            p1.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Yakıt Eklenemedi");
        }
    }

    public void sil(int ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "DELETE from yakitturu Where yakitturuID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Yakıt Silindi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Yakıt Silinemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Yakıt Silinemedi");
        }
    }

    public void guncelle(int ID, String guncelyakit) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            String sql = "UPDATE yakitturu SET yakitturu=? WHERE yakitturuID=?";
            PreparedStatement p = con.prepareStatement(sql);

            p.setString(1, guncelyakit);
            p.setInt(2, ID);

            int sonuc = p.executeUpdate();
            if (sonuc == 1) {
                JOptionPane.showMessageDialog(null, "Yakıt Güncellendi");
                guncelle();
            } else {
                JOptionPane.showMessageDialog(null, "Yakıt Güncellenemedi");
            }
            p.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Yakıt Güncellenemedi");
        }
    }

    public yakit() {
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        yazi1 = new JLabel("YakitID");
        yazi1.setBounds(60, 40, 50, 20);
        add(yazi1);

        yakitIDtxt = new JTextField(20);
        yakitIDtxt.setBounds(110, 40, 100, 20);
        add(yakitIDtxt);

        yazi = new JLabel("Yakit");
        yazi.setBounds(60, 80, 50, 20);
        add(yazi);

        yakittxt = new JTextField(20);
        yakittxt.setBounds(110, 80, 100, 20);
        add(yakittxt);

        eklebut = new JButton("EKLE");
        eklebut = new JButton("EKLE");
        eklebut.setActionCommand("ekle");
        eklebut.addActionListener(this);
        eklebut.setBackground(Color.decode("#7ec0ee"));
        eklebut.setBounds(90, 140, 100, 20);
        add(eklebut);

        silbut = new JButton("SİL");
        silbut.setActionCommand("sil");
        silbut.addActionListener(this);
        silbut.setBackground(Color.decode("#7ec0ee"));
        silbut.setBounds(90, 180, 100, 20);
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
        yakitIDtxt.setText("");
        yakittxt.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String text = e.getActionCommand();

        if (text.equals("ekle")) {
            yakit = yakittxt.getText();
            yakitID = Integer.parseInt(yakitIDtxt.getText());
            ekle(yakit, yakitID);
        } else if (text.equals("sil")) {
            yakitID = Integer.parseInt(yakitIDtxt.getText());
            sil(yakitID);
        } else if (text.equals("guncelle")) {
            yakit = yakittxt.getText();
            yakitID = Integer.parseInt(yakitIDtxt.getText());
            guncelle(yakitID, yakit);
        } else if (text.equals("temizle")) {
            temizle();
        }

    }
}
