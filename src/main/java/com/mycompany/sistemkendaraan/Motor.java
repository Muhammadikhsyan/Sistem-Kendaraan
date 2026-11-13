package com.mycompany.sistemkendaraan;

public class Motor extends Kendaraan implements DapatDiAsuransikan {
    private static final long serialVersionUID = 1L;
    private int kapasitasCC;

    public Motor(String platNomor, String merk, int tahun, int kapasitasCC) throws Exception {
        super(platNomor, merk, tahun);
        if (kapasitasCC <= 0) {
            throw new Exception("Kapasitas CC motor tidak valid.");
        }
        this.kapasitasCC = kapasitasCC;
    }

    public int getKapasitasCC() {
        return kapasitasCC;
    }

    public void setKapasitasCC(int kapasitasCC) {
        this.kapasitasCC = kapasitasCC;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf(" | Tipe: Motor (%d CC)", kapasitasCC);
    }

    @Override
    public void caraServis() {
        System.out.println("    -> Servis Motor: Cek rantai/CVT, ganti oli mesin, dan tune-up injeksi.");
    }

    @Override
    public void hitungBiayaPajak() {
        System.out.println("    -> Estimasi Pajak Motor: Rp 350.000 / tahun.");
    }

    @Override
    public void prosesKlaimAsuransi() {
        System.out.println("    -> [Asuransi] Perlindungan asuransi motor terdaftar untuk risiko kehilangan.");
    }
}