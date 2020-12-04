package com.pbp.tubes.uas.richhotel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbp.tubes.uas.richhotel.Dao.KamarDAO;
import com.pbp.tubes.uas.richhotel.Dao.TransaksiDAO;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("data")
    @Expose
    private TransaksiDAO transaksis = null;

    @SerializedName("message")
    @Expose
    private String message;

    public TransaksiDAO getTransaksis() {return transaksis;}

    public void setTransaksis(TransaksiDAO transaksis) {this.transaksis=transaksis;}

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}
}
