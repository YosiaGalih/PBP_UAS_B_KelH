package com.pbp.tubes.uas.richhotel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbp.tubes.uas.richhotel.Dao.TransaksiDAO;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("data")
    @Expose
    private List<TransaksiDAO> transaksi = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<TransaksiDAO> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(List<TransaksiDAO> transaksi) {
        this.transaksi = transaksi;
    }

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}
}
