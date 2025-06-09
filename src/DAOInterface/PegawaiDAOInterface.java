package DAOInterface;

import Model.Pegawai;
// import java.util.List; // Jika nanti ada getAllPegawai

/**
 * Interface untuk operasi pada data Pegawai.
 */
public interface PegawaiDAOInterface {
    
    /**
     * Mengambil data Pegawai berdasarkan NIP.
     * Method ini bisa digunakan sebagai dasar untuk validasi login.
     * @param nip NIP Pegawai yang dicari.
     * @return Objek Pegawai jika ditemukan, null jika tidak.
     */
    public Pegawai getPegawaiByNip(String nip);
    
    // Tambahkan method lain jika diperlukan di masa mendatang, contoh:
    // public void insert(Pegawai p);
    // public void update(Pegawai p);
    // public void delete(String nip);
    // public List<Pegawai> getAll();
    // public boolean validateLogin(String nip, String password); // Alternatif
} 