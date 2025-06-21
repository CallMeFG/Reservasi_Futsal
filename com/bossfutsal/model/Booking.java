package com.bossfutsal.model;

import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Kelas Model untuk merepresentasikan data dari tabel 'bookings'.
 */
public class Booking {
    private String bookingId;
    private Date tanggalMain;
    private Time jamMulai;
    private int durasiJam;
    private double totalHarga;
    private String metodePembayaran;
    private String statusPembayaran;
    private Timestamp tanggalBooking;

    // Atribut untuk menyimpan objek relasi
    private User pelanggan;
    private Lapangan lapangan;
    private User admin;

    public Booking(String bookingId, User pelanggan, Lapangan lapangan, User admin, Date tanggalMain, Time jamMulai, int durasiJam, double totalHarga, String metodePembayaran, String statusPembayaran, Timestamp tanggalBooking) {
        this.bookingId = bookingId;
        this.pelanggan = pelanggan;
        this.lapangan = lapangan;
        this.admin = admin;
        this.tanggalMain = tanggalMain;
        this.jamMulai = jamMulai;
        this.durasiJam = durasiJam;
        this.totalHarga = totalHarga;
        this.metodePembayaran = metodePembayaran;
        this.statusPembayaran = statusPembayaran;
        this.tanggalBooking = tanggalBooking;
    }

    public String getBookingId() { return bookingId; }
    public User getPelanggan() { return pelanggan; }
    public Lapangan getLapangan() { return lapangan; }
    public User getAdmin() { return admin; }
    public java.sql.Date getTanggalMain() { return tanggalMain; }
    public java.sql.Time getJamMulai() { return jamMulai; }
    public int getDurasiJam() { return durasiJam; }
    public double getTotalHarga() { return totalHarga; }
    public String getMetodePembayaran() { return metodePembayaran; }

    public String getStatusPembayaran() { return statusPembayaran; } 
    public java.sql.Timestamp getTanggalBooking() { return tanggalBooking; }
}