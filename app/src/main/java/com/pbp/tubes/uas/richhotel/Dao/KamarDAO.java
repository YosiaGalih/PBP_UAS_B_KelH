package com.pbp.tubes.uas.richhotel.Dao;

import com.google.gson.annotations.SerializedName;

public class KamarDAO {
    @SerializedName("id")
    private String id;

    @SerializedName("nama_kamar")
    private String nama_kamar;

    @SerializedName("kapasitas")
    private String kapasitas;

    @SerializedName("harga")
    private String harga;

    @SerializedName("imageURL")
    private String imageURL;

    public KamarDAO (String id,String nama_kamar, String kapasitas, String harga, String imageURL)
    {
        this.id = id;
        this.nama_kamar = nama_kamar;
        this.kapasitas = kapasitas;
        this.harga = harga;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_kamar() {return nama_kamar;}

    public void setNama_kamar(String nama_kamar) {this.nama_kamar = nama_kamar;}

    public String getKapasitas() {return kapasitas;}

    public void setKapasitas(String kapasitas) {this.kapasitas = kapasitas;}

    public String getHarga() {return harga;}

    public void setHarga(String harga) {this.harga = harga;}

    public String getImageURL() {return imageURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

}
