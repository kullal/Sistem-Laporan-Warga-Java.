package Controller;

import DAO.PegawaiDAO;
import DAOInterface.PegawaiDAOInterface;
import Model.Pegawai;
import View.LoginPegawai; // Untuk referensi jika controller perlu berinteraksi lebih jauh dengan view
import View.MenuPegawai;
import javax.swing.JOptionPane;

/**
 * Controller untuk menangani logika login pegawai.
 */
public class LoginController {
    private PegawaiDAOInterface pegawaiDAO;
    private LoginPegawai viewLogin; // Referensi ke view, jika diperlukan interaksi balik

    public LoginController(LoginPegawai view) {
        this.pegawaiDAO = new PegawaiDAO();
        this.viewLogin = view; // Simpan referensi view
    }
    
    public LoginController() { // Constructor alternatif jika view tidak perlu direferensikan balik secara kompleks
        this.pegawaiDAO = new PegawaiDAO();
    }

    public boolean prosesLogin(String nip, String password) {
        if (nip == null || nip.trim().isEmpty() || password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(viewLogin, "NIP dan Password tidak boleh kosong!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Pegawai pegawai = pegawaiDAO.getPegawaiByNip(nip);

        if (pegawai != null) {
            // Untuk sekarang, perbandingan password langsung (plain text)
            // Di aplikasi production, ini harusnya perbandingan HASH password
            if (pegawai.getPassword_pegawai().equals(password)) {
                // Login Berhasil
                System.out.println("Login berhasil untuk NIP: " + nip);
                // Buka MenuPegawai
                MenuPegawai menuPegawaiForm = new MenuPegawai();
                menuPegawaiForm.setVisible(true);
                
                // Simpan NIP pegawai yang login
                LoginPegawai.setNipPegawaiLogin(nip);
                
                // Tutup form login (viewLogin)
                if (viewLogin != null) {
                    viewLogin.dispose();
                }
                return true;
            } else {
                // Password salah
                JOptionPane.showMessageDialog(viewLogin, "Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            // NIP tidak ditemukan
            JOptionPane.showMessageDialog(viewLogin, "NIP tidak ditemukan!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
} 