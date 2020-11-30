package com.pbp.tubes.uas.richhotel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbp.tubes.uas.richhotel.Dao.KamarDAO;

import java.util.List;

public class KamarResponse {
    @SerializedName("data")
    @Expose
    private List<KamarDAO> kamar_hotels = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<KamarDAO> getKamar_hotels() {return kamar_hotels;}

    public void setKamar_hotels(List<KamarDAO> kamar_hotels) {this.kamar_hotels=kamar_hotels;}

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}
}
