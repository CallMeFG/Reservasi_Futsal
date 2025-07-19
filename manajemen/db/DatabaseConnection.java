package manajemen.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/manajemen_futsal";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection;
    
    private DatabaseConnection() {
    }
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("FATAL: Driver MySQL (Connector/J) tidak ditemukan.");
            System.err.println("Pastikan file .jar sudah ditambahkan ke Libraries proyek.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("FATAL: Gagal terhubung ke database. Cek detail koneksi & pastikan MySQL berjalan.");
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) {
        System.out.println("--- Tes Koneksi Database ---");
        Connection testConnection = DatabaseConnection.getConnection();
        
        if (testConnection != null) {
            System.out.println("Tes koneksi SUKSES. Aplikasi siap terhubung ke database.");
            try {
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