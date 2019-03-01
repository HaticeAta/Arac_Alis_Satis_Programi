/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Proje extends JFrame implements ActionListener {

    JButton kisim1, kisim2, cikis;
    JLabel yazi, yazi1;

    public Proje() {
        super("ARAÇ ALIŞ-SATIŞ PROGRAMI");
        Container con = getContentPane();
        con.setLayout(null);
        con.setBackground(Color.LIGHT_GRAY);

        kisim1 = new JButton("EKLEME - SİLME - GÜNCELLEME");
        kisim1.setActionCommand("buton1");
        kisim1.addActionListener(this);
        kisim1.setBounds(30, 30, 330, 30);
        kisim1.setBackground(Color.decode("#7ec0ee"));
        con.add(kisim1);

        kisim2 = new JButton("İLAN SORGULAMA");
        kisim2.setActionCommand("buton2");
        kisim2.addActionListener(this);
        kisim2.setBounds(30, 80, 330, 30);
        kisim2.setBackground(Color.decode("#7ec0ee"));
        con.add(kisim2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("buton1")) {

            birinci bir = new birinci();

        } else if (text.equals("buton2")) {

            ikinci iki = new ikinci();

        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Proje cerceve = new Proje();
                    cerceve.setSize(400, 190);
                    cerceve.setLocation(400, 200);
                    cerceve.setVisible(true);
                    cerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (Exception e) {
                    System.out.println("Hata");
                }
            }
        });
    }

}
