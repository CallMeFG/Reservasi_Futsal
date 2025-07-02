package manajemen.controller;
import manajemen.db.DatabaseConnection; 
import manajemen.model.Lapangan;      
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manajemen.model.User;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import manajemen.model.Booking;

public class FutsalController {

    public User checkLogin(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = 'admin'";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getString("user_id"),
                            rs.getString("nama_lengkap"),
                            rs.getString("no_hp"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public boolean updateStatusPembayaran(String bookingId, String statusBaru) {
        String sql = "UPDATE bookings SET status_pembayaran = ? WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, statusBaru);
            pstmt.setString(2, bookingId);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<User> getAllUsers() {
        List<User> daftarUser = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("nama_lengkap"),
                        rs.getString("no_hp"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                daftarUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarUser;
    }
    public boolean tambahUser(User user) {
        String sql = "INSERT INTO users (user_id, nama_lengkap, no_hp, username, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getNamaLengkap());
            pstmt.setString(3, user.getNoHp());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getRole());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET nama_lengkap = ?, no_hp = ?, username = ?, password = ?, role = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNamaLengkap());
            pstmt.setString(2, user.getNoHp());
            pstmt.setString(3, user.getUsername());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                pstmt.setString(4, user.getPassword());
            } else {
                pstmt.setString(4, user.getPassword()); // Untuk simple, kita set saja
            }
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, user.getUserId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean hapusUser(String userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<User> getAllPelanggan() {
        List<User> daftarPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'pelanggan'";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("user_id");
                String nama = rs.getString("nama_lengkap");
                String noHp = rs.getString("no_hp");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                User pelanggan = new User(id, nama, noHp, username, password, role);
                daftarPelanggan.add(pelanggan);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data pelanggan: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarPelanggan;
    }
    public List<Booking> getBookingsByDateAndLapangan(String lapanganId, Date tanggal) {
        List<Booking> daftarBooking = new ArrayList<>();

        String sql = "SELECT b.*, u.nama_lengkap, u.no_hp "
                + "FROM bookings b "
                + "JOIN users u ON b.user_id_pelanggan = u.user_id "
                + "WHERE b.lapangan_id = ? AND b.tanggal_main = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lapanganId);
            pstmt.setDate(2, tanggal);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String namaPelanggan = rs.getString("nama_lengkap");
                String noHpPelanggan = rs.getString("no_hp");

                User pelanggan = new User(rs.getString("user_id_pelanggan"), namaPelanggan, noHpPelanggan, null, null, "pelanggan");

                Lapangan dummyLapangan = new Lapangan(rs.getString("lapangan_id"), "Dummy", null, 0);
                User dummyAdmin = new User(rs.getString("user_id_admin"), "Dummy Admin", null, null, null, "admin");

                Booking booking = new Booking(
                        rs.getString("booking_id"),
                        pelanggan, //
                        dummyLapangan,
                        dummyAdmin,
                        rs.getDate("tanggal_main"),
                        rs.getTime("jam_mulai"),
                        rs.getInt("durasi_jam"),
                        rs.getDouble("total_harga"),
                        rs.getString("metode_pembayaran"),
                        rs.getString("status_pembayaran"),
                        rs.getTimestamp("tanggal_booking")
                );
                daftarBooking.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarBooking;
    }
    public List<Booking> getAllBookings() {
        List<Booking> daftarBooking = new ArrayList<>();

        String sql = "SELECT b.*, p.nama_lengkap, l.jenis_lapangan "
                + "FROM bookings b "
                + "JOIN users p ON b.user_id_pelanggan = p.user_id "
                + "JOIN lapangan l ON b.lapangan_id = l.lapangan_id "
                + "ORDER BY b.tanggal_booking DESC"; // Diurutkan dari yang paling baru

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User pelanggan = new User(rs.getString("user_id_pelanggan"), rs.getString("nama_lengkap"), null, null, null, "pelanggan");

                Lapangan lapangan = new Lapangan(rs.getString("lapangan_id"), rs.getString("jenis_lapangan"), null, 0);

                User admin = new User(rs.getString("user_id_admin"), "Admin", null, null, null, "admin");

                Booking booking = new Booking(
                        rs.getString("booking_id"),
                        pelanggan,
                        lapangan,
                        admin,
                        rs.getDate("tanggal_main"),
                        rs.getTime("jam_mulai"),
                        rs.getInt("durasi_jam"),
                        rs.getDouble("total_harga"),
                        rs.getString("metode_pembayaran"),
                        rs.getString("status_pembayaran"),
                        rs.getTimestamp("tanggal_booking")
                );
                daftarBooking.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarBooking;
    }
    public List<Booking> getBookingsByDateRange(Date tanggalMulai, Date tanggalSelesai) {
        List<Booking> daftarBooking = new ArrayList<>();
        String sql = "SELECT b.*, p.nama_lengkap, l.jenis_lapangan "
                + "FROM bookings b "
                + "JOIN users p ON b.user_id_pelanggan = p.user_id "
                + "JOIN lapangan l ON b.lapangan_id = l.lapangan_id "
                + "WHERE b.tanggal_main BETWEEN ? AND ? "
                + "ORDER BY b.tanggal_booking DESC";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, tanggalMulai);
            pstmt.setDate(2, tanggalSelesai);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User pelanggan = new User(rs.getString("user_id_pelanggan"), rs.getString("nama_lengkap"), null, null, null, "pelanggan");
                Lapangan lapangan = new Lapangan(rs.getString("lapangan_id"), rs.getString("jenis_lapangan"), null, rs.getDouble("total_harga"));
                User admin = new User(rs.getString("user_id_admin"), "Admin", null, null, null, "admin");
                Booking booking = new Booking(rs.getString("booking_id"), pelanggan, lapangan, admin, rs.getDate("tanggal_main"), rs.getTime("jam_mulai"), rs.getInt("durasi_jam"), rs.getDouble("total_harga"), rs.getString("metode_pembayaran"), rs.getString("status_pembayaran"), rs.getTimestamp("tanggal_booking"));
                daftarBooking.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarBooking;
    }
    public boolean simpanBooking(Booking booking) {
        String sql = "INSERT INTO bookings (booking_id, user_id_pelanggan, lapangan_id, user_id_admin, "
                + "tanggal_main, jam_mulai, durasi_jam, total_harga, metode_pembayaran, "
                + "status_pembayaran, tanggal_booking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, booking.getBookingId());
            pstmt.setString(2, booking.getPelanggan().getUserId());
            pstmt.setString(3, booking.getLapangan().getLapanganId());
            pstmt.setString(4, booking.getAdmin().getUserId()); 
            pstmt.setDate(5, booking.getTanggalMain());
            pstmt.setTime(6, booking.getJamMulai());
            pstmt.setInt(7, booking.getDurasiJam()); 
            pstmt.setDouble(8, booking.getTotalHarga()); 
            pstmt.setString(9, "Tunai");
            pstmt.setString(10, "Belum Lunas"); 
            pstmt.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Lapangan> getAllLapangan() {
        List<Lapangan> daftarLapangan = new ArrayList<>();

        String sql = "SELECT * FROM lapangan";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("lapangan_id");
                String jenis = rs.getString("jenis_lapangan");
                String ukuran = rs.getString("ukuran");
                double harga = rs.getDouble("harga_sewa_per_jam");

                Lapangan lapangan = new Lapangan(id, jenis, ukuran, harga);

                daftarLapangan.add(lapangan);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data lapangan: " + e.getMessage());
            e.printStackTrace();
        }

        return daftarLapangan;
    }
    public boolean tambahLapangan(Lapangan lapangan) {
        String sql = "INSERT INTO lapangan (lapangan_id, jenis_lapangan, ukuran, harga_sewa_per_jam) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lapangan.getLapanganId());
            pstmt.setString(2, lapangan.getJenisLapangan());
            pstmt.setString(3, lapangan.getUkuran());
            pstmt.setDouble(4, lapangan.getHargaSewaPerJam());

            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Gagal menyimpan data lapangan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean hapusLapangan(String id) {
        String sql = "DELETE FROM lapangan WHERE lapangan_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Gagal menghapus data lapangan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateLapangan(Lapangan lapangan) {
        String sql = "UPDATE lapangan SET jenis_lapangan = ?, ukuran = ?, harga_sewa_per_jam = ? WHERE lapangan_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lapangan.getJenisLapangan());
            pstmt.setString(2, lapangan.getUkuran());
            pstmt.setDouble(3, lapangan.getHargaSewaPerJam());
            pstmt.setString(4, lapangan.getLapanganId()); 

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Gagal mengupdate data lapangan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
