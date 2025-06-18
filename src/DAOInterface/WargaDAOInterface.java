package DAOInterface;

import Model.Warga;
// import java.util.List; // Jika nanti ada getAllWarga

/**
 * Interface untuk operasi CRUD pada data Warga.
 */
public interface WargaDAOInterface {
    
    /**
     * Menyimpan objek Warga baru ke database.
     * @param w Objek Warga yang akan disimpan.
     */
    public void insert(Warga w);
    
    /**
     * Mengambil data Warga berdasarkan NIK.
     * @param nik NIK Warga yang dicari.
     * @return Objek Warga jika ditemukan, null jika tidak.
     */
    public Warga getWargaByNik(String nik);
    
    // Tambahkan method lain jika diperlukan di masa mendatang, contoh:
    // public void update(Warga w);
    // public void delete(String nik);
    // public List<Warga> getAll();
} 