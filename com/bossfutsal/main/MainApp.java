package com.bossfutsal.main;

import com.bossfutsal.model.User;
import com.bossfutsal.view.FormLogin;
import com.bossfutsal.view.FormTransaksiBooking;

public class MainApp {
    public static void main(String[] args) {
        // 1. Buat dan tampilkan form login terlebih dahulu
        FormLogin formLogin = new FormLogin(null, true); // Buat sebagai dialog modal
        formLogin.setVisible(true);

        // 2. Kode di bawah ini baru akan berjalan SETELAH form login ditutup
        // Cek apakah login berhasil dengan melihat variabel loggedInUser
        if (formLogin.loggedInUser != null) {
            // Jika login sukses, tampilkan form utama
            System.out.println("Login berhasil sebagai: " + formLogin.loggedInUser.getNamaLengkap());
            FormTransaksiBooking formUtama = new FormTransaksiBooking();
            // Kita bisa teruskan info user yang login ke form utama jika perlu
            // formUtama.setLoggedInUser(formLogin.loggedInUser); 
            formUtama.setVisible(true);
        } else {
            // Jika login gagal (user menutup dialog), aplikasi selesai.
            System.out.println("Login dibatalkan. Aplikasi berhenti.");
            System.exit(0);
        }
    }
}