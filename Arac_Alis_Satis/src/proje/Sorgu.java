/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

/**
 *
 * @author Hatice
 */
public class Sorgu {

    public static String sorgu;
    public static String sirala = " asc ";

    public static String ilanadisorgu, fiyatsorgu, kmsorgu, tarihsorgu, sehirsorgu, markasorgu, modelsorgu, vitessorgu, yakitsorgu, renksorgu;
    public static String baslangickismi, bitiskismi, siralamakismi;

    public static String sorguolustur(String ilanadi, int fiyatmin, int fiyatmax, int kmmin, int kmmax, String ilantarihi,
            String sehir, String marka, String model, String vites, String yakit, String renk, String siralamaturu) {

        baslangickismi = sorgubaslangic();
        bitiskismi = sorgubitis();

        if (ilanadi != null) {
            ilanadisorgu = ilanadisorguolustur(ilanadi);
        } else {
            ilanadisorgu = " ";
        }

        fiyatsorgu = fiyatsorguolustur(fiyatmin, fiyatmax);

        kmsorgu = kmsorguolustur(kmmin, kmmax);

        if (ilantarihi != null) {
            tarihsorgu = ilantarihisorguolustur(ilantarihi);
        } else {
            tarihsorgu = " ";
        }

        if (sehir != null) {
            sehirsorgu = sehirsorguolustur(sehir);
        } else {
            sehirsorgu = " ";
        }

        if (marka != null) {
            markasorgu = markasorguolustur(marka);
        } else {
            markasorgu = " ";
        }

        if (model != null) {
            modelsorgu = modelsorguolustur(model);
        } else {
            modelsorgu = " ";
        }

        if (vites != null) {
            vitessorgu = vitessorguolustur(vites);
        } else {
            vitessorgu = " ";
        }

        if (yakit != null) {
            yakitsorgu = yakitsorguolustur(yakit);
        } else {
            yakitsorgu = " ";
        }

        if (renk != null) {
            renksorgu = renksorguolustur(renk);
        } else {
            renksorgu = " ";
        }

        if (siralamaturu != null) {
            siralamakismi = sorgusiralama(siralamaturu);
        } else if (siralamaturu == null) {
            siralamakismi = " ";
        }

        sorgu = baslangickismi + ilanadisorgu + fiyatsorgu + kmsorgu + tarihsorgu + sehirsorgu + markasorgu
                + modelsorgu + vitessorgu + yakitsorgu + renksorgu + bitiskismi + siralamakismi;
        return sorgu;
    }

    public static String ilanadisorguolustur(String ad) {
        String sorgu_ilan = " ilan.`ilanAdi` = '"
                + ad
                + "' and ";

        return sorgu_ilan;
    }

    public static String fiyatsorguolustur(int min, int max) {
        String sorgu_fiyat;
        if (min != -1 && max == -1) {
            sorgu_fiyat = " ilan.`ilanFiyat`>= " + min + " and ";
        } else if (min == -1 && max != -1) {
            sorgu_fiyat = " ilan.`ilanFiyat`<= " + max + " and ";
        } else if (min != -1 && max != -1) {
            sorgu_fiyat = " (ilan.`ilanFiyat` between "
                    + min
                    + " and "
                    + max
                    + ") and ";
        } else {
            sorgu_fiyat = " ";
        }
        return sorgu_fiyat;
    }

    public static String kmsorguolustur(int min, int max) {
        String sorgu_km;
        if (min != -1 && max == -1) {
            sorgu_km = " ilan.`ilanKM` >= " + min + " and ";
        } else if (min == -1 && max != -1) {
            sorgu_km = " ilan.`ilanKM` <= " + max + " and ";
        } else if (min != -1 && max != -1) {
            sorgu_km = " ( ilan.`ilanKM` between "
                    + min
                    + " and "
                    + max
                    + ") and ";
        } else {
            sorgu_km = " ";
        }
        return sorgu_km;
    }

    public static String ilantarihisorguolustur(String tarih) {
        String sorgu_tarih = " ilan.`ilanTarih`='"
                + tarih
                + "' and ";

        return sorgu_tarih;
    }

    public static String sehirsorguolustur(String sehir) {
        String sorgu_sehir = " sehir.sehir = '"
                + sehir
                + "' and ";

        return sorgu_sehir;
    }

    public static String markasorguolustur(String marka) {
        String sorgu_marka = " ilan.`ilanArabaID` IN (select arabaID from araba where\n"
                + "arabaMarka='"
                + marka
                + "'\n"
                + ") and ";

        return sorgu_marka;
    }

    public static String modelsorguolustur(String model) {
        String sorgu_model = " ilan.`ilanArabaID` IN (select arabaID from araba where\n"
                + "arabaModel='"
                + model
                + "'\n"
                + ") and ";

        return sorgu_model;
    }

    public static String vitessorguolustur(String vites) {
        String sorgu_vites = " ilan.ilanArabaID IN ( select arabaID from araba where \n"
                + "araba.`arabaVitesTuruID`= (select vitesturu.vitesturuID from vitesturu where vitesturu='"
                + vites
                + "'))\n"
                + "and ";

        return sorgu_vites;
    }

    public static String yakitsorguolustur(String yakit) {
        String sorgu_yakit = " ilan.ilanArabaID IN ( select arabaID from araba where \n"
                + "araba.arabaYakitTuruID = (select yakitturu.yakitturuID from yakitturu where yakitturu='"
                + yakit
                + "'))\n"
                + "and \n"
                + " ";

        return sorgu_yakit;
    }

    public static String renksorguolustur(String renk) {
        String sorgu_renk = " renk.renk = '"
                + renk
                + "' and ";

        return sorgu_renk;
    }

    public static String sorgubaslangic() {
        String baslangıc = "select ilan.`ilanAdi`,ilan.`ilanFiyat`,ilan.`ilanKM`,ilan.`ilanTarih`,\n"
                + "sehir.sehir,araba.`arabaMarka`,araba.`arabaModel`,vitesturu.vitesturu,yakitturu.yakitturu,renk.renk\n"
                + "\n"
                + "from ilan , sehir ,araba,vitesturu, renk ,yakitturu \n"
                + "\n"
                + "where (\n"
                + "\n"
                + "";

        return baslangıc;
    }

    public static String sorgubitis() {
        String bitis = "\n"
                + "araba.`arabaYakitTuruID`=yakitturu.`yakitturuID`\n"
                + "and araba.`arabaVitesTuruID`=vitesturu.vitesturuID\n"
                + "and ilan.`ilanSehirID`=sehir.`sehirID`\n"
                + "and ilan.`ilanArabaID`=araba.`arabaID`\n"
                + "and araba.`arabaRenkID`=renk.`renkID`\n"
                + ")\n"
                + "\n"
                + " ";

        return bitis;
    }

    public static String sorgusiralama(String tur) {
        String siralama = " ORDER BY " + tur + sirala;
        return siralama;
    }
}
