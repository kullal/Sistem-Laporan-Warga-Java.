package DAO;

import DAOInterface.WargaDAOInterface;
import Helper.KoneksiDB;
import Model.Warga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Bisa digunakan jika tidak ada parameter
import java.sql.Timestamp; // Untuk konversi Date ke Timestamp SQL
import java.util.Date; // Untuk tanggal_registrasi

/**
 * Implementasi dari WargaDAOInterface untuk operasi database pada data Warga.
 */
public class WargaDAO implements WargaDAOInterface {
    // SQL Commands
    private static final String INSERT_WARGA = "INSERT INTO warga (nik, nama_warga, tanggal_registrasi) VALUES (?, ?, ?)";
    private static final String SELECT_WARGA_BY_NIK = "SELECT * FROM warga WHERE nik = ?";

    public WargaDAO() {
        // Tidak perlu inisialisasi koneksi di sini
    }

    @Override
    public void insert(Warga w) {
        PreparedStatement statement = null;
        Connection con = null;
        try {
            con = Helper.KoneksiDB.getConnection();
            statement = con.prepareStatement(INSERT_WARGA);
            statement.setString(1, w.getNik());
            statement.setString(2, w.getNama_warga());
            
            // Jika tanggal registrasi di set di objek Warga, gunakan itu.
            // Jika tidak, biarkan database menggunakan DEFAULT CURRENT_TIMESTAMP (jika kolomnya diset demikian)
            // atau set secara manual ke waktu sekarang.
            if (w.getTanggal_registrasi() != null) {
                statement.setTimestamp(3, new Timestamp(w.getTanggal_registrasi().getTime()));
            } else {
                statement.setTimestamp(3, new Timestamp(new Date().getTime())); // Waktu saat ini
            }
            
            statement.executeUpdate();
            System.out.println("Data warga berhasil dimasukkan: " + w.getNik());

        } catch (SQLException e) {
            System.err.println("Error saat memasukkan data warga: " + e.getMessage());
            // e.printStackTrace(); // Uncomment untuk detail error
        } finally {
            try {
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
            }
            // Sebaiknya koneksi tidak ditutup di sini agar bisa digunakan method lain
            // Penutupan koneksi idealnya dilakukan di akhir penggunaan aplikasi atau saat tidak dibutuhkan lagi
        }
    }

    @Override
    public Warga getWargaByNik(String nik) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Warga warga = null;
        Connection con = null;
        try {
            con = Helper.KoneksiDB.getConnection();
            statement = con.prepareStatement(SELECT_WARGA_BY_NIK);
            statement.setString(1, nik);
            rs = statement.executeQuery();

            if (rs.next()) {
                warga = new Warga();
                warga.setNik(rs.getString("nik"));
                warga.setNama_warga(rs.getString("nama_warga"));
                warga.setTanggal_registrasi(rs.getTimestamp("tanggal_registrasi")); // getTimestamp mengambil java.sql.Timestamp, yang merupakan subclass dari java.util.Date
                System.out.println("Data warga ditemukan: " + nik);
            } else {
                System.out.println("Data warga dengan NIK " + nik + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data warga: " + e.getMessage());
            // e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
            }
        }
        return warga;
    }
} 