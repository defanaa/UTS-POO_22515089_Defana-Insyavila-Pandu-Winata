package ekspedisiPaket;

public class Pengirim {
    private String idPengirim;
    private String nama;
    private String noTelp;
    private int jumlahKirim;

    public Pengirim() {
        this("-", "-", "00000000");
    }


    public Pengirim(String idPengirim, String nama, String noTelp) {
        setIdPengirim(idPengirim);
        setNama(nama);
        setNoTelp(noTelp);
        this.jumlahKirim = 0;
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public void setIdPengirim(String idPengirim) {
        if (idPengirim == null || idPengirim.trim().isEmpty()) {
            throw new IllegalArgumentException("ID pengirim tidak boleh kosong");
        }
        this.idPengirim = idPengirim.trim().toUpperCase();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        this.nama = nama.trim();
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        // validasi: hanya angka, 8 sampai 15 digit
        if (noTelp == null || !noTelp.trim().matches("\\d{8,15}")) {
            throw new IllegalArgumentException("No telepon harus berisi angka saja (8-15 digit)");
        }
        this.noTelp = noTelp.trim();
    }

    public int getJumlahKirim() {
        return jumlahKirim;
    }

    public void tambahJumlahKirim() {
        jumlahKirim++;
    }

    public void tampilkanInfo() {
        String info = "ID: " + idPengirim + " | " + nama + " | " + noTelp + " | Jumlah kirim: " + jumlahKirim;
        if (isPelangganSetia()) {
            info += " (pelanggan setia)";
        }
        System.out.println(info);
    }

    public boolean isPelangganSetia() {
        return jumlahKirim >= 5;
    }
}
