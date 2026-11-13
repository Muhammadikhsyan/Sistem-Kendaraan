package com.mycompany.sistemkendaraan;

public class KendaraanTidakDitemukanException extends Exception {
    public KendaraanTidakDitemukanException(String message) {
        super(message);
    }
}