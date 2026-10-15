/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class PasienUmum extends Pasien implements Pembayaran{
    private String jenisPembayaran;

    public PasienUmum(String nama, int umur,
            String noTelepon, String jenisPembayaran){

        super(nama, umur, noTelepon);
        this.jenisPembayaran = jenisPembayaran;
    }
    public PasienUmum(int idPasien, String nama, int umur,
                  String noTelepon, String jenisPembayaran) {
    super(idPasien, nama, umur, noTelepon);
    this.jenisPembayaran = jenisPembayaran;
    }

    public String getJenisPembayaran(){
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran){
        this.jenisPembayaran = jenisPembayaran;
    }
    
    @Override
    public void prosesPembayaran(){
        System.out.println("Pembayaran pasien umum: " + jenisPembayaran);
    }

    @Override
    public String getInfoTambahan(){
        return "Pembayaran: " + jenisPembayaran;
    }
    
    @Override
    public void tampilkanInfo(){
        System.out.println("========================");
        System.out.println("Kategori Pasien Umum");
        System.out.println("ID Pasien  : " + getIdPasien());
        System.out.println("Nama       : " + getNama());
        System.out.println("Umur       : " + getUmur());
        System.out.println("No Telepon : " + getNoTelepon());
        System.out.println("Pembayaran : " + jenisPembayaran);
        System.out.println("========================");
    }
}
