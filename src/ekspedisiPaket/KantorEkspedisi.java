package ekspedisiPaket;
import java.util.ArrayList;


public class KantorEkspedisi {
    private String nama;
    private String kota;
    private double tarifPerKg;
    private static final double TARIF_DEFAULT = 10000;
    private SortedList daftarPaket;
    private ArrayList<Pengirim> daftarPengirim;
    private ArrayList<Pengiriman> daftarPengiriman;


    public KantorEkspedisi() {
        this("Kantor Ekspedisi", "-", TARIF_DEFAULT);
    }

    public KantorEkspedisi(String nama, String kota, double tarifPerKg) {
        this.nama = nama;
        this.kota = kota;
        this.tarifPerKg = tarifPerKg;
        this.daftarPaket = new SortedList(5); // kapasitas paket 5
        this.daftarPengirim = new ArrayList<>();
        this.daftarPengiriman = new ArrayList<>();
    }


    public SortedList getDaftarPaket() {
        return daftarPaket;
    }


    public boolean tambahPaket(Paket paket) {
        if (daftarPaket.isFull()) {
            System.out.println("Daftar paket penuh, tidak bisa menambah paket");
            return false;
        }
        if (daftarPaket.search(paket.getResi()) != null) {
            System.out.println("Resi " + paket.getResi() + " sudah terdaftar");
            return false;
        }
        return daftarPaket.insert(paket);
    }

    public boolean tambahPengirim(Pengirim pengirim) {
        if (cariPengirim(pengirim.getIdPengirim()) != null) {
            System.out.println("ID pengirim " + pengirim.getIdPengirim() + " sudah terdaftar");
            return false;
        }
        daftarPengirim.add(pengirim);
        return true;
    }


    public Pengiriman buatPengiriman(String resi, String idPengirim, String kotaTujuan) {
        Paket paket = cariPaket(resi);
        if (paket == null) {
            System.out.println("Paket dengan resi " + resi + " tidak ditemukan");
            return null;
        }
        Pengirim pengirim = cariPengirim(idPengirim);
        if (pengirim == null) {
            System.out.println("Pengirim dengan ID " + idPengirim + " tidak ditemukan");
            return null;
        }
        // satu paket hanya boleh dikirim satu kali
        for (Pengiriman p : daftarPengiriman) {
            if (p.getPaket() == paket) {
                System.out.println("Paket " + paket.getResi() + " sudah pernah dibuatkan pengiriman");
                return null;
            }
        }
        String id = "KRM" + (daftarPengiriman.size() + 1);
        Pengiriman pengiriman = new Pengiriman(id, paket, pengirim, kotaTujuan, tarifPerKg);
        daftarPengiriman.add(pengiriman);
        pengirim.tambahJumlahKirim();
        return pengiriman;
    }

    public Paket cariPaket(String resi) {
        return daftarPaket.search(resi);
    }

    public Pengirim cariPengirim(String idPengirim) {
        for (Pengirim p : daftarPengirim) {
            if (p.getIdPengirim().equalsIgnoreCase(idPengirim.trim())) {
                return p;
            }
        }
        return null;
    }

    public boolean updateStatusPaket(String resi) {
        Paket paket = cariPaket(resi);
        if (paket == null) {
            System.out.println("Paket dengan resi " + resi + " tidak ditemukan");
            return false;
        }
        return paket.ubahStatus();
    }

    public void tampilkanSemuaPaket() {
        daftarPaket.tampilkan();
    }

    public void tampilkanSemuaPengirim() {
        if (daftarPengirim.isEmpty()) {
            System.out.println("Belum ada pengirim");
            return;
        }
        for (Pengirim p : daftarPengirim) {
            p.tampilkanInfo();
        }
    }

    public void tampilkanRingkasan() {
        double totalOngkir = 0;
        for (Pengiriman p : daftarPengiriman) {
            totalOngkir += p.getOngkir();
        }
        System.out.println("=== Ringkasan " + nama + " (" + kota + ") ===");
        System.out.println("Jumlah paket      : " + daftarPaket.size());
        System.out.println("Jumlah pengirim   : " + daftarPengirim.size());
        System.out.println("Jumlah pengiriman : " + daftarPengiriman.size());
        System.out.println("Total ongkir      : Rp" + (long) totalOngkir);
    }
}
