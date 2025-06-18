package Controller;

import DAO.WargaDAO;
import DAOInterface.WargaDAOInterface;
import Model.Warga;
import View.RegistrasiWarga; // Untuk referensi jika controller perlu berinteraksi lebih jauh dengan view
import View.IsiLaporanWarga;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 * Controller untuk menangani logika registrasi warga.
 */
public class RegistrasiController {
    private WargaDAOInterface wargaDAO;
    private RegistrasiWarga viewRegistrasi; // Referensi ke view

    public RegistrasiController(RegistrasiWarga view) {
        this.wargaDAO = new WargaDAO();
        this.viewRegistrasi = view;
    }
    
    // Constructor alternatif jika tidak selalu butuh referensi balik ke view secara detail
    public RegistrasiController() {
        this.wargaDAO = new WargaDAO();
    }

    public boolean prosesRegistrasi(String nik, String nama) {
        // 1. Validasi input dasar
        if (nik == null || nik.trim().isEmpty() || nama == null || nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(viewRegistrasi, "NIK dan Nama tidak boleh kosong!", "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // (Opsional) Validasi format NIK (misal panjangnya harus 16)
//        if (nik.trim().length() != 9) {
//            JOptionPane.showMessageDialog(viewRegistrasi, "NIK harus terdiri dari 9 digit!", "Format NIK Salah", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }

        // 2. Cek apakah NIK sudah terdaftar
        if (wargaDAO.getWargaByNik(nik.trim()) != null) {
            JOptionPane.showMessageDialog(viewRegistrasi, "NIK sudah terdaftar! Silakan gunakan NIK lain.", "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 3. Jika NIK belum terdaftar, buat objek Warga dan simpan
        Warga wargaBaru = new Warga();
        wargaBaru.setNik(nik.trim());
        wargaBaru.setNama_warga(nama.trim());
        wargaBaru.setTanggal_registrasi(new Date()); // Set tanggal registrasi saat ini

        try {
            wargaDAO.insert(wargaBaru);
            JOptionPane.showMessageDialog(viewRegistrasi, 
                                        "Registrasi Berhasil! Selamat datang, " + nama.trim() + ".", 
                                        "Registrasi Sukses", 
                                        JOptionPane.INFORMATION_MESSAGE);
            
            // Navigasi ke form IsiLaporanWarga dan tutup form registrasi
            IsiLaporanWarga isiLaporanForm = new IsiLaporanWarga(wargaBaru.getNik());
            isiLaporanForm.setVisible(true);
            
            if (viewRegistrasi != null) {
                viewRegistrasi.dispose();
            }
            return true;

        } catch (Exception e) { // Tangkap exception umum jika insert gagal karena alasan lain
            JOptionPane.showMessageDialog(viewRegistrasi, "Terjadi kesalahan saat menyimpan data registrasi.", "Error Database", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error saat insert warga di controller: " + e.getMessage());
            // e.printStackTrace();
            return false;
        }
    }
} 