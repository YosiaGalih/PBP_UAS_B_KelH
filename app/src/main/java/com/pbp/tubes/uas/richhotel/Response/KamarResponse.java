package com.pbp.tubes.uas.richhotel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbp.tubes.uas.richhotel.Dao.KamarDAO;

import java.util.List;

public class KamarResponse {
    @SerializedName("data")
    @Expose
    private List<KamarDAO> kamar = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<KamarDAO> getKamar() { return kamar; }

    public void setKamar(List<KamarDAO> kamar) { this.kamar = kamar; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
