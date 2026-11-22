package ekspedisiPaket;

public class Pengiriman {
    private String idPengiriman;
    private Paket paket;
    private Pengirim pengirim;
    private String kotaTujuan;
    private double ongkir;

    public Pengiriman() {
        this("-", new Paket(), new Pengirim(), "-", 1);
    }


    public Pengiriman(String idPengiriman, Paket paket, Pengirim pengirim, String kotaTujuan, double tarifPerKg) {
        if (paket == null || pengirim == null) {
            throw new IllegalArgumentException("Paket dan pengirim tidak boleh kosong");
        }
        this.idPengiriman = idPengiriman;
        this.paket = paket;
        this.pengirim = pengirim;
        setKotaTujuan(kotaTujuan);
        hitungOngkir(tarifPerKg);
    }

    public Paket getPaket() {
        return paket;
    }

    public Pengirim getPengirim() {
        return pengirim;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        if (kotaTujuan == null || kotaTujuan.trim().isEmpty()) {
            throw new IllegalArgumentException("Kota tujuan tidak boleh kosong");
        }
        this.kotaTujuan = kotaTujuan.trim();
    }

    public double getOngkir() {
        return ongkir;
    }

    public double hitungOngkir(double tarifPerKg) {
        ongkir = paket.hitungOngkir(tarifPerKg);
        return ongkir;
    }

    // majukan status paket satu tahap
    public boolean lanjutkanStatus() {
        return paket.ubahStatus();
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + idPengiriman + " | Paket: " + paket.getResi()
                + " | Pengirim: " + pengirim.getNama()
                + " | Tujuan: " + kotaTujuan
                + " | Ongkir: Rp" + (long) ongkir
                + " | Status: " + paket.getStatus());
    }
}
