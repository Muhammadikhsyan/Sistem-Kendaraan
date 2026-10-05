package com.mycompany.sistemkendaraan;

public class Mobil extends Kendaraan implements DapatDiAsuransikan, DapatDimodifikasi {
    private int jumlahPintu;

    public Mobil(String platNomor, String merk, int tahun, int jumlahPintu) {
        super(platNomor, merk, tahun);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf(" | Tipe: Mobil (%d Pintu)%n", jumlahPintu);
    }

    @Override
    public void caraServis() {
        System.out.println("   -> Servis Mobil: Ganti oli mesin, cek rem, dan ganti filter AC.");
    }

    @Override
    public void prosesKlaimAsuransi() {
        System.out.println("   -> [Asuransi] Polis asuransi mobil aktif dan klaim perbaikan disetujui.");
    }

    @Override
    public void pasangModifikasi() {
        System.out.println("   -> [Modifikasi] Body kit sport dan velg racing berhasil dipasang pada mobil.");
    }
}