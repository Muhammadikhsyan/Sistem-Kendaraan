package com.mycompany.sistemkendaraan;

import java.io.FileWriter;
import java.io.IOException;

public class LayananServis {
    public static void prosesBookingServis(Pemilik pemilik, Kendaraan kendaraan) {
        System.out.println("\n=== STRUK BUKTI BOOKING SERVIS ===");
        System.out.println("Pemilik : " + pemilik.getNama() + " (NIK: " + pemilik.getNik() + ")");
        System.out.println("Unit    : " + kendaraan.getMerk() + " (" + kendaraan.getPlatNomor() + ")");
        kendaraan.caraServis();
        System.out.println("Status  : Jadwal servis berhasil dicatat di sistem.");
        System.out.println("=================================");

        try (FileWriter pipaTulis = new FileWriter("log_servis.txt", true)) {
            String logEntry = "Pemilik: " + pemilik.getNama() + " | Unit: " + kendaraan.getMerk() + " (" + kendaraan.getPlatNomor() + ")\n";
            pipaTulis.write(logEntry);
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log transaksi ke file. " + e.getMessage());
        }
    }
}