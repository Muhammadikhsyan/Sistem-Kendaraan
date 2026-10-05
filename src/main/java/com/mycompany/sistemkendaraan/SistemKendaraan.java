package com.mycompany.sistemkendaraan;

import java.util.Scanner;

public class SistemKendaraan {

    public static void cariKendaraan(String platNomor, Kendaraan[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian Plat Nomor: " + platNomor + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getPlatNomor().equalsIgnoreCase(platNomor)) {
                daftar[i].tampilkanInfo();
                daftar[i].caraServis(); 
                
                if (daftar[i] instanceof DapatDiAsuransikan) {
                    ((DapatDiAsuransikan) daftar[i]).prosesKlaimAsuransi();
                }
                if (daftar[i] instanceof DapatDimodifikasi) {
                    ((DapatDimodifikasi) daftar[i]).pasangModifikasi();
                }
                
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan dengan plat tersebut tidak ditemukan.");
        }
    }

    public static void cariKendaraan(int tahun, Kendaraan[] daftar, int jumlah) {
        System.out.println("\n--- Hasil Pencarian Tahun Produksi: " + tahun + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getTahun() == tahun) {
                daftar[i].tampilkanInfo();
                daftar[i].caraServis();
                
                if (daftar[i] instanceof DapatDiAsuransikan) {
                    ((DapatDiAsuransikan) daftar[i]).prosesKlaimAsuransi();
                }
                if (daftar[i] instanceof DapatDimodifikasi) {
                    ((DapatDimodifikasi) daftar[i]).pasangModifikasi();
                }
                
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan buatan tahun tersebut tidak ditemukan.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kendaraan[] daftarKendaraan = new Kendaraan[10];
        int jumlahKendaraan = 0;
        boolean isRunning = true;

        System.out.println("===============================================");
        System.out.println("  SELAMAT DATANG DI SISTEM MANAJEMEN KENDARAAN ");
        System.out.println("===============================================");

        while (isRunning) {
            System.out.println("\n>>> MENU UTAMA <<<");
            System.out.println("1. Tambah Kendaraan Baru");
            System.out.println("2. Tampilkan Seluruh Kendaraan");
            System.out.println("3. Cari Kendaraan");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    if (jumlahKendaraan < daftarKendaraan.length) {
                        System.out.println("\n-- Pilih Tipe Kendaraan --");
                        System.out.println("1. Mobil");
                        System.out.println("2. Motor");
                        System.out.print("Pilihan (1/2): ");
                        int tipe = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Plat Nomor : ");
                        String plat = scanner.nextLine();
                        System.out.print("Masukkan Merk       : ");
                        String merk = scanner.nextLine();
                        System.out.print("Masukkan Tahun      : ");
                        int tahun = scanner.nextInt();

                        if (tipe == 1) {
                            System.out.print("Masukkan Jumlah Pintu: ");
                            int pintu = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Mobil(plat, merk, tahun, pintu);
                        } else if (tipe == 2) {
                            System.out.print("Masukkan Kapasitas CC: ");
                            int cc = scanner.nextInt();
                            scanner.nextLine();
                            daftarKendaraan[jumlahKendaraan] = new Motor(plat, merk, tahun, cc);
                        }

                        jumlahKendaraan++;
                        System.out.println("--> Kendaraan berhasil ditambahkan!");
                    } else {
                        System.out.println("Maaf, kapasitas penyimpanan penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n================ DAFTAR KENDARAAN ================");
                    if (jumlahKendaraan == 0) {
                        System.out.println("Belum ada data kendaraan yang tersimpan.");
                    } else {
                        for (int i = 0; i < jumlahKendaraan; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarKendaraan[i].tampilkanInfo();
                            daftarKendaraan[i].caraServis();
                            
                            if (daftarKendaraan[i] instanceof DapatDiAsuransikan) {
                                ((DapatDiAsuransikan) daftarKendaraan[i]).prosesKlaimAsuransi();
                            }
                            if (daftarKendaraan[i] instanceof DapatDimodifikasi) {
                                ((DapatDimodifikasi) daftarKendaraan[i]).pasangModifikasi();
                            }
                            System.out.println("--------------------------------------------------");
                        }
                        System.out.println("Total unit terdaftar di sistem: " + Kendaraan.totalKendaraan);
                    }
                    break;

                case 3:
                    if (jumlahKendaraan == 0) {
                        System.out.println("\nBelum ada data kendaraan untuk dicari.");
                    } else {
                        System.out.println("\n-- Cari Berdasarkan --");
                        System.out.println("1. Plat Nomor (String)");
                        System.out.println("2. Tahun Produksi (Angka)");
                        System.out.print("Pilihan (1/2): ");
                        int modeCari = scanner.nextInt();
                        scanner.nextLine();

                        if (modeCari == 1) {
                            System.out.print("Masukkan Plat Nomor: ");
                            String platCari = scanner.nextLine();
                            cariKendaraan(platCari, daftarKendaraan, jumlahKendaraan);
                        } else if (modeCari == 2) {
                            System.out.print("Masukkan Tahun Produksi: ");
                            int tahunCari = scanner.nextInt();
                            scanner.nextLine();
                            cariKendaraan(tahunCari, daftarKendaraan, jumlahKendaraan);
                        } else {
                            System.out.println("Pilihan mode tidak valid.");
                        }
                    }
                    break;

                case 4:
                    isRunning = false;
                    System.out.println("\nTerima kasih telah menggunakan Sistem Manajemen Kendaraan.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        }
        scanner.close();
    }
}