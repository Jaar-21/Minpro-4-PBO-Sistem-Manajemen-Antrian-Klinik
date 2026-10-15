/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Pasien;
import java.util.List;

/**
 * @author Asus     
 */
public class PasienView {

    public void tampilkanMenu(){
        System.out.println("==========================================");
        System.out.println("      SISTEM MANAJEMEN PASIEN KLINIK");
        System.out.println("==========================================");
        System.out.println("1. Tampilkan Pasien");
        System.out.println("2. Tambahkan Pasien");
        System.out.println("3. Update Pasien");
        System.out.println("4. Menghapus Pasien");
        System.out.println("5. Panggil Pasien");
        System.out.println("6. Keluar");
    }


    public void tampilkanPilihanJenisPasien(){
        System.out.println("1. Pasien Umum");
        System.out.println("2. Pasien BPJS");
    }

    public void tampilkanDaftarPasien(List<Pasien> listPasien){
        if (listPasien.isEmpty()){
            System.out.println("belum ada data pasien");
        } else {
            System.out.println("===== DAFTAR PASIEN =====");
            System.out.printf("%-10s | %-20s | %-6s | %-15s | %-15s\n",
                    "ID", "Nama", "Umur", "NO Telepon", "Info Tambahan");
            for (Pasien pasien : listPasien){
                System.out.println(pasien);
            }
        }
    }

    public void pesan(String teks){
        System.out.println(teks);
    }
}
