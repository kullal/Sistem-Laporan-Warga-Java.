-- Membuat database jika belum ada
CREATE DATABASE IF NOT EXISTS dbsistem;

-- Menggunakan database dbsistem
USE dbsistem;

-- Pastikan Anda menghapus tabel lama jika ada sebelum membuat yang baru
-- DROP TABLE IF EXISTS laporan;
-- DROP TABLE IF EXISTS pegawai;
-- DROP TABLE IF EXISTS warga;

-- Tabel untuk Data Warga
CREATE TABLE warga (
    nik VARCHAR(16) PRIMARY KEY,
    nama_warga VARCHAR(100) NOT NULL,
    tanggal_registrasi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabel untuk Data Pegawai
CREATE TABLE pegawai (
    nip VARCHAR(20) PRIMARY KEY,
    nama_pegawai VARCHAR(100) NOT NULL,
    password_pegawai VARCHAR(255) NOT NULL, -- Ingat untuk HASH password ini
    tanggal_dibuat TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabel untuk Laporan dari Warga
CREATE TABLE laporan (
    id_laporan INT AUTO_INCREMENT PRIMARY KEY,
    nik_pelapor VARCHAR(16) NOT NULL,
    nama_pelapor VARCHAR(100) NOT NULL,
    jenis_laporan VARCHAR(50) NOT NULL,
    detail_laporan TEXT NOT NULL,
    tanggal_kejadian DATE,
    tanggal_lapor TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_laporan VARCHAR(20) DEFAULT 'Pending' NOT NULL,
    nip_pegawai_penanggung_jawab VARCHAR(20),
    catatan_pegawai TEXT,
    tanggal_update_status TIMESTAMP NULL, -- Kolom ini bisa NULL dan akan default ke NULL

    FOREIGN KEY (nik_pelapor) REFERENCES warga(nik),
    FOREIGN KEY (nip_pegawai_penanggung_jawab) REFERENCES pegawai(nip)
);

-- Contoh memasukkan data pegawai
INSERT INTO pegawai (nip, nama_pegawai, password_pegawai) VALUES
('2304130134', 'Riski Yuniar', 'rahasia'); 