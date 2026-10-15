# 🏥 Sistem Manajemen Antrian Pasien pada Klinik

> Mini Project 4 - Pemrograman Berorientasi Objek (PBO)

Program berbasis Java Console untuk mengelola data antrian pasien klinik menggunakan database MySQL. Program menyediakan fitur CRUD (*Create, Read, Update, Delete*) dan pemanggilan pasien dengan dua jenis pasien, yaitu Pasien Umum dan Pasien BPJS.

Program menerapkan konsep Encapsulation, Inheritance, Polymorphism, Abstraction, MVC (*Model-View-Controller*), JDBC, validasi input, serta Interface sebagai nilai tambah.

---

## 👨‍🎓 Identitas Mahasiswa

| Keterangan | Data |
|---|---|
| Nama | Ahmad Fajar Novia |
| NIM | 2509116041 |
| Program Studi | Sistem Informasi |
| Fakultas | Fakultas Teknik |
| Universitas | Universitas Mulawarman |
| Mata Kuliah | Pemrograman Berorientasi Objek |
| Project | Mini Project 4 |

---

## 📋 1. Deskripsi Singkat Program

Sistem Manajemen Antrian Pasien pada Klinik merupakan program yang digunakan untuk mengelola data pasien melalui menu console. Pengguna dapat menampilkan, menambahkan, memperbarui, menghapus, dan memanggil pasien berdasarkan ID.

Program memiliki dua jenis pasien:

- **Pasien Umum:** memiliki pilihan pembayaran Tunai atau Transfer.
- **Pasien BPJS:** memiliki nomor BPJS sebagai informasi tambahan.

Data pasien disimpan menggunakan database MySQL sehingga data yang belum dihapus tetap tersedia ketika program dijalankan kembali.

Program juga dilengkapi validasi input untuk memeriksa nama, umur, nomor telepon, nomor BPJS, dan ID pasien.

---

## 🗂️ 2. Struktur Package

```text
src/main/java/
├── main/
│   └── Main.java
├── controller/
│   └── PasienController.java
├── model/
│   ├── Pasien.java
│   ├── PasienUmum.java
│   ├── PasienBPJS.java
│   └── Pembayaran.java
├── view/
│   └── PasienView.java
├── service/
│   └── PasienCRUD.java
├── dao/
│   └── PasienDAO.java
├── database/
│   └── KoneksiDatabase.java
└── helper/
    └── ValidasiInput.java
```

### Penjelasan Package

- **main:** berisi `Main.java` sebagai titik awal program untuk menjalankan `PasienController`.
- **controller:** berisi `PasienController.java` yang mengatur alur program, menerima input, dan memproses pilihan menu.
- **model:** berisi class `Pasien`, `PasienUmum`, `PasienBPJS`, dan interface `Pembayaran` untuk merepresentasikan data serta perilaku pasien.
- **view:** berisi `PasienView.java` untuk menampilkan menu, daftar pasien, dan pesan kepada pengguna.
- **service:** berisi `PasienCRUD.java` yang mengatur proses tambah, tampil, update, hapus, dan panggil pasien.
- **dao:** berisi `PasienDAO.java` yang menjalankan operasi SQL untuk mengelola data pasien.
- **database:** berisi `KoneksiDatabase.java` untuk menghubungkan aplikasi Java dengan MySQL.
- **helper:** berisi `ValidasiInput.java` untuk memeriksa input pengguna.

---

## ⚙️ 3. Penjelasan Alur Program

Saat program dijalankan, `Main.java` memanggil `PasienController` untuk menampilkan menu utama.

Alur pengolahan data:

1. Pengguna memilih menu melalui console.
2. Controller menerima dan memvalidasi input.
3. Controller memanggil method pada Service.
4. Service meneruskan proses pengelolaan data ke DAO.
5. DAO menjalankan perintah SQL melalui JDBC ke database MySQL.
6. Hasil proses ditampilkan kepada pengguna melalui View.

### Menu Program

<img width="504" height="208" alt="image" src="https://github.com/user-attachments/assets/eaac771c-58de-4af5-acd2-7025839c6bf9" />


**1. Tampilkan Pasien**

<img width="744" height="257" alt="image" src="https://github.com/user-attachments/assets/2d931ebd-d6f2-4bc3-9c44-2f742518dcc4" />

Menampilkan seluruh data pasien yang tersimpan di database, meliputi ID, nama, umur, nomor telepon, dan informasi tambahan sesuai jenis pasien.

**2. Tambahkan Pasien**

<img width="790" height="710" alt="image" src="https://github.com/user-attachments/assets/9e0f60a6-1583-4d9d-8556-db143e48e055" />

Pengguna memasukkan nama, umur, nomor telepon, dan jenis pasien. Pasien Umum memiliki pilihan pembayaran Tunai atau Transfer, sedangkan Pasien BPJS meminta nomor BPJS. ID dibuat otomatis oleh MySQL menggunakan `AUTO_INCREMENT`.

**3. Update Pasien**

<img width="698" height="669" alt="image" src="https://github.com/user-attachments/assets/8feb4062-20c1-46e1-b6f9-e0bec760841c" />

Memperbarui nama, umur, dan nomor telepon berdasarkan ID pasien. Jika ID tidak ditemukan, program menampilkan pesan bahwa pasien tidak ditemukan.

**4. Hapus Pasien**

<img width="734" height="601" alt="image" src="https://github.com/user-attachments/assets/2d33f930-3636-4549-b0cd-c00fe02a8c21" />

Menghapus data pasien berdasarkan ID dari database MySQL. Data pada tabel terkait juga terhapus melalui relasi foreign key dengan `ON DELETE CASCADE`.

**5. Panggil Pasien**

<img width="683" height="484" alt="image" src="https://github.com/user-attachments/assets/671b88c6-de45-43d4-bc3e-a6c04197ed7e" />

Menampilkan informasi pasien berdasarkan ID. Untuk Pasien Umum, program juga menjalankan proses pembayaran. Setelah berhasil dipanggil, data pasien dihapus dari database karena dianggap sudah masuk untuk mendapatkan pelayanan.

**6. Keluar**

<img width="631" height="323" alt="image" src="https://github.com/user-attachments/assets/06c00a55-f861-48ea-ac8f-63608545eff0" />

Mengakhiri program dan menampilkan pesan bahwa program selesai.

---

## 🔒 4. Penerapan Encapsulation

Encapsulation diterapkan dengan menjadikan atribut tertentu sebagai `private` sehingga tidak dapat diakses langsung dari luar class.

Contoh pada class `Pasien`:

<img width="470" height="138" alt="image" src="https://github.com/user-attachments/assets/dcfbe380-f025-4fbe-be79-6d109c5cf8b4" />


ID pasien menggunakan:

```java
protected final int idPasien;
```

Data diakses melalui getter dan setter, seperti `getNama()`, `setNama()`, `getUmur()`, dan `setUmur()`.

Class `PasienUmum` memiliki atribut `private String jenisPembayaran`, sedangkan `PasienBPJS` memiliki atribut `private String nomorBPJS`.

Setter juga melakukan validasi terhadap data yang dimasukkan. Dengan demikian, perubahan data dapat dikendalikan melalui method yang telah disediakan.

---

## 🧬 5. Penerapan Inheritance

Inheritance diterapkan dengan menjadikan `Pasien` sebagai superclass dan `PasienUmum` serta `PasienBPJS` sebagai subclass.

```text
          Pasien
         /      \
        /        \
 PasienUmum   PasienBPJS
```

- **Pasien:** menyimpan data dasar pasien seperti ID, nama, umur, dan nomor telepon.
- **PasienUmum:** mewarisi class `Pasien` dan memiliki atribut tambahan berupa jenis pembayaran.
- **PasienBPJS:** mewarisi class `Pasien` dan memiliki atribut tambahan berupa nomor BPJS.

Kedua subclass menggunakan `extends Pasien`. Dengan inheritance, atribut dan method dasar tidak perlu ditulis ulang pada setiap subclass.

---

## 🎭 6. Penerapan Polymorphism

Polymorphism diterapkan melalui method overriding dan method overloading.

### A. Method Overriding

Class `Pasien` memiliki abstract method:

<img width="440" height="34" alt="image" src="https://github.com/user-attachments/assets/926d414b-6565-443b-94ba-dfebde4b2654" />


Method tersebut diimplementasikan oleh `PasienUmum` dan `PasienBPJS` menggunakan `@Override`.

Meskipun nama method sama, informasi yang ditampilkan berbeda sesuai jenis pasien. Pasien Umum menampilkan informasi pembayaran, sedangkan Pasien BPJS menampilkan nomor BPJS.

Method `getInfoTambahan()` juga di-override oleh kedua subclass agar informasi tambahan sesuai dengan jenis objek pasien.

### B. Method Overloading

Method overloading diterapkan melalui dua method `tampilkanInfo()` dengan parameter berbeda.

```java
public abstract void tampilkanInfo();
```

<img width="569" height="178" alt="image" src="https://github.com/user-attachments/assets/bd8476ba-3c6a-4073-b77e-20fc6c77eda9" />


Method pertama tidak memiliki parameter, sedangkan method kedua menerima parameter `int cekId`. Method kedua memeriksa ID sebelum memanggil method `tampilkanInfo()` sesuai jenis objek pasien.

---

## 🧱 7. Penerapan Abstraction

Abstraction diterapkan melalui abstract class `Pasien` dan abstract method `tampilkanInfo()`.

```java
public abstract class Pasien
```

Class `Pasien` menjadi dasar bagi seluruh jenis pasien dan tidak digunakan untuk membuat objek secara langsung.

Abstract method yang digunakan:

```java
public abstract void tampilkanInfo();
```

Method tersebut harus diimplementasikan oleh subclass `PasienUmum` dan `PasienBPJS`. Dengan abstraction, class induk menentukan perilaku dasar, sedangkan setiap subclass menentukan detail implementasinya.

---

## 🔌 8. Penerapan JDBC

JDBC (*Java Database Connectivity*) digunakan untuk menghubungkan program Java dengan database MySQL agar data pasien dapat disimpan dan dikelola secara permanen.

Penerapannya terdapat pada package `database` dan `dao`.

### A. Koneksi Database

File `KoneksiDatabase.java` mengatur koneksi ke database `klinik` menggunakan `DriverManager.getConnection()`.

<img width="787" height="417" alt="image" src="https://github.com/user-attachments/assets/0a9d6f02-e2f6-422f-9e77-f50c3498c11f" />


### B. Operasi Database

File `PasienDAO.java` menangani operasi SQL menggunakan beberapa class JDBC:

- `Connection` untuk mengelola koneksi database.
- `PreparedStatement` untuk menjalankan perintah SQL dengan parameter.
- `ResultSet` untuk membaca hasil query.
- `SQLException` untuk menangani kesalahan operasi SQL.

DAO menyediakan proses untuk menambah, mengambil, menampilkan, memperbarui, dan menghapus data pasien.

### C. Struktur Database

Database yang digunakan bernama `klinik` dan terdiri dari tiga tabel utama:

| Tabel | Fungsi |
|---|---|
| `pasien` | Menyimpan data dasar pasien. |
| `pasien_umum` | Menyimpan jenis pembayaran Pasien Umum. |
| `pasien_bpjs` | Menyimpan nomor BPJS pasien. |

Tabel `pasien_umum` dan `pasien_bpjs` terhubung ke tabel `pasien` menggunakan foreign key.

### D. Alur JDBC

```text
PasienController
       |
       v
   PasienCRUD
       |
       v
    PasienDAO
       |
       v
KoneksiDatabase
       |
       v
   MySQL
```

Dengan JDBC, perubahan data dilakukan langsung ke database. Data yang belum dihapus tetap tersimpan meskipun program ditutup.

---

## 🧩 9. Penerapan Interface

yang diterapkan adalah interface `Pembayaran` pada package `model`.

Interface tersebut mendefinisikan method:

<img width="342" height="153" alt="image" src="https://github.com/user-attachments/assets/672f4263-eb32-4f36-b2d8-450db027883b" />


Interface digunakan oleh class `PasienUmum`:

<img width="640" height="61" alt="image" src="https://github.com/user-attachments/assets/fc34110c-5239-45de-942d-af84e6f15df4" />


Class `PasienUmum` mengimplementasikan method `prosesPembayaran()` untuk menampilkan informasi jenis pembayaran pasien. Method ini digunakan ketika pasien umum dipanggil melalui Controller.

Penerapan interface membuat perilaku pembayaran didefinisikan melalui kontrak yang harus diimplementasikan oleh class terkait.

---

## 🏗️ 10. Penerapan MVC

Program menggunakan pola MVC untuk memisahkan data, tampilan, dan alur program.

| Komponen | Class/Package | Fungsi |
|---|---|---|
| Model | `model` | Merepresentasikan data dan perilaku pasien. |
| View | `PasienView.java` | Menampilkan menu, daftar pasien, dan pesan. |
| Controller | `PasienController.java` | Mengatur alur program dan pilihan pengguna. |

Selain MVC, terdapat dua lapisan tambahan:

- **Service:** `PasienCRUD.java` mengatur proses pengelolaan data pasien.
- **DAO:** `PasienDAO.java` menangani komunikasi dengan database melalui JDBC.

Pemisahan ini membuat tanggung jawab setiap bagian program lebih jelas dan memudahkan pemeliharaan kode.

---

## ✅ 11. Validasi Input

Validasi input diterapkan melalui class `ValidasiInput.java` pada package `helper`.

Validasi yang digunakan meliputi:

<img width="683" height="295" alt="image" src="https://github.com/user-attachments/assets/728a41b9-2b72-4e83-8f1c-18e3318b7fba" />

- **Nama pasien:** hanya menerima huruf dan spasi.

<img width="647" height="269" alt="image" src="https://github.com/user-attachments/assets/5ed217c4-4edb-4587-bc8f-e249578a437b" />

- **Umur pasien:** berada pada rentang 1–200 tahun.

<img width="1107" height="335" alt="image" src="https://github.com/user-attachments/assets/da01f703-b979-4d35-9700-df78ebb9be1e" />

- **Nomor telepon:** terdiri dari 9–13 digit angka.

<img width="787" height="286" alt="image" src="https://github.com/user-attachments/assets/29d2b59c-fbff-4be4-a450-336fc580e473" />

- **Nomor BPJS:** hanya menerima angka.

<img width="673" height="329" alt="image" src="https://github.com/user-attachments/assets/3fa54315-be0a-4e69-a247-c99da790f6f8" />


Jika input tidak sesuai, program meminta pengguna memasukkan kembali data yang benar.

---


## 📁 13. File Database

Repository menyertakan file `klinik.sql` yang berisi perintah SQL untuk membuat database dan tabel yang digunakan oleh program.

Sebelum menjalankan aplikasi, impor atau jalankan file `klinik.sql` melalui MySQL atau phpMyAdmin.

---
