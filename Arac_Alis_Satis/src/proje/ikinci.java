/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Hatice
 */
public class ikinci extends JFrame implements ActionListener {

    static Connection con;
    static Statement st;

    JComboBox ilanadibox, ilantarihbox, sehirbox, markabox, modelbox, vitesbox, yakitbox, renkbox, siralamabox;
    JButton sorgulamabuton, artansıralamabut, azalansıralamabut;
    JScrollPane scroll;
    JTable sonuctablosu;
    JLabel yazi1, yazi2, yazi3, yazi4, yazi5, yazi6, yazi7, yazi8, yazi9, yazi10, yazi11, yazi12, yazisira;
    JTextField fiyatmaxtxt, fiyatmintxt, kmmaxtxt, kmmintxt, sorgusayisi;
    public String ilanadi, ilantarihi, sehir, marka, model, vites, yakit, renk, siralamaturu, siralama;
    public int fiyatmax, fiyatmin, kmmax, kmmin;

    public ikinci() {

        super("İLAN SORGULAMA");
        Container con = getContentPane();
        con.setLayout(null);
        con.setBackground(Color.decode("#7ec0ee"));

        Label();
        TextField();

        ilanAdi();
        ilanTarihi();
        sehir();
        marka();
        model();
        vites();
        yakit();
        renk();

        buton();
        tablo();
        siralamaturu();
        setVisible(true);
        setLocation(80, 100);
        setSize(1215, 435);

    }

    public String tumkayitlar() {
        String sorgu = "select ilan.`ilanAdi`,ilan.`ilanFiyat`,ilan.`ilanKM`,ilan.`ilanTarih`,\n"
                + "sehir.sehir,araba.`arabaMarka`,araba.`arabaModel`,vitesturu.vitesturu,yakitturu.yakitturu,renk.renk\n"
                + "\n"
                + "from ilan , sehir ,araba,vitesturu, renk ,yakitturu \n"
                + "\n"
                + "where (\n"
                + "ilan.`ilanArabaID`=araba.`arabaID`\n"
                + "and ilan.`ilanSehirID`=sehir.`sehirID`\n"
                + "and araba.`arabaYakitTuruID`=yakitturu.`yakitturuID`\n"
                + "and araba.`arabaVitesTuruID`=vitesturu.vitesturuID\n"
                + "and araba.`arabaRenkID`=renk.`renkID`\n"
                + ")\n"
                + " "
                + " ORDER BY arabaMarka,arabaModel";

        return sorgu;
    }

    public int sorgusayisi(String sorgu) {
        int satirsayisi = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = sorgu;
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                satirsayisi++;
            }

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SORGU SAYISI BULMA HATASI");
        }

        return satirsayisi;
    }

    public void yaziolustur(int sayi) {

        sorgusayisi = new JTextField("   " + sayi + " Tane Kayıt Bulunmuştur");
        sorgusayisi.setBounds(270, 260, 177, 20);
        add(sorgusayisi);
    }

    public void yenisorgu(String sorgu) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = sorgu;
            ResultSet sonuc = st.executeQuery(sql);

            SorguTablosu sorgutable = new SorguTablosu(sonuc);

            sonuctablosu.setModel(sorgutable);
            add(sonuctablosu);

            add(scroll);
            scroll.setViewportView(sonuctablosu);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "YENI SORGU HATASI");
        }

        yaziolustur(sorgusayisi(sorgu));
    }

    public void tablo() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");

            Statement st = con.createStatement();
            String sql = tumkayitlar();
            ResultSet sonuc = st.executeQuery(sql);

            SorguTablosu sorgutable = new SorguTablosu(sonuc);

            sonuctablosu = new JTable();
            sonuctablosu.setBounds(270, 20, 900, 230);

            scroll = new JScrollPane();
            scroll.setBounds(270, 20, 900, 230);

            sonuctablosu.setModel(sorgutable);
            add(sonuctablosu);

            add(scroll);
            scroll.setViewportView(sonuctablosu);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sonuçlar Gösterilemiyor");
        }
    }

    public void TextField() {

        fiyatmintxt = new JTextField();
        fiyatmintxt.setBounds(130, 50, 110, 20);
        add(fiyatmintxt);

        fiyatmaxtxt = new JTextField();
        fiyatmaxtxt.setBounds(130, 80, 110, 20);
        add(fiyatmaxtxt);

        kmmintxt = new JTextField();
        kmmintxt.setBounds(130, 110, 110, 20);
        add(kmmintxt);

        kmmaxtxt = new JTextField();
        kmmaxtxt.setBounds(130, 140, 110, 20);
        add(kmmaxtxt);

    }

    public void Label() {
        yazi1 = new JLabel("Ilan Adı");
        yazi1.setBounds(30, 20, 100, 20);
        add(yazi1);
        yazi2 = new JLabel("Minimum  Fiyat");
        yazi2.setBounds(30, 50, 100, 20);
        add(yazi2);
        yazi3 = new JLabel("Maximum Fiyat");
        yazi3.setBounds(30, 80, 100, 20);
        add(yazi3);
        yazi4 = new JLabel("Minimum  KM");
        yazi4.setBounds(30, 110, 100, 20);
        add(yazi4);
        yazi5 = new JLabel("Maximum KM");
        yazi5.setBounds(30, 140, 100, 20);
        add(yazi5);
        yazi6 = new JLabel("Ilan Tarihi");
        yazi6.setBounds(30, 170, 100, 20);
        add(yazi6);
        yazi7 = new JLabel("Sehir");
        yazi7.setBounds(30, 200, 100, 20);
        add(yazi7);
        yazi8 = new JLabel("Marka");
        yazi8.setBounds(30, 230, 100, 20);
        add(yazi8);
        yazi9 = new JLabel("Model");
        yazi9.setBounds(30, 260, 100, 20);
        add(yazi9);
        yazi10 = new JLabel("Vites");
        yazi10.setBounds(30, 290, 100, 20);
        add(yazi10);
        yazi11 = new JLabel("Yakit");
        yazi11.setBounds(30, 320, 100, 20);
        add(yazi11);
        yazi12 = new JLabel("Renk");
        yazi12.setBounds(30, 350, 100, 20);
        add(yazi12);

        yazisira = new JLabel("Sıralama Türünü Seçiniz . . .");
        yazisira.setBounds(560, 280, 200, 25);
        add(yazisira);
    }

    public void buton() {

        sorgulamabuton = new JButton("Sorgula");
        sorgulamabuton.setActionCommand("sorgula");
        sorgulamabuton.addActionListener(this);
        sorgulamabuton.setBounds(295, 300, 150, 30);
        sorgulamabuton.setBackground(Color.LIGHT_GRAY);
        add(sorgulamabuton);

        artansıralamabut = new JButton("Artan Sıralama");
        artansıralamabut.setActionCommand("artan");
        artansıralamabut.addActionListener(this);
        artansıralamabut.setBounds(910, 280, 150, 25);
        artansıralamabut.setBackground(Color.LIGHT_GRAY);
        add(artansıralamabut);

        azalansıralamabut = new JButton("Azalan Sıralama");
        azalansıralamabut.setActionCommand("azalan");
        azalansıralamabut.addActionListener(this);
        azalansıralamabut.setBounds(910, 325, 150, 25);
        azalansıralamabut.setBackground(Color.LIGHT_GRAY);
        add(azalansıralamabut);
    }

    public void siralamaturu() {
        siralamabox = new JComboBox();
        siralamabox.setModel(new DefaultComboBoxModel(new String[]{"   "}));

        siralamabox.addItem("ilanAdi");
        siralamabox.addItem("ilanFiyat");
        siralamabox.addItem("ilanKM");
        siralamabox.addItem("ilanTarih");
        siralamabox.addItem("sehir");
        siralamabox.addItem("arabaMarka");
        siralamabox.addItem("arabaModel");
        siralamabox.addItem("vitesturu");
        siralamabox.addItem("yakitturu");
        siralamabox.addItem("renk");

        siralamabox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    siralamaturu = null;
                } else {
                    siralamaturu = sonuc;
                }
            }
        });
        siralamabox.setBounds(730, 280, 150, 25);
        add(siralamabox);
    }

    public void ilanAdi() {
        ilanadibox = new JComboBox();
        ilanadibox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT ilanAdi from ilan";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                ilanadibox.addItem(sonuc.getObject("ilanAdi"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        ilanadibox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    ilanadi = null;
                } else {
                    ilanadi = sonuc;
                }
            }
        });
        ilanadibox.setBounds(130, 20, 110, 20);
        add(ilanadibox);
    }

    public void ilanTarihi() {
        ilantarihbox = new JComboBox();
        ilantarihbox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT ilanTarih from ilan";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                ilantarihbox.addItem(sonuc.getObject("ilanTarih"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        ilantarihbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    ilantarihi = null;
                } else {
                    ilantarihi = sonuc;
                }
            }
        });
        ilantarihbox.setBounds(130, 170, 110, 20);
        add(ilantarihbox);
    }

    public void sehir() {
        sehirbox = new JComboBox();
        sehirbox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT sehir from sehir";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                sehirbox.addItem(sonuc.getObject("sehir"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        sehirbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    sehir = null;
                } else {
                    sehir = sonuc;
                }
            }
        });
        sehirbox.setBounds(130, 200, 110, 20);
        add(sehirbox);
    }

    public void marka() {
        markabox = new JComboBox();
        markabox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "select DISTINCT arabaMarka from araba";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                markabox.addItem(sonuc.getObject("arabaMarka"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        markabox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    marka = null;
                    modelguncelle(marka);
                } else {
                    marka = sonuc;
                    modelguncelle(marka);
                }
            }
        });

        markabox.setBounds(130, 230, 110, 20);
        add(markabox);
    }

    public void modelguncelle(String mrk) {
        for (int i = modelbox.getItemCount() - 1; i >= 1; i--) {
            modelbox.removeItemAt(i);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT arabaModel from araba where arabaMarka='"
                    + mrk + "'";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                modelbox.addItem(sonuc.getObject("arabaModel"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }
    }

    public void model() {
        modelbox = new JComboBox();
        modelbox.setModel(new DefaultComboBoxModel(new String[]{"   "}));

        modelbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    model = null;
                } else {
                    model = sonuc;
                }
            }
        });

        modelbox.setBounds(130, 260, 110, 20);
        add(modelbox);
    }

    public void vites() {
        vitesbox = new JComboBox();
        vitesbox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT vitesturu from vitesturu";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                vitesbox.addItem(sonuc.getObject("vitesturu"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        vitesbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    vites = null;
                } else {
                    vites = sonuc;
                }
            }
        });
        vitesbox.setBounds(130, 290, 110, 20);
        add(vitesbox);
    }

    public void yakit() {
        yakitbox = new JComboBox();
        yakitbox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT yakitturu from yakitturu";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                yakitbox.addItem(sonuc.getObject("yakitturu"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        yakitbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    yakit = null;
                } else {
                    yakit = sonuc;
                }
            }
        });
        yakitbox.setBounds(130, 320, 110, 20);
        add(yakitbox);
    }

    public void renk() {
        renkbox = new JComboBox();
        renkbox.setModel(new DefaultComboBoxModel(new String[]{"   "}));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeveritabani", "root", "12345");
            Statement st = con.createStatement();
            String sql = "SELECT DISTINCT renk from renk";
            ResultSet sonuc = st.executeQuery(sql);

            while (sonuc.next()) {
                renkbox.addItem(sonuc.getObject("renk"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata");
        }

        renkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sonuc = (((JComboBox) ae.getSource()).getSelectedItem()).toString();
                if (sonuc.equals("   ")) {
                    renk = null;
                } else {
                    renk = sonuc;
                }
            }
        });
        renkbox.setBounds(130, 350, 110, 20);
        add(renkbox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("sorgula")) {
            if (!fiyatmintxt.getText().equals("")) {
                fiyatmin = Integer.parseInt(fiyatmintxt.getText());
            } else {
                fiyatmin = -1;
            }

            if (!fiyatmaxtxt.getText().equals("")) {
                fiyatmax = Integer.parseInt(fiyatmaxtxt.getText());
            } else {
                fiyatmax = -1;
            }

            if (!kmmintxt.getText().equals("")) {
                kmmin = Integer.parseInt(kmmintxt.getText());
            } else {
                kmmin = -1;
            }

            if (!kmmaxtxt.getText().equals("")) {
                kmmax = Integer.parseInt(kmmaxtxt.getText());
            } else {
                kmmax = -1;
            }
            yenisorgu(Sorgu.sorguolustur(ilanadi, fiyatmin, fiyatmax, kmmin, kmmax, ilantarihi, sehir, marka, model, vites, yakit, renk, siralamaturu));
        } else if (text.equals("artan")) {
            Sorgu.sirala = " asc ";
            yenisorgu(Sorgu.sorguolustur(ilanadi, fiyatmin, fiyatmax, kmmin, kmmax, ilantarihi, sehir, marka, model, vites, yakit, renk, siralamaturu));
        } else if (text.equals("azalan")) {
            Sorgu.sirala = " desc ";
            yenisorgu(Sorgu.sorguolustur(ilanadi, fiyatmin, fiyatmax, kmmin, kmmax, ilantarihi, sehir, marka, model, vites, yakit, renk, siralamaturu));
        }
    }

}
