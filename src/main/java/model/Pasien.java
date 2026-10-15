/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public abstract class Pasien {
    public static int hitungId = 1;
    protected final int idPasien;
    private String nama;
    private int umur;
    private String noTelepon;

    public Pasien( String nama, int umur, String noTelepon){
        this.idPasien = hitungId++;
        this.nama = nama;
        this.umur = umur;
        this.noTelepon = noTelepon;
    }
    
    public Pasien(int idPasien, String nama, int umur, String noTelepon){
    this.idPasien = idPasien;
    this.nama = nama;
    this.umur = umur;
    this.noTelepon = noTelepon;
    }

    public int getIdPasien(){
        return idPasien;
    }

    public final String getNama(){
        return nama;
    }

    public void setNama(String nama){
        if (nama != null && !nama.isEmpty() && nama.matches("[a-zA-Z ]+")) {
            this.nama = nama;
        } else {
            System.out.println("Nama hanya boleh mengandung huruf");
        }
    }

    public int getUmur(){
        return umur;
    }

    public void setUmur(int umur){
        if (umur > 0 && umur <= 200) {
            this.umur = umur;
        } else {
            System.out.println("Umur harus antara 1 - 200");
        }
    }

    public String getNoTelepon(){
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon){
        if (noTelepon != null && noTelepon.matches("\\d{9,13}")) {
            this.noTelepon = noTelepon;
        } else {
            System.out.println("Nomor telepon harus berupa angka dan terdiri dari 9-13 digit");
        }
    }
    
    public abstract void tampilkanInfo();
    
    public void tampilkanInfo(int cekId){
    if (cekId == idPasien){
        tampilkanInfo();
    } else {
        System.out.println("ID pasien tidak sesuai");
    }
}

    public String getInfoTambahan(){
        return "-";
    }


    @Override
    public String toString(){
        return String.format("%-10d | %-20s | %-6d | %-15s | %s",
                idPasien, nama, umur, noTelepon, getInfoTambahan());
    }
    
    
}
