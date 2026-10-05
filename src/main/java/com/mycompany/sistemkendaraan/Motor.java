package com.mycompany.sistemkendaraan;

public class Motor extends Kendaraan implements DapatDiAsuransikan {
    private int kapasitasCC;

    public Motor(String platNomor, String merk, int tahun, int kapasitasCC) {
        super(platNomor, merk, tahun);
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
        System.out.printf(" | Tipe: Motor (%d CC)%n", kapasitasCC);
    }

    @Override
    public void caraServis() {
        System.out.println("   -> Servis Motor: Cek rantai/CVT, ganti oli mesin, dan tune-up injeksi.");
    }

    @Override
    public void prosesKlaimAsuransi() {
        System.out.println("   -> [Asuransi] Perlindungan asuransi motor terdaftar untuk risiko kehilangan.");
    }
}