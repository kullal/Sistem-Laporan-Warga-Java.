package DAO;

import DAOInterface.PegawaiDAOInterface;
import Helper.KoneksiDB;
import Model.Pegawai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementasi dari PegawaiDAOInterface untuk operasi database pada data Pegawai.
 */
public class PegawaiDAO implements PegawaiDAOInterface {
    Connection con;
    // SQL Commands - sesuaikan nama tabel dan kolom jika berbeda di database Anda
    private static final String SELECT_PEGAWAI_BY_NIP = "SELECT nip, nama_pegawai, password_pegawai, tanggal_dibuat FROM pegawai WHERE nip = ?";

    public PegawaiDAO() {
        con = KoneksiDB.getConnection(); // Dapatkan koneksi saat DAO dibuat
    }

    @Override
    public Pegawai getPegawaiByNip(String nip) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Pegawai pegawai = null;

        if (con == null) {
            System.err.println("Koneksi ke database belum diinisialisasi di PegawaiDAO.");
            return null;
        }

        try {
            statement = con.prepareStatement(SELECT_PEGAWAI_BY_NIP);
            statement.setString(1, nip);
            rs = statement.executeQuery();

            if (rs.next()) {
                pegawai = new Pegawai();
                pegawai.setNip(rs.getString("nip"));
                pegawai.setNama_pegawai(rs.getString("nama_pegawai"));
                pegawai.setPassword_pegawai(rs.getString("password_pegawai")); // Ambil password (mentah dari DB)
                pegawai.setTanggal_dibuat(rs.getTimestamp("tanggal_dibuat"));
                
                System.out.println("Data pegawai ditemukan: " + nip);
            } else {
                System.out.println("Data pegawai dengan NIP " + nip + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data pegawai: " + e.getMessage());
            // e.printStackTrace(); // Uncomment untuk detail error
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // e.printStackTrace();
            }
            // Koneksi tidak ditutup di sini agar bisa digunakan kembali
        }
        return pegawai;
    }
    
    // Jika Anda butuh method lain seperti insertPegawai, dll., bisa ditambahkan di sini
    // dan di PegawaiDAOInterface.
} 