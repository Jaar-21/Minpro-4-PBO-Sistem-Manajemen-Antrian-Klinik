/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class PasienBPJS extends Pasien {
    private String nomorBPJS;

    public PasienBPJS(String nama, int umur,
            String noTelepon, String nomorBPJS){

        super(nama, umur, noTelepon);
        this.nomorBPJS = nomorBPJS;
    }
    
    public PasienBPJS(int idPasien, String nama, int umur,
                      String noTelepon, String nomorBPJS) {
        super(idPasien, nama, umur, noTelepon);
        this.nomorBPJS = nomorBPJS;
    }


    public String getNomorBPJS(){
        return nomorBPJS;
    }

    public void setNomorBPJS(String nomorBPJS){
        if (nomorBPJS != null && nomorBPJS.matches("\\d+")) {
            this.nomorBPJS = nomorBPJS;
        } else {
            System.out.println("Nomor BPJS hanya boleh berupa angka");
        }
    }

    @Override
    public String getInfoTambahan(){
        return "BPJS: " + nomorBPJS;
    }
    @Override
    public void tampilkanInfo(){
        System.out.println("========================");
        System.out.println("Kategori Pasien BPJS");
        System.out.println("ID Pasien  : " + getIdPasien());
        System.out.println("Nama       : " + getNama());
        System.out.println("Umur       : " + getUmur());
        System.out.println("No Telepon : " + getNoTelepon());
        System.out.println("Nomor BPJS : " + nomorBPJS);
        System.out.println("========================");
    }
}
