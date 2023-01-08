
package forms;

import java.util.Date;

public class siswaLolos {
    
    private int nomor_peserta;
    private int tahun_ajaran;
    private String nama;
    private String jenis_kelamin;
    private Date tanggal_lahir;
    private String tempat_lahir;
    private String akta_kelahiran;
    private String kartu_keluarga;
    private String no_nik;
    private String alamat;
    private String nama_ortu;
    private String status;
    private int user_id;
    private String nama_lengkap;
    
    public siswaLolos(int nomor_peserta,int tahun_ajaran,String nama, String jenis_kelamin, Date tanggal_lahir,
            String no_nik,String alamat, String status, String nama_lengkap)
    {
        
        
        this.nomor_peserta = nomor_peserta;
        this.tahun_ajaran = tahun_ajaran;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.tempat_lahir = tempat_lahir;
        this.akta_kelahiran = akta_kelahiran;
        this.kartu_keluarga = kartu_keluarga;
        this.no_nik = no_nik;
        this.alamat = alamat;
        this.nama_ortu = nama_ortu;
        this.status = status;
        this.user_id=user_id;
        this.nama_lengkap=nama_lengkap;
        
    }
    
    public int getNomorPeserta(){
        return nomor_peserta;
    }
    public int getTahunAjaran(){
        return tahun_ajaran;
    }
    public String getNama(){
        return nama;
    }
    public String getJenisKelamin(){
        return jenis_kelamin;
    }
    public Date getTanggalLahir(){
        return tanggal_lahir;
    }
    public String getTempatLahir(){
        return tempat_lahir;
    }
    public String getAktaKelahiran(){
        return akta_kelahiran;
    }
    public String getKartuKeluarga(){
        return kartu_keluarga;
    }
    public String getNoNik(){
        return no_nik;
    }
    public String getAlamat(){
        return alamat;
    }
    public String getNamaOrtu(){
        return nama_ortu;
    }
    public String getStatus(){
        return status;
    }
    public int getUserId(){
        return user_id;
    }
    public String getNamaPetugas(){
    return nama_lengkap;
    }
}
