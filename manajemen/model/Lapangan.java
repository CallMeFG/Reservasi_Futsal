package manajemen.model;

public class Lapangan {

    private String lapanganId;
    private String jenisLapangan;
    private String ukuran;
    private double hargaSewaPerJam;

    public Lapangan(String lapanganId, String jenisLapangan, String ukuran, double hargaSewaPerJam) {
        this.lapanganId = lapanganId;
        this.jenisLapangan = jenisLapangan;
        this.ukuran = ukuran;
        this.hargaSewaPerJam = hargaSewaPerJam;
    }

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

    @Override
    public String toString() {
        // Mengembalikan format "ID - Jenis" (contoh: "KL001 - Sintetis")
        return this.lapanganId + " - " + this.jenisLapangan;
    }
}
