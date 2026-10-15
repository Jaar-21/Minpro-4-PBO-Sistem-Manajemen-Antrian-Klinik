/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
/**
 * @author Asus
 */


import dao.PasienDAO;
import java.util.List;
import model.Pasien;
import model.PasienUmum;
import model.PasienBPJS;


public class PasienCRUD {

    private PasienDAO dao = new PasienDAO();

    public boolean cekIdPasien(int idPasien) {
        for (Pasien pasien : dao.getSemuaPasien()) {
            if (pasien.getIdPasien() == idPasien) {
                return true;
            }
        }
        return false;
    }

    public boolean tambahPasienUmum(PasienUmum pasien) {
        return dao.tambahPasienUmum(pasien);
    }

    public boolean tambahPasienBPJS(PasienBPJS pasien) {
        return dao.tambahPasienBPJS(pasien);
    }

    public List<Pasien> getListPasien() {
        return dao.getSemuaPasien();
    }

    public boolean hapusPasien(int idPasien) {
        return dao.hapusPasien(idPasien);
    }

    public boolean updatePasien(
            int idPasien, String namaBaru, int umurBaru,
            String noTeleponBaru) {
        return dao.updatePasien(
                idPasien, namaBaru, umurBaru, noTeleponBaru);
    }

    public Pasien panggilPasien(int idPasien) {
        Pasien pasien = dao.ambilPasien(idPasien);

        if (pasien != null) {
            boolean berhasil = dao.hapusPasien(idPasien);

            if (berhasil) {
                return pasien;
            }
        }

        return null;
    }
}
