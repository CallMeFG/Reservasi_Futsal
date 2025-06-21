package com.bossfutsal.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas ini bertanggung jawab untuk membuat dan mengelola koneksi ke database.
 * Menggunakan Singleton Pattern untuk memastikan hanya ada satu koneksi aktif
 * di seluruh aplikasi demi efisiensi sumber daya.
 *
 * @author [Nama Anda & Rekan Anda]
 * @version 1.0
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/manajemen_futsal";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection;
    /**
     * Constructor dibuat private untuk mencegah pembuatan objek dari luar kelas ini.
     * Ini adalah inti dari Singleton Pattern.
     */
    private DatabaseConnection() {
        // Konstruktor sengaja dikosongkan.
    }
    /**
     * Method publik statis untuk mendapatkan instance koneksi.
     * Jika koneksi belum ada atau sudah ditutup, maka akan dibuat koneksi baru.
     * Jika sudah ada, akan mengembalikan koneksi yang sama.
     *
     * @return Objek Connection yang siap pakai.
     */
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // 1. Mendaftarkan driver JDBC dari file .jar yang sudah ditambahkan
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // 2. Membuat koneksi baru ke database
                System.out.println("Mencoba membuat koneksi baru ke database...");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Koneksi berhasil dibuat!");
            }
        } catch (ClassNotFoundException e) {
            // Error ini terjadi jika file .jar Connector/J lupa ditambahkan ke Libraries
            System.err.println("FATAL: Driver MySQL (Connector/J) tidak ditemukan.");
            System.err.println("Pastikan file .jar sudah ditambahkan ke Libraries proyek.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Error ini terjadi jika detail koneksi salah (URL, user, pass) atau server MySQL tidak aktif
            System.err.println("FATAL: Gagal terhubung ke database. Cek detail koneksi & pastikan MySQL berjalan.");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Method main ini digunakan untuk melakukan tes koneksi secara mandiri.Anda bisa menjalankan file ini langsung untuk memastikan koneksi berhasil.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("--- Tes Koneksi Database ---");
        Connection testConnection = DatabaseConnection.getConnection();
        
        if (testConnection != null) {
            System.out.println("Tes koneksi SUKSES. Aplikasi siap terhubung ke database.");
            try {
                // Jangan lupa menutup koneksi setelah tes selesai
                testConnection.close();
                System.out.println("Koneksi berhasil ditutup setelah tes.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Tes koneksi GAGAL. Periksa pesan error di atas.");
        }
        System.out.println("--- Tes Selesai ---");
    }
}