package com.example.gitae;

public class Transaksi {


        public String id;
        public String nomorTujuan;
        public int jumlah;

        public Transaksi() { } // Diperlukan untuk Firebase

        public Transaksi(String id, String nomorTujuan, int jumlah) {
            this.id = id;
            this.nomorTujuan = nomorTujuan;
            this.jumlah = jumlah;
        }
    }

