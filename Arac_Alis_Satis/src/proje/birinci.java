/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Hatice
 */
public class birinci extends JFrame{
    public birinci(){
        super("VERİ EKLEME - SİLME - GÜNCELLEME");
        Container con = getContentPane();
        
        JTabbedPane sekmelipano = new JTabbedPane();
        sekmelipano.setBackground(Color.LIGHT_GRAY);
        sekmelipano.addTab("İLAN", new ilan());
        sekmelipano.addTab("ARABA", new araba());
        sekmelipano.addTab("YAKIT", new yakit());
        sekmelipano.addTab("VITES", new vites());
        sekmelipano.addTab("RENK", new renk());
        sekmelipano.addTab("SEHIR", new sehir());
        con.add(sekmelipano);
        setLocation(220,80);
        
        setSize(800,520);
        setVisible(true);   
    }
}
