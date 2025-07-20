package manajemen.main;

import manajemen.model.User;
import manajemen.view.FormLogin;
import manajemen.view.FormTransaksiBooking;

public class MainApp {
    public static void main(String[] args) {
        FormLogin formLogin = new FormLogin(null, true); // Buat sebagai dialog modal
        formLogin.setVisible(true);

        if (formLogin.loggedInUser != null) {
            System.out.println("Login berhasil sebagai: " + formLogin.loggedInUser.getNamaLengkap());
            FormTransaksiBooking formUtama = new FormTransaksiBooking();
            formUtama.setVisible(true);
        } else {
            System.out.println("Login dibatalkan. Aplikasi berhenti.");
            System.exit(0);
        }
    }
}