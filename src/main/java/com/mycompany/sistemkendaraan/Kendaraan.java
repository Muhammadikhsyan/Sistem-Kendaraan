package com.mycompany.sistemkendaraan;

public abstract class Kendaraan {
    private String platNomor;
    private String merk;
    private int tahun;
    
    public static int totalKendaraan = 0;

    public Kendaraan(String platNomor, String merk, int tahun) {
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
}