package Controller;

import DAO.LaporanDAO;
import DAOInterface.LaporanDAOInterface;
import Model.Laporan;
import View.IsiLaporanWarga; // Opsional, jika controller perlu referensi spesifik
import View.MenuPegawai;     // Opsional, jika controller perlu referensi spesifik
import javax.swing.JOptionPane;
// import java.util.Date; // Tidak digunakan untuk tanggal kejadian dan lapor lagi
import java.util.List;
import java.util.ArrayList; // Untuk list kosong jika terjadi error
import java.text.SimpleDateFormat; // Untuk format tanggal lapor
import java.util.Date; // Masih dibutuhkan untuk new Date() saat generate tanggal lapor

/**
 * Controller untuk menangani logika terkait laporan.
 */
public class LaporanController {
    private LaporanDAOInterface laporanDAO;
    // Tidak menyimpan referensi view secara default agar lebih fleksibel,
    // View bisa menampilkan JOptionPane sendiri atau controller bisa menerima view sebagai parameter jika perlu.

    public LaporanController() {
        this.laporanDAO = new LaporanDAO();
    }

    /**
     * Menangani submit laporan baru dari warga.
     * @param nikPelapor NIK warga yang melapor.
     * @param namaPelapor Nama warga yang melapor.
     * @param jenisLaporan Jenis laporan.
     * @param detailLaporan Detail dari laporan.
     * @param tanggalKejadianStr Tanggal kejadian dalam format String.
     * @param viewAsal Referensi ke view pemanggil untuk menampilkan pesan (opsional).
     * @return true jika berhasil, false jika gagal.
     */
    public boolean submitLaporanBaru(String nikPelapor, String namaPelapor, String jenisLaporan, String detailLaporan, String tanggalKejadianStr, IsiLaporanWarga viewAsal) {
        if (nikPelapor == null || nikPelapor.trim().isEmpty() || 
            namaPelapor == null || namaPelapor.trim().isEmpty() || 
            jenisLaporan == null || jenisLaporan.trim().isEmpty() || 
            detailLaporan == null || detailLaporan.trim().isEmpty() || 
            tanggalKejadianStr == null || tanggalKejadianStr.trim().isEmpty()) { 
            JOptionPane.showMessageDialog(viewAsal, "Semua field (NIK, Nama, Jenis, Detail, Tanggal YYYY-MM-DD) harus diisi!", "Submit Laporan Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // (Opsional) Validasi format tanggalKejadianStr (misal, yyyy-MM-dd)
        // try {
        //     java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        //     sdf.setLenient(false); // Agar formatnya ketat
        //     sdf.parse(tanggalKejadianStr);
        // } catch (java.text.ParseException e) {
        //     JOptionPane.showMessageDialog(viewAsal, "Format tanggal kejadian tidak valid. Gunakan YYYY-MM-DD.", "Format Tanggal Salah", JOptionPane.ERROR_MESSAGE);
        //     return false;
        // }

        Laporan laporanBaru = new Laporan();
        laporanBaru.setNik_pelapor(nikPelapor.trim());
        laporanBaru.setNama_pelapor(namaPelapor.trim());
        laporanBaru.setJenis_laporan(jenisLaporan);
        laporanBaru.setDetail_laporan(detailLaporan.trim());
        laporanBaru.setTanggal_kejadian(tanggalKejadianStr); // Simpan sebagai String
        
        // Buat tanggal_lapor sebagai String "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdfLapor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        laporanBaru.setTanggal_lapor(sdfLapor.format(new Date())); 
        
        laporanBaru.setStatus_laporan("Pending");

        try {
            laporanDAO.insert(laporanBaru);
            JOptionPane.showMessageDialog(viewAsal, 
                                        "Laporan berhasil dikirim!\nID Laporan: " + laporanBaru.getId_laporan(),
                                        "Submit Laporan Sukses", 
                                        JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAsal, "Terjadi kesalahan saat menyimpan laporan.", "Error Database", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error saat insert laporan di controller: " + e.getMessage());
            return false;
        }
    }

    /**
     * Mengambil semua laporan untuk ditampilkan (misalnya di Menu Pegawai).
     * @return List objek Laporan, atau list kosong jika ada error.
     */
    public List<Laporan> getListSemuaLaporan() {
        try {
            return laporanDAO.getAll();
        } catch (Exception e) {
            System.err.println("Error saat mengambil semua laporan di controller: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }

    /**
     * Mengupdate status laporan.
     * @param idLaporan ID laporan yang akan diupdate.
     * @param statusBaru Status baru.
     * @param nipPegawai NIP pegawai yang melakukan update.
     * @param viewAsal Referensi ke view pemanggil untuk menampilkan pesan (opsional).
     * @return true jika berhasil, false jika gagal.
     */
    public boolean prosesUpdateStatus(int idLaporan, String statusBaru, String nipPegawai, MenuPegawai viewAsal) {
        if (statusBaru == null || statusBaru.trim().isEmpty() || 
            nipPegawai == null || nipPegawai.trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewAsal, "Status baru dan NIP Pegawai harus diisi!", "Update Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // NIP Pegawai juga divalidasi atau didapat dari sesi login pegawai

        try {
            // Tanggal update status akan di-generate sebagai String di DAO
            laporanDAO.updateStatus(idLaporan, statusBaru, nipPegawai);
            return true; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAsal, "Terjadi kesalahan saat mengupdate status laporan.", "Error Database", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error saat update status laporan di controller: " + e.getMessage());
            return false;
        }
    }

    /**
     * Menghapus laporan berdasarkan ID.
     * @param idLaporan ID laporan yang akan dihapus.
     * @param viewAsal Referensi ke view pemanggil untuk notifikasi.
     * @return true jika berhasil, false jika gagal.
     */
    public boolean hapusLaporan(int idLaporan, MenuPegawai viewAsal) {
        try {
            boolean sukses = laporanDAO.delete(idLaporan);
            if (sukses) {
                JOptionPane.showMessageDialog(viewAsal, "Laporan berhasil dihapus!", "Hapus Laporan", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(viewAsal, "Gagal menghapus laporan!", "Hapus Laporan", JOptionPane.ERROR_MESSAGE);
            }
            return sukses;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewAsal, "Terjadi kesalahan saat menghapus laporan.", "Error Database", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error saat hapus laporan di controller: " + e.getMessage());
            return false;
        }
    }
} 