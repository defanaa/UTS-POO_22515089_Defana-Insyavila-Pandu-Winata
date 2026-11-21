# daftar class

1. paket 
2. pengirim
3. pengiriman
4. kantorEkspedisi
5. sortedList
6. Main

# paket
atribut :
- resi : String
- deskripsi : string
- beratKg : double
- status : string

  konstruktor :
+ paket ()
+ paket (resi : String, deskripsi : String beratKg : double)

  method :
+ getResi(): String
+ setResi (resi: String) :void
+ getDeskripsi() : String
+ setDeskripsi(deskripsi : string) : void
+ getBeratKg(): double
+ setBeratKg(beratKg: double): void
+ getStatus(): String
+ hitungOngkir(tarifPerKg: double): double
+ ubahStatus(): boolean
+ isSelesai(): boolean
+ tampilkanInfo(): void

aturan bisnis :
- berat harus > 0
- status hanya boleh maju satu tahap



# pengirim

atribut :
- idPengirim : string
- nama : string
- noTelp: String
- jumlahKirim: int

  konstruktor:
+ Pengirim()
+ Pengirim(idPengirim: String, nama: String, noTelp: String)

  method:
+ getIdPengirim(): String
+ setIdPengirim(idPengirim: String): void
+ getNama(): String
+ setNama(nama: String): void
+ getNoTelp(): String
+ setNoTelp(noTelp: String): void
+ getJumlahKirim(): int
+ tambahJumlahKirim(): void
+ tampilkanInfo(): void
+ isPelangganSetia(): boolean