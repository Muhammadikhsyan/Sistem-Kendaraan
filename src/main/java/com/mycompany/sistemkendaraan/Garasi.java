package com.mycompany.sistemkendaraan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Garasi {
    private String namaGarasi;
    private RuangPenyimpanan ruangInternal;
    private ArrayList<Kendaraan> daftarKendaraan;
    private HashMap<String, Pemilik> dataPemilik;

    public Garasi(String namaGarasi) {
        this.namaGarasi = namaGarasi;
        this.ruangInternal = new RuangPenyimpanan("Area Parkir Utama", 15);
        this.daftarKendaraan = new ArrayList<>();
        this.dataPemilik = new HashMap<>();
    }

    public void bukaGarasi() {
        System.out.println("\n*** Menginisialisasi sistem " + namaGarasi + " ***");
        ruangInternal.infoRuang();
    }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("--> Kendaraan berhasil ditambahkan ke garasi!");
    }

    public void tampilkanSemuaKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada data kendaraan yang tersimpan.");
        } else {
            for (int i = 0; i < daftarKendaraan.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarKendaraan.get(i).tampilkanInfo();
                daftarKendaraan.get(i).caraServis();
                daftarKendaraan.get(i).hitungBiayaPajak();

                if (daftarKendaraan.get(i) instanceof DapatDiAsuransikan) {
                    ((DapatDiAsuransikan) daftarKendaraan.get(i)).prosesKlaimAsuransi();
                }
                if (daftarKendaraan.get(i) instanceof DapatDimodifikasi) {
                    ((DapatDimodifikasi) daftarKendaraan.get(i)).pasangModifikasi();
                }
                System.out.println("--------------------------------------------------");
            }
            System.out.println("Total unit terdaftar di sistem: " + Kendaraan.totalKendaraan);
        }
    }

    public void cariKendaraan(String platNomor) throws KendaraanTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian Plat Nomor: " + platNomor + " ---");
        boolean ditemukan = false;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getPlatNomor().equalsIgnoreCase(platNomor)) {
                k.tampilkanInfo();
                k.caraServis();
                k.hitungBiayaPajak();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Kendaraan dengan plat '" + platNomor + "' tidak ditemukan.");
        }
    }

    public void cariKendaraan(int tahun) throws KendaraanTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian Tahun Produksi: " + tahun + " ---");
        boolean ditemukan = false;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getTahun() == tahun) {
                k.tampilkanInfo();
                k.caraServis();
                k.hitungBiayaPajak();
                System.out.println();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KendaraanTidakDitemukanException("Gagal: Kendaraan buatan tahun " + tahun + " tidak ditemukan.");
        }
    }

    public void tambahPemilik(String nik, Pemilik p) {
        dataPemilik.put(nik, p);
        System.out.println("--> Data pemilik berhasil didaftarkan.");
    }

    public void cariPemilik(String nik) {
        if (dataPemilik.containsKey(nik)) {
            Pemilik p = dataPemilik.get(nik);
            System.out.println("Ditemukan! Nama Pemilik: " + p.getNama() + " (NIK: " + p.getNik() + ")");
        } else {
            System.out.println("Pemilik dengan NIK tersebut tidak terdaftar.");
        }
    }

    public void tampilkanSemuaPemilik() {
        System.out.println("Total Pemilik Terdaftar: " + dataPemilik.size());
        for (String nik : dataPemilik.keySet()) {
            System.out.println("- NIK: " + nik + " | Nama: " + dataPemilik.get(nik).getNama());
        }
    }

    public void simpanDataKeFile() {
        try (FileOutputStream fileKeluar = new FileOutputStream("data_garasi.dat");
             ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar)) {
            
            mesinPembeku.writeObject(this.daftarKendaraan);
            mesinPembeku.writeObject(this.dataPemilik);
            System.out.println("-> [SYSTEM] Seluruh data garasi berhasil disimpan ke Harddisk (Serialization).");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_garasi.dat");
            if (!cekFile.exists()) return; 

            try (FileInputStream fileMasuk = new FileInputStream(cekFile);
                 ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk)) {
                
                this.daftarKendaraan = (ArrayList<Kendaraan>) mesinPembangkit.readObject();
                this.dataAnggota = (HashMap<String, Pemilik>) mesinPembangkit.readObject(); 
                Kendaraan.totalKendaraan = this.daftarKendaraan.size();
                System.out.println("-> [SYSTEM] Data sistem berhasil dimuat ulang dari penyimpanan.");
            }
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }

    public void bacaLogServis() {
        System.out.println("\n--- Riwayat Log Servis Kendaraan ---");
        try {
            File fileLog = new File("log_servis.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada log riwayat servis.");
                return;
            }
            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log servis.");
        }
    }

    public int getJumlahKendaraan() {
        return daftarKendaraan.size();
    }

    public Kendaraan getKendaraanInt(int index) {
        return daftarKendaraan.get(index);
    }

    private HashMap<String, Pemilik> dataAnggota = new HashMap<>(); 
}