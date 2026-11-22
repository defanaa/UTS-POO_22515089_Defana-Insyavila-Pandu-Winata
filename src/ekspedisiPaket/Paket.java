package ekspedisiPaket;

public class Paket {
    private String resi;
    private String deskripsi;
    private double beratKg;
    private String status;

    private static final String[] URUTAN_STATUS = {"DITERIMA", "DIPROSES", "DIKIRIM", "SAMPAI"};


    public Paket() {
        this("-", "-", 1);
    }

    public Paket(String resi, String deskripsi, double beratKg) {
        setResi(resi);
        setDeskripsi(deskripsi);
        setBeratKg(beratKg);
        this.status = URUTAN_STATUS[0];
    }

    public String getResi() {
        return resi;
    }

    public void setResi(String resi) {
        if (resi == null || resi.trim().isEmpty()) {
            throw new IllegalArgumentException("Resi tidak boleh kosong");
        }
        this.resi = resi.trim().toUpperCase();
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        if (deskripsi == null || deskripsi.trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi tidak boleh kosong");
        }
        this.deskripsi = deskripsi.trim();
    }

    public double getBeratKg() {
        return beratKg;
    }

    public void setBeratKg(double beratKg) {
        // aturan bisnis: berat harus lebih dari 0
        if (beratKg <= 0) {
            throw new IllegalArgumentException("Berat harus lebih dari 0");
        }
        this.beratKg = beratKg;
    }

    public String getStatus() {
        return status;
    }

    // ongkir = berat x tarif per kg
    public double hitungOngkir(double tarifPerKg) {
        return beratKg * tarifPerKg;
    }

    public boolean ubahStatus() {
        for (int i = 0; i < URUTAN_STATUS.length; i++) {
            if (URUTAN_STATUS[i].equals(status)) {
                if (i == URUTAN_STATUS.length - 1) {
                    System.out.println("Paket sudah SAMPAI, status tidak bisa diubah lagi");
                    return false;
                }
                status = URUTAN_STATUS[i + 1];
                return true;
            }
        }
        return false;
    }

    public boolean isSelesai() {
        return status.equals("SAMPAI");
    }

    public void tampilkanInfo() {
        System.out.println("Resi: " + resi + " | " + deskripsi + " | " + beratKg + " kg | " + status);
    }
}
