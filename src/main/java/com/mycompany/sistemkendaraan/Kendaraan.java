package com.mycompany.sistemkendaraan;

import java.io.Serializable;

public abstract class Kendaraan implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String platNomor;
    private String merk;
    private int tahun;
    
    public static int totalKendaraan = 0;

    public Kendaraan(String platNomor, String merk, int tahun) throws Exception {
        if (tahun < 1900) {
            throw new Exception("Tahun produksi tidak valid (terlalu tua atau negatif).");
        }
        this.platNomor = platNomor;
        this.merk = merk;
        this.tahun = tahun;
        totalKendaraan++;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        if (tahun > 1900) {
            this.tahun = tahun;
        }
    }

    public void tampilkanInfo() {
        System.out.printf("Plat: %-10s | Merk: %-12s | Tahun: %d", platNomor, merk, tahun);
    }

    public abstract void caraServis();
    public abstract void hitungBiayaPajak();
}