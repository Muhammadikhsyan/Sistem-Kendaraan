package com.mycompany.sistemkendaraan;

import java.io.Serializable;

public class Pemilik implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nik;
    private String nama;

    public Pemilik(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }
}

class RuangPenyimpanan implements Serializable {
    private static final long serialVersionUID = 1L;
    private String namaRuang;
    private int kapasitasMax;

    public RuangPenyimpanan(String namaRuang, int kapasitasMax) {
        this.namaRuang = namaRuang;
        this.kapasitasMax = kapasitasMax;
    }

    public void infoRuang() {
        System.out.println("-> Komponen Internal: " + namaRuang + " (Kapasitas: " + kapasitasMax + " Unit)");
    }
}