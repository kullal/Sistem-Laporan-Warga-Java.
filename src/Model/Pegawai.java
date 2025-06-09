package Model;

import java.util.Date;

/**
 * Kelas Model untuk merepresentasikan data Pegawai.
 */
public class Pegawai {
    private String nip;
    private String nama_pegawai;
    private String password_pegawai; // Di model disimpan sebagai String
    private Date tanggal_dibuat;

    // Constructor
    public Pegawai() {
    }

    public Pegawai(String nip, String nama_pegawai, String password_pegawai, Date tanggal_dibuat) {
        this.nip = nip;
        this.nama_pegawai = nama_pegawai;
        this.password_pegawai = password_pegawai;
        this.tanggal_dibuat = tanggal_dibuat;
    }

    // Getter dan Setter
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getPassword_pegawai() {
        return password_pegawai;
    }

    public void setPassword_pegawai(String password_pegawai) {
        this.password_pegawai = password_pegawai;
    }

    public Date getTanggal_dibuat() {
        return tanggal_dibuat;
    }

    public void setTanggal_dibuat(Date tanggal_dibuat) {
        this.tanggal_dibuat = tanggal_dibuat;
    }
    
    @Override
    public String toString() {
        return "Pegawai{" + 
               "nip='" + nip + '\'' + 
               ", nama_pegawai='" + nama_pegawai + '\'' + 
               // Hati-hati menampilkan password di log produksi
               // ", password_pegawai='" + password_pegawai + '\'' + 
               ", tanggal_dibuat=" + tanggal_dibuat + 
               '}';
    }
} 