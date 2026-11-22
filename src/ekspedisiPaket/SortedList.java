package ekspedisiPaket;

import java.util.ArrayList;


public class SortedList {
    private ArrayList<Paket> data;
    private int kapasitas;

    public SortedList(int kapasitas) {
        this.kapasitas = kapasitas;
        this.data = new ArrayList<>();
    }


    public boolean insert(Paket paket) {
        if (paket == null || isFull()) {
            return false;
        }
        int pos = 0;
        while (pos < data.size() && data.get(pos).getResi().compareTo(paket.getResi()) < 0) {
            pos++;
        }
        if (pos < data.size() && data.get(pos).getResi().equals(paket.getResi())) {
            return false; // resi sudah ada
        }
        data.add(pos, paket);
        return true;
    }

    public boolean remove(String resi) {
        if (isEmpty()) {
            return false;
        }
        int idx = cariIndeks(resi);
        if (idx == -1) {
            return false;
        }
        data.remove(idx);
        return true;
    }


    public Paket search(String resi) {
        int idx = cariIndeks(resi);
        if (idx == -1) {
            return null;
        }
        return data.get(idx);
    }


    public Paket get(int index) {
        if (index < 0 || index >= data.size()) {
            return null;
        }
        return data.get(index);
    }

    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Daftar paket kosong");
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            System.out.print("[" + i + "] ");
            data.get(i).tampilkanInfo();
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean isFull() {
        return data.size() >= kapasitas;
    }

    private int cariIndeks(String resi) {
        if (resi == null) {
            return -1;
        }
        String kunci = resi.trim().toUpperCase();
        int kiri = 0;
        int kanan = data.size() - 1;
        while (kiri <= kanan) {
            int tengah = (kiri + kanan) / 2;
            int hasil = data.get(tengah).getResi().compareTo(kunci);
            if (hasil == 0) {
                return tengah;
            } else if (hasil < 0) {
                kiri = tengah + 1;
            } else {
                kanan = tengah - 1;
            }
        }
        return -1;
    }
}