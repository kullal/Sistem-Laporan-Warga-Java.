package DAO;

import DAOInterface.LaporanDAOInterface;
import Helper.KoneksiDB;
import Model.Laporan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementasi dari LaporanDAOInterface untuk operasi database pada data Laporan.
 */
public class LaporanDAO implements LaporanDAOInterface {

    @Override
    public boolean insert(Laporan l) {
        String sql = "INSERT INTO laporan (nik_pelapor, jenis_laporan, detail_laporan, tanggal_kejadian, tanggal_lapor, status_laporan) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, l.getNik_pelapor());
            pstmt.setString(2, l.getJenis_laporan());
            pstmt.setString(3, l.getDetail_laporan());
            pstmt.setString(4, l.getTanggal_kejadian());

            pstmt.setString(5, l.getTanggal_lapor());

            if (l.getStatus_laporan() == null || l.getStatus_laporan().isEmpty()) {
                pstmt.setString(6, "Pending");
            } else {
                pstmt.setString(6, l.getStatus_laporan());
            }
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        l.setId_laporan(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat insert laporan: " + e.getMessage());
            // e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Laporan> getAll() {
        List<Laporan> listLaporan = new ArrayList<>();
        String sql = "SELECT * FROM laporan ORDER BY tanggal_lapor DESC";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Laporan lap = new Laporan();
                lap.setId_laporan(rs.getInt("id_laporan"));
                lap.setNik_pelapor(rs.getString("nik_pelapor"));
                lap.setJenis_laporan(rs.getString("jenis_laporan"));
                lap.setDetail_laporan(rs.getString("detail_laporan"));
                lap.setTanggal_kejadian(rs.getString("tanggal_kejadian"));
                lap.setTanggal_lapor(rs.getString("tanggal_lapor"));
                lap.setStatus_laporan(rs.getString("status_laporan"));
                lap.setNip_pegawai_penanggung_jawab(rs.getString("nip_pegawai_penanggung_jawab"));
                lap.setCatatan_pegawai(rs.getString("catatan_pegawai"));
                lap.setTanggal_update_status(rs.getString("tanggal_update_status"));
                listLaporan.add(lap);
            }
        } catch (SQLException e) {
            System.err.println("Error saat get all laporan: " + e.getMessage());
            // e.printStackTrace();
        }
        return listLaporan;
    }

    @Override
    public boolean updateStatus(int idLaporan, String status_laporan, String nipPegawai) {
        String sql = "UPDATE laporan SET status_laporan = ?, nip_pegawai_penanggung_jawab = ?, tanggal_update_status = ? WHERE id_laporan = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status_laporan);
            pstmt.setString(2, nipPegawai);
            
            SimpleDateFormat sdfUpdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pstmt.setString(3, sdfUpdate.format(new Date()));
            
            pstmt.setInt(4, idLaporan);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error saat update status laporan: " + e.getMessage());
            // e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idLaporan) {
        String sql = "DELETE FROM laporan WHERE id_laporan = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idLaporan);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus laporan: " + e.getMessage());
        }
        return false;
    }
} 