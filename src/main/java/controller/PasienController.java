/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.PasienCRUD;
import helper.ValidasiInput;
import model.Pasien;
import model.PasienUmum;
import model.PasienBPJS;
import view.PasienView;
import java.util.Scanner;


public class PasienController {

    private PasienCRUD crud;
    private PasienView view;
    private Scanner scanner;

    public PasienController() {
        crud = new PasienCRUD();
        view = new PasienView();
        scanner = new Scanner(System.in);
    }

    public void jalankanProgram() {
        boolean berjalan = true;

        while (berjalan) {
            view.tampilkanMenu();

            int pilihan = ValidasiInput.inputInteger(scanner, "Pilih menu 1-6 : ");

            switch (pilihan) {

                case 1:
                    view.tampilkanDaftarPasien(crud.getListPasien());
                    break;
                    
                case 2:
                    view.tampilkanDaftarPasien(crud.getListPasien());
                    view.pesan("=====TAMBAH PASIEN=====");
                    String nama = ValidasiInput.inputNama(
                            scanner, "masukkan nama pasien : ");
                    int umur = ValidasiInput.inputUmur(
                            scanner, "Masukkan umur pasien : ");
                    String noTelepon = ValidasiInput.inputNoTelepon(
                            scanner, "masukkan no telepon : ");

                    view.tampilkanPilihanJenisPasien();

                    int jenis;
                    while (true) {
                        jenis = ValidasiInput.inputInteger(
                                scanner, "Pilih jenis pasien : ");

                        if (jenis == 1 || jenis == 2) {
                            break;
                        }
                        view.pesan("Jenis pasien tidak valid");
                    }

                    if (jenis == 1) {

                        int pembayaran;
                        while (true) {
                            System.out.println("1. Tunai");
                            System.out.println("2. Transfer");

                            pembayaran = ValidasiInput.inputInteger(
                                    scanner, "Pilih pembayaran : ");

                            if (pembayaran == 1 || pembayaran == 2) {                                
                                break;                               
                            }
                            view.pesan("Pilihan pembayaran tidak valid");
                        }

                        String jenisPembayaran;
                        if (pembayaran == 1) {
                            jenisPembayaran = "Tunai";
                        } else {
                            jenisPembayaran = "Transfer";
                        }

                        PasienUmum pasienUmumBaru = new PasienUmum(
                                nama, umur, noTelepon, jenisPembayaran);
                        
                        boolean berhasil = crud.tambahPasienUmum(pasienUmumBaru);

                        if (berhasil) {
                            view.pesan("Pasien umum berhasil ditambahkan");
                        } else {
                            view.pesan("Gagal menambahkan pasien umum");
                        }


                    } else if (jenis == 2) {

                        String nomorBPJS = ValidasiInput.inputNomorBPJS(
                                scanner, "Masukkan nomor BPJS : ");

                        PasienBPJS pasienBPJSBaru = new PasienBPJS(
                                nama, umur, noTelepon, nomorBPJS);
                        
                        boolean berhasil = crud.tambahPasienBPJS(pasienBPJSBaru);

                        if (berhasil) {
                            view.pesan("Pasien BPJS berhasil ditambahkan");
                        } else {
                            view.pesan("Gagal menambahkan pasien BPJS");
                        }


                    }
                    break;

                case 3:
                    view.tampilkanDaftarPasien(crud.getListPasien());
                    view.pesan("=====UPDATE PASIEN=====");

                    int idUpdate = ValidasiInput.inputInteger(
                            scanner, "Masukkan ID Pasien : ");

                    if (!crud.cekIdPasien(idUpdate)) {
                        view.pesan("ID pasien tidak ditemukan");
                        break;
                    }

                    String namaBaru = ValidasiInput.inputNama(
                            scanner, "Nama baru : ");

                    int umurBaru = ValidasiInput.inputUmur(
                            scanner, "Masukkan umur pasien : ");

                    String noTeleponBaru = ValidasiInput.inputNoTelepon(
                            scanner, "nomor telepon baru : ");

                    boolean updateBerhasil = crud.updatePasien(
                            idUpdate, namaBaru, umurBaru, noTeleponBaru);

                    view.pesan(updateBerhasil
                            ? "pasien berhasil diupdate"
                            : "ID pasien tidak ditemukan");
                    break;

                case 4:
                    view.tampilkanDaftarPasien(crud.getListPasien());
                    view.pesan("=====HAPUS DATA PASIEN=====");

                    int idHapus = ValidasiInput.inputInteger(
                            scanner,
                            "Masukkan ID Pasien yang ingin dihapus : ");

                    boolean hapusBerhasil = crud.hapusPasien(idHapus);

                    view.pesan(hapusBerhasil
                            ? "pasien berhasil dihapus"
                            : "ID pasien tidak ditemukan");
                    break;

                case 5:
                    view.tampilkanDaftarPasien(crud.getListPasien());
                    view.pesan("=====PANGGIL PASIEN=====");

                    int idPanggil = ValidasiInput.inputInteger(
                            scanner, "Masukkan ID Pasien : ");

                    Pasien pasienDipanggil = crud.panggilPasien(idPanggil);

                    if (pasienDipanggil != null) {
                        pasienDipanggil.tampilkanInfo(idPanggil);
                        
                        if (pasienDipanggil instanceof PasienUmum) {
                            ((PasienUmum) pasienDipanggil).prosesPembayaran();
                        }
                        
                        view.pesan(
                                "Panggilan atas nama Pasien " + pasienDipanggil.getNama() + " Silahkan memasuki ruangan");
                    } else {
                        view.pesan("ID pasien tidak ditemukan");
                    }
                    break;

                case 6:
                    berjalan = false;
                    view.pesan("program selesai");
                    break;

                default:
                    view.pesan("pilihan tidak valid");
            }
        }

        scanner.close();
    }
}
