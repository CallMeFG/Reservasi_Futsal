package com.bossfutsal.model;

/**
 * Kelas Model (POJO - Plain Old Java Object) untuk merepresentasikan satu baris
 * data dari tabel 'lapangan'. Setiap objek dari kelas ini adalah satu lapangan
 * futsal.
 *
 * @author [Nama Anda & Rekan Anda]
 * @version 1.0
 */
public class Lapangan {

    // 1. ATRIBUT/FIELDS
    // Dibuat 'private' untuk enkapsulasi.
    private String lapanganId;
    private String jenisLapangan;
    private String ukuran;
    private double hargaSewaPerJam;

    // 2. CONSTRUCTOR
    /**
     * Constructor untuk membuat objek Lapangan baru dengan semua datanya. Ini
     * akan sangat berguna saat kita mengambil data dari database.
     *
     * @param lapanganId ID unik lapangan (misal: 'KL00001')
     * @param jenisLapangan Jenis lapangan (misal: 'Sintetis')
     * @param ukuran Ukuran lapangan (misal: 'P42 L25')
     * @param hargaSewaPerJam Harga sewa per jamnya
     */
    public Lapangan(String lapanganId, String jenisLapangan, String ukuran, double hargaSewaPerJam) {
        this.lapanganId = lapanganId;
        this.jenisLapangan = jenisLapangan;
        this.ukuran = ukuran;
        this.hargaSewaPerJam = hargaSewaPerJam;
    }

    // 3. METHODS (GETTERS)
    public String getLapanganId() {
        return lapanganId;
    }

    public String getJenisLapangan() {
        return jenisLapangan;
    }

    public String getUkuran() {
        return ukuran;
    }

    public double getHargaSewaPerJam() {
        return hargaSewaPerJam;
    }

    // 4. METHOD toString() (Sangat Berguna untuk Debugging)
    /**
     * Override method toString() untuk memberikan representasi String yang
     * informatif dari objek Lapangan.Berguna saat kita ingin mencetak objek ini
     * ke konsol.
     *
     * @return
     */
    // Di dalam kelas Lapangan.java
    @Override
    public String toString() {
        // Mengembalikan format "ID - Jenis" (contoh: "KL001 - Sintetis")
        return this.lapanganId + " - " + this.jenisLapangan;
    }
}
