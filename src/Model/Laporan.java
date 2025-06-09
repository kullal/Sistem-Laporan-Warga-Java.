package Model;

// import java.util.Date; // Tidak digunakan lagi untuk field tanggal utama

/**
 * Kelas Model untuk merepresentasikan data Laporan.
 */
public class Laporan {
    private int id_laporan;
    private String nik_pelapor; // Bisa juga objek Warga jika ingin relasi langsung di model
    private String jenis_laporan;
    private String detail_laporan;
    private String tanggal_kejadian; // Diubah ke String
    private String tanggal_lapor;    // Diubah ke String
    private String status_laporan;
    private String nip_pegawai_penanggung_jawab; // Bisa juga objek Pegawai
    private String catatan_pegawai;
    private String tanggal_update_status; // Diubah ke String (atau tetap Date jika mau diparsing saat baca)
                                         // Untuk konsistensi, kita ubah ke String juga.
                                         // DAO akan mengisinya dengan String format "yyyy-MM-dd HH:mm:ss"

    // Constructor
    public Laporan() {
    }

    // Constructor lengkap (opsional, bisa dibuat sesuai kebutuhan)
    public Laporan(int id_laporan, String nik_pelapor, String jenis_laporan, String detail_laporan, 
                   String tanggal_kejadian, String tanggal_lapor, String status_laporan, 
                   String nip_pegawai_penanggung_jawab, String catatan_pegawai, String tanggal_update_status) {
        this.id_laporan = id_laporan;
        this.nik_pelapor = nik_pelapor;
        this.jenis_laporan = jenis_laporan;
        this.detail_laporan = detail_laporan;
        this.tanggal_kejadian = tanggal_kejadian;
        this.tanggal_lapor = tanggal_lapor;
        this.status_laporan = status_laporan;
        this.nip_pegawai_penanggung_jawab = nip_pegawai_penanggung_jawab;
        this.catatan_pegawai = catatan_pegawai;
        this.tanggal_update_status = tanggal_update_status;
    }

    // Getter dan Setter
    public int getId_laporan() {
        return id_laporan;
    }

    public void setId_laporan(int id_laporan) {
        this.id_laporan = id_laporan;
    }

    public String getNik_pelapor() {
        return nik_pelapor;
    }

    public void setNik_pelapor(String nik_pelapor) {
        this.nik_pelapor = nik_pelapor;
    }

    public String getJenis_laporan() {
        return jenis_laporan;
    }

    public void setJenis_laporan(String jenis_laporan) {
        this.jenis_laporan = jenis_laporan;
    }

    public String getDetail_laporan() {
        return detail_laporan;
    }

    public void setDetail_laporan(String detail_laporan) {
        this.detail_laporan = detail_laporan;
    }

    public String getTanggal_kejadian() {
        return tanggal_kejadian;
    }

    public void setTanggal_kejadian(String tanggal_kejadian) {
        this.tanggal_kejadian = tanggal_kejadian;
    }

    public String getTanggal_lapor() {
        return tanggal_lapor;
    }

    public void setTanggal_lapor(String tanggal_lapor) {
        this.tanggal_lapor = tanggal_lapor;
    }

    public String getStatus_laporan() {
        return status_laporan;
    }

    public void setStatus_laporan(String status_laporan) {
        this.status_laporan = status_laporan;
    }

    public String getNip_pegawai_penanggung_jawab() {
        return nip_pegawai_penanggung_jawab;
    }

    public void setNip_pegawai_penanggung_jawab(String nip_pegawai_penanggung_jawab) {
        this.nip_pegawai_penanggung_jawab = nip_pegawai_penanggung_jawab;
    }

    public String getCatatan_pegawai() {
        return catatan_pegawai;
    }

    public void setCatatan_pegawai(String catatan_pegawai) {
        this.catatan_pegawai = catatan_pegawai;
    }

    public String getTanggal_update_status() {
        return tanggal_update_status;
    }

    public void setTanggal_update_status(String tanggal_update_status) {
        this.tanggal_update_status = tanggal_update_status;
    }
    
    @Override
    public String toString() {
        return "Laporan{" + 
               "id_laporan=" + id_laporan +
               ", nik_pelapor='" + nik_pelapor + '\'' + 
               ", jenis_laporan='" + jenis_laporan + '\'' + 
               ", status_laporan='" + status_laporan + '\'' +
               ", tanggal_lapor=" + tanggal_lapor +
               '}';
    }
} 