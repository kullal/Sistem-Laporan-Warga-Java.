package DAOInterface;

import Model.Laporan;
import java.util.List;

/**
 * Interface untuk operasi CRUD pada data Laporan.
 */
public interface LaporanDAOInterface {
    
    /**
     * Menyimpan objek Laporan baru ke database.
     * @param l Objek Laporan yang akan disimpan.
     */
    public boolean insert(Laporan l);
    
    /**
     * Mengambil semua data Laporan dari database.
     * @return List dari objek Laporan.
     */
    public List<Laporan> getAll();
    
    /**
     * Mengupdate status sebuah laporan berdasarkan ID-nya.
     * Juga mencatat NIP pegawai yang melakukan update dan tanggal update.
     * @param idLaporan ID Laporan yang akan diupdate.
     * @param status_laporan Status baru untuk laporan.
     * @param nipPegawai NIP Pegawai yang melakukan update.
     */
    public boolean updateStatus(int idLaporan, String status_laporan, String nipPegawai);
    
    /**
     * Menghapus sebuah laporan dari database berdasarkan ID-nya.
     * @param idLaporan ID Laporan yang akan dihapus.
     */
    public boolean delete(int idLaporan);
    
    // Tambahkan method lain jika diperlukan di masa mendatang, contoh:
    // public Laporan getById(int idLaporan);
    // public List<Laporan> getByStatus(String status);
    // public List<Laporan> getByNikPelapor(String nik);
} 