/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Asus
 */
import model.Pasien;
import model.PasienUmum;
import model.PasienBPJS;
import database.KoneksiDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasienDAO {

    
public boolean tambahPasienUmum(PasienUmum pasien) {
    String sqlPasien = "INSERT INTO pasien (nama, umur, no_telepon) VALUES (?, ?, ?)";
    String sqlUmum = "INSERT INTO pasien_umum (id_pasien, jenis_pembayaran) VALUES (?, ?)";

    try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
        if (koneksi == null) {
            return false;
        }

        try {
            koneksi.setAutoCommit(false);

            try (PreparedStatement psPasien = koneksi.prepareStatement(
                    sqlPasien, java.sql.Statement.RETURN_GENERATED_KEYS)) {

                psPasien.setString(1, pasien.getNama());
                psPasien.setInt(2, pasien.getUmur());
                psPasien.setString(3, pasien.getNoTelepon());
                psPasien.executeUpdate();

                try (ResultSet rs = psPasien.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("ID pasien gagal dibuat.");
                    }

                    int idPasien = rs.getInt(1);

                    try (PreparedStatement psUmum = koneksi.prepareStatement(sqlUmum)) {
                        psUmum.setInt(1, idPasien);
                        psUmum.setString(2, pasien.getJenisPembayaran());
                        psUmum.executeUpdate();
                    }
                }
            }

            koneksi.commit();
            return true;

        } catch (SQLException e) {
            koneksi.rollback();
            System.out.println("Gagal menambahkan pasien umum: " + e.getMessage());
            return false;

        } finally {
            koneksi.setAutoCommit(true);
        }

    } catch (SQLException e) {
        System.out.println("Gagal mengakses database: " + e.getMessage());
        return false;
    }
}

public boolean tambahPasienBPJS(PasienBPJS pasien) {
    String sqlPasien = "INSERT INTO pasien (nama, umur, no_telepon) VALUES (?, ?, ?)";
    String sqlBPJS = "INSERT INTO pasien_bpjs (id_pasien, nomor_bpjs) VALUES (?, ?)";

    try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
        if (koneksi == null) {
            return false;
        }

        try {
            koneksi.setAutoCommit(false);

            try (PreparedStatement psPasien = koneksi.prepareStatement(
                    sqlPasien, java.sql.Statement.RETURN_GENERATED_KEYS)) {

                psPasien.setString(1, pasien.getNama());
                psPasien.setInt(2, pasien.getUmur());
                psPasien.setString(3, pasien.getNoTelepon());
                psPasien.executeUpdate();

                try (ResultSet rs = psPasien.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("ID pasien gagal dibuat.");
                    }

                    int idPasien = rs.getInt(1);

                    try (PreparedStatement psBPJS = koneksi.prepareStatement(sqlBPJS)) {
                        psBPJS.setInt(1, idPasien);
                        psBPJS.setString(2, pasien.getNomorBPJS());
                        psBPJS.executeUpdate();
                    }
                }
            }

            koneksi.commit();
            return true;

        } catch (SQLException e) {
            koneksi.rollback();
            System.out.println("Gagal menambahkan pasien BPJS: " + e.getMessage());
            return false;

        } finally {
            koneksi.setAutoCommit(true);
        }

    } catch (SQLException e) {
        System.out.println("Gagal mengakses database: " + e.getMessage());
        return false;
    }
}

    // Menghapus data pasien
    public boolean hapusPasien(int idPasien) {
        String sql = "DELETE FROM pasien WHERE id_pasien = ?";

        try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
            if (koneksi == null) {
                return false;
            }

            try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
                ps.setInt(1, idPasien);

                int hasil = ps.executeUpdate();
                return hasil > 0;
            }

        } catch (SQLException e) {
            System.out.println("Gagal menghapus pasien: " + e.getMessage());
            return false;
        }
    }

    // Memperbarui data pasien
    public boolean updatePasien(int idPasien, String namaBaru,
                                int umurBaru, String noTeleponBaru) {
        String sql = "UPDATE pasien SET nama = ?, umur = ?, no_telepon = ? "
                   + "WHERE id_pasien = ?";

        try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
            if (koneksi == null) {
                return false;
            }

            try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
                ps.setString(1, namaBaru);
                ps.setInt(2, umurBaru);
                ps.setString(3, noTeleponBaru);
                ps.setInt(4, idPasien);

                int hasil = ps.executeUpdate();
                return hasil > 0;
            }

        } catch (SQLException e) {
            System.out.println("Gagal memperbarui pasien: " + e.getMessage());
            return false;
        }
    }

    // Mengambil data pasien berdasarkan ID untuk fitur panggil pasien
    public Pasien ambilPasien(int idPasien) {
        String sql = "SELECT p.id_pasien, p.nama, p.umur, p.no_telepon, "
                   + "pu.jenis_pembayaran, pb.nomor_bpjs "
                   + "FROM pasien p "
                   + "LEFT JOIN pasien_umum pu ON p.id_pasien = pu.id_pasien "
                   + "LEFT JOIN pasien_bpjs pb ON p.id_pasien = pb.id_pasien "
                   + "WHERE p.id_pasien = ?";

        try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
            if (koneksi == null) {
                return null;
            }

            try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
                ps.setInt(1, idPasien);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id_pasien");
                        String nama = rs.getString("nama");
                        int umur = rs.getInt("umur");
                        String noTelepon = rs.getString("no_telepon");

                        String jenisPembayaran =
                                rs.getString("jenis_pembayaran");
                        String nomorBPJS = rs.getString("nomor_bpjs");

                        if (jenisPembayaran != null) {
                            return new PasienUmum(
                                    id, nama, umur, noTelepon,
                                    jenisPembayaran);
                        } else if (nomorBPJS != null) {
                            return new PasienBPJS(
                                    id, nama, umur, noTelepon,
                                    nomorBPJS);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil pasien: " + e.getMessage());
        }

        return null;
    }



public List<Pasien> getSemuaPasien() {
    List<Pasien> daftarPasien = new ArrayList<>();

    String sql = "SELECT p.id_pasien, p.nama, p.umur, p.no_telepon, " +
                 "pu.jenis_pembayaran, pb.nomor_bpjs " +
                 "FROM pasien p " +
                 "LEFT JOIN pasien_umum pu ON p.id_pasien = pu.id_pasien " +
                 "LEFT JOIN pasien_bpjs pb ON p.id_pasien = pb.id_pasien";

    try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
        if (koneksi == null) {
            return daftarPasien;
        }

        try (PreparedStatement ps = koneksi.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_pasien");
                String nama = rs.getString("nama");
                int umur = rs.getInt("umur");
                String noTelepon = rs.getString("no_telepon");

                String jenisPembayaran = rs.getString("jenis_pembayaran");
                String nomorBPJS = rs.getString("nomor_bpjs");

                if (jenisPembayaran != null) {
                    daftarPasien.add(new PasienUmum(
                            id, nama, umur, noTelepon, jenisPembayaran));
                } else if (nomorBPJS != null) {
                    daftarPasien.add(new PasienBPJS(
                            id, nama, umur, noTelepon, nomorBPJS));
                }
            }
        }

    } catch (SQLException e) {
        System.out.println("Gagal mengambil data pasien: " + e.getMessage());
    }

    return daftarPasien;
}

}

