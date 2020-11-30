package com.pbp.tubes.uas.richhotel.Dao;

import com.google.gson.annotations.SerializedName;

public class TransaksiDAO {
    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("id_pemesan")
    private String id_pemesan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("pilihan_kamar")
    private String pilihan_kamar;

    @SerializedName("tgl_check_in")
    private String tglCheckIn;

    @SerializedName("tgl_check_out")
    private String tglCheckOut;

    public TransaksiDAO (String id,String nama, String id_pemesan, String alamat, String pilihan_kamar,String tglCheckIn, String tglCheckOut)
    {
        this.id = id;
        this.nama = nama;
        this.id_pemesan = id_pemesan;
        this.alamat = alamat;
        this.pilihan_kamar = pilihan_kamar;
        this.tglCheckIn = tglCheckIn;
        this.tglCheckOut = tglCheckOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {return nama;}

    public void setNama(String nama) {this.nama = nama;}

    public String getId_pemesan() {return this.id_pemesan;}

    public void setId_pemesan(String id_pemesan) {this.id_pemesan = id_pemesan;}

    public String getAlamat() {return alamat;}

    public void setAlamat(String alamat) {this.alamat = alamat;}

    public String getPilihan_kamar() {return pilihan_kamar;}

    public void setPilihan_kamar(String pilihan_kamar) {this.pilihan_kamar = pilihan_kamar;}

    public String getTglCheckIn() {return tglCheckIn;}

    public void setTglCheckIn(String tglCheckIn) {this.tglCheckIn = tglCheckIn;}

    public String getTglCheckOut() {return tglCheckOut;}

    public void setTglCheckOut(String tglCheckOut) {this.tglCheckOut= tglCheckOut;}

}
