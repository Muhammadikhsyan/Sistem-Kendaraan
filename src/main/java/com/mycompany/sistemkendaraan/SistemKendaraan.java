package com.mycompany.sistemkendaraan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemKendaraan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Garasi garasiUtama = new Garasi("Garasi Pusat Unila");
        garasiUtama.bukaGarasi();

        garasiUtama.muatDataDariFile();

        boolean isRunning = true;

        System.out.println("===============================================");
        System.out.println("   SELAMAT DATANG DI SISTEM MANAJEMEN KENDARAAN ");
        System.out.println("===============================================");

        while (isRunning) {
            System.out.println("\n>>> MENU UTAMA <<<");
            System.out.println("1. Tambah Kendaraan Baru");
            System.out.println("2. Tampilkan Seluruh Kendaraan");
            System.out.println("3. Cari Kendaraan");
            System.out.println("4. Manajemen Pemilik");
            System.out.println("5. Booking Layanan Servis (Asosiasi)");
            System.out.println("6. Lihat File Log Servis (File I/O Teks)");
            System.out.println("7. Simpan & Keluar (Serialization)");
            System.out.print("Pilih menu (1-7): ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
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
                            garasiUtama.tambahKendaraan(new Mobil(plat, merk, tahun, pintu));
                        } else if (tipe == 2) {
                            System.out.print("Masukkan Kapasitas CC: ");
                            int cc = scanner.nextInt();
                            scanner.nextLine();
                            garasiUtama.tambahKendaraan(new Motor(plat, merk, tahun, cc));
                        } else {
                            System.out.println("Tipe kendaraan tidak valid!");
                        }
                        break;

                    case 2:
                        System.out.println("\n================ DAFTAR KENDARAAN ================");
                        garasiUtama.tampilkanSemuaKendaraan();
                        break;

                    case 3:
                        System.out.println("\n-- Cari Berdasarkan --");
                        System.out.println("1. Plat Nomor (String)");
                        System.out.println("2. Tahun Produksi (Angka)");
                        System.out.print("Pilihan (1/2): ");
                        int modeCari = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            if (modeCari == 1) {
                                System.out.print("Masukkan Plat Nomor: ");
                                String platCari = scanner.nextLine();
                                garasiUtama.cariKendaraan(platCari);
                            } else if (modeCari == 2) {
                                System.out.print("Masukkan Tahun Produksi: ");
                                int tahunCari = scanner.nextInt();
                                scanner.nextLine();
                                garasiUtama.cariKendaraan(tahunCari);
                            } else {
                                System.out.println("Pilihan mode tidak valid.");
                            }
                        } catch (KendaraanTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("\n-- Manajemen Pemilik (HashMap) --");
                        System.out.println("1. Tambah Pemilik Baru");
                        System.out.println("2. Cari Pemilik via NIK");
                        System.out.println("3. Tampilkan Semua Pemilik");
                        System.out.print("Pilih: ");
                        int menuPemilik = scanner.nextInt();
                        scanner.nextLine();

                        if (menuPemilik == 1) {
                            System.out.print("Masukkan NIK: ");
                            String nik = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap: ");
                            String nama = scanner.nextLine();
                            garasiUtama.tambahPemilik(nik, new Pemilik(nik, nama));
                        } else if (menuPemilik == 2) {
                            System.out.print("Masukkan NIK yang dicari: ");
                            garasiUtama.cariPemilik(scanner.nextLine());
                        } else if (menuPemilik == 3) {
                            garasiUtama.tampilkanSemuaPemilik();
                        }
                        break;

                    case 5:
                        System.out.println("\n-- Simulasi Layanan Servis (Asosiasi) --");
                        System.out.print("Masukkan NIK Pemilik: ");
                        String nikCari = scanner.nextLine();
                        System.out.print("Masukkan Nama Pemilik: ");
                        String namaPemilik = scanner.nextLine();
                        Pemilik pSimulasi = new Pemilik(nikCari, namaPemilik);

                        if (garasiUtama.getJumlahKendaraan() == 0) {
                            System.out.println("Belum ada kendaraan yang terdaftar untuk diservis.");
                        } else {
                            garasiUtama.tampilkanSemuaKendaraan();
                            System.out.print("Pilih nomor unit kendaraan yg ingin diservis: ");
                            int idx = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (idx >= 0 && idx < garasiUtama.getJumlahKendaraan()) {
                                Kendaraan target = garasiUtama.getKendaraanInt(idx);
                                LayananServis.prosesBookingServis(pSimulasi, target);
                            } else {
                                System.out.println("Pilihan nomor unit tidak valid!");
                            }
                        }
                        break;

                    case 6:
                        garasiUtama.bacaLogServis();
                        break;

                    case 7:
                        System.out.println("Menyimpan seluruh data ke penyimpanan internal...");
                        garasiUtama.simpanDataKeFile(); 
                        System.out.println("\nTerima kasih telah menggunakan Sistem Manajemen Kendaraan.");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid, silakan coba lagi.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR INPUT] Harap masukkan format data/angka yang valid!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[SYSTEM ERROR] Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}