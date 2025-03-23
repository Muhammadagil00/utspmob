package com.example.gitae;

public class Transaction {
    private String id;
    private String jenis; // "Pemasukan" atau "Pengeluaran"
    private int jumlah;
    private String deskripsi;
    private String tanggal;

    public Transaction() {}

    public Transaction(String id, String jenis, int jumlah, String deskripsi, String tanggal) {
        this.id = id;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public String getId() { return id; }
    public String getJenis() { return jenis; }
    public int getJumlah() { return jumlah; }
    public String getDeskripsi() { return deskripsi; }
    public String getTanggal() { return tanggal; }
}

