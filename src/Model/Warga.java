package Model;

import java.util.Date;

/**
 * Kelas Model untuk merepresentasikan data Warga.
 */
public class Warga {
    private String nik;
    private String nama_warga;
    private Date tanggal_registrasi; // Menggunakan java.util.Date untuk kesederhanaan di Model

    // Constructor
    public Warga() {
    }

    public Warga(String nik, String nama_warga, Date tanggal_registrasi) {
        this.nik = nik;
        this.nama_warga = nama_warga;
        this.tanggal_registrasi = tanggal_registrasi;
    }

    // Getter dan Setter
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama_warga() {
        return nama_warga;
    }

    public void setNama_warga(String nama_warga) {
        this.nama_warga = nama_warga;
    }

    public Date getTanggal_registrasi() {
        return tanggal_registrasi;
    }

    public void setTanggal_registrasi(Date tanggal_registrasi) {
        this.tanggal_registrasi = tanggal_registrasi;
    }
    
    // Anda bisa menambahkan method toString() untuk debugging jika perlu
    @Override
    public String toString() {
        return "Warga{" + 
               "nik='" + nik + '\'' + 
               ", nama_warga='" + nama_warga + '\'' + 
               ", tanggal_registrasi=" + tanggal_registrasi + 
               '}';
    }
} 