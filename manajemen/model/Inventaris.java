package manajemen.model;

public class Inventaris {
    private String inventarisId;
    private String namaBrg;
    private int jumlah;

    public Inventaris(String inventarisId, String namaBrg, String jumlah) {
        this.inventarisId = inventarisId;
        this.namaBrg = namaBrg;
        this.jumlah = Integer.parseInt(jumlah);
    }
    public String getInventarisId() {
        return inventarisId;
    }

    public String getNamaBrg() {
        return namaBrg;
    }
    public String getJumlah() {
        return String.valueOf(jumlah);
    }
 
    @Override
    public String toString() {
        return this.namaBrg; 
    }
}
