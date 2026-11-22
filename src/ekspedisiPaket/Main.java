package ekspedisiPaket;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static KantorEkspedisi kantor = new KantorEkspedisi("Kantor Ekspedisi Kendari", "Kendari", 10000);

    public static void main(String[] args) {
        boolean jalan = true;
        try {
            while (jalan) {
                tampilkanMenu();
                int pilihan = bacaInt("Pilih menu: ");
                System.out.println();
                switch (pilihan) {
                    case 1:
                        menuPaket();
                        break;
                    case 2:
                        menuPengirim();
                        break;
                    case 3:
                        menuBuatPengiriman();
                        break;
                    case 4:
                        menuCariData();
                        break;
                    case 5:
                        menuSortedList();
                        break;
                    case 6:
                        menuRingkasan();
                        break;
                    case 7:
                        jalan = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia, pilih 1-7");
                }
            }
        } catch (NoSuchElementException e) {
            // terjadi kalau input ditutup (misal Ctrl+D)
            System.out.println("Input berakhir, program dihentikan");
        }
        System.out.println("Terima kasih, program selesai");
    }

    private static void tampilkanMenu() {
        System.out.println();
        System.out.println("===== EKSPEDISI PAKET =====");
        System.out.println("1. Kelola paket");
        System.out.println("2. Kelola pengirim");
        System.out.println("3. Buat pengiriman");
        System.out.println("4. Cari data");
        System.out.println("5. Operasi ADT Sorted List");
        System.out.println("6. Ringkasan");
        System.out.println("7. Keluar");
    }

    private static void menuPaket() {
        System.out.println("-- Kelola Paket --");
        System.out.println("1. Tambah paket");
        System.out.println("2. Tampilkan semua paket");
        System.out.println("3. Majukan status paket");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            String resi = bacaString("Resi: ");
            String deskripsi = bacaString("Deskripsi: ");
            double berat = bacaDouble("Berat (kg): ");
            try {
                Paket paket = new Paket(resi, deskripsi, berat);
                if (kantor.tambahPaket(paket)) {
                    System.out.println("Paket " + paket.getResi() + " berhasil ditambahkan");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if (pilih == 2) {
            kantor.tampilkanSemuaPaket();
        } else if (pilih == 3) {
            String resi = bacaString("Resi paket: ");
            if (kantor.updateStatusPaket(resi)) {
                System.out.println("Status sekarang: " + kantor.cariPaket(resi).getStatus());
            }
        } else {
            System.out.println("Pilihan tidak tersedia");
        }
    }

    private static void menuPengirim() {
        System.out.println("-- Kelola Pengirim --");
        System.out.println("1. Tambah pengirim");
        System.out.println("2. Tampilkan semua pengirim");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            String id = bacaString("ID pengirim: ");
            String nama = bacaString("Nama: ");
            String telp = bacaString("No telepon: ");
            try {
                Pengirim pengirim = new Pengirim(id, nama, telp);
                if (kantor.tambahPengirim(pengirim)) {
                    System.out.println("Pengirim " + pengirim.getNama() + " berhasil ditambahkan");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if (pilih == 2) {
            kantor.tampilkanSemuaPengirim();
        } else {
            System.out.println("Pilihan tidak tersedia");
        }
    }

    private static void menuBuatPengiriman() {
        System.out.println("-- Buat Pengiriman --");
        String resi = bacaString("Resi paket: ");
        String id = bacaString("ID pengirim: ");
        String kota = bacaString("Kota tujuan: ");
        try {
            Pengiriman p = kantor.buatPengiriman(resi, id, kota);
            if (p != null) {
                System.out.println("Pengiriman berhasil dibuat:");
                p.tampilkanInfo();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuCariData() {
        System.out.println("-- Cari Data --");
        System.out.println("1. Cari paket (resi)");
        System.out.println("2. Cari pengirim (ID)");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            Paket paket = kantor.cariPaket(bacaString("Resi: "));
            if (paket == null) {
                System.out.println("Paket tidak ditemukan");
            } else {
                paket.tampilkanInfo();
            }
        } else if (pilih == 2) {
            Pengirim pengirim = kantor.cariPengirim(bacaString("ID pengirim: "));
            if (pengirim == null) {
                System.out.println("Pengirim tidak ditemukan");
            } else {
                pengirim.tampilkanInfo();
            }
        } else {
            System.out.println("Pilihan tidak tersedia");
        }
    }

    private static void menuSortedList() {
        SortedList list = kantor.getDaftarPaket();
        System.out.println("-- ADT Sorted List --");
        System.out.println("1. insert");
        System.out.println("2. remove");
        System.out.println("3. search");
        System.out.println("4. get (index)");
        System.out.println("5. tampilkan");
        System.out.println("6. size, isEmpty, isFull");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            String resi = bacaString("Resi: ");
            String deskripsi = bacaString("Deskripsi: ");
            double berat = bacaDouble("Berat (kg): ");
            try {
                if (kantor.tambahPaket(new Paket(resi, deskripsi, berat))) {
                    System.out.println("Insert berhasil");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else if (pilih == 2) {
            String resi = bacaString("Resi yang dihapus: ");
            if (list.isEmpty()) {
                System.out.println("Daftar kosong, tidak ada yang bisa dihapus");
            } else if (list.remove(resi)) {
                System.out.println("Paket berhasil dihapus");
            } else {
                System.out.println("Resi " + resi + " tidak ditemukan");
            }
        } else if (pilih == 3) {
            Paket p = list.search(bacaString("Resi dicari: "));
            if (p == null) {
                System.out.println("Data tidak ditemukan");
            } else {
                p.tampilkanInfo();
            }
        } else if (pilih == 4) {
            int index = bacaInt("Index: ");
            Paket p = list.get(index);
            if (p == null) {
                System.out.println("Index " + index + " di luar batas");
            } else {
                p.tampilkanInfo();
            }
        } else if (pilih == 5) {
            list.tampilkan();
        } else if (pilih == 6) {
            System.out.println("size = " + list.size() + ", isEmpty = " + list.isEmpty() + ", isFull = " + list.isFull());
        } else {
            System.out.println("Pilihan tidak tersedia");
        }
        System.out.println();
        System.out.println("Isi ADT sekarang:");
        list.tampilkan();
    }

    private static void menuRingkasan() {
        kantor.tampilkanRingkasan();
    }

    // baca angka bulat, ulang terus sampai input benar
    private static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat");
            }
        }
    }

    private static double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka");
            }
        }
    }


    private static String bacaString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Input tidak boleh kosong");
        }
    }
}
