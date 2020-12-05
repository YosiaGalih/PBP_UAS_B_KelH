package com.pbp.tubes.uas.richhotel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbp.tubes.uas.richhotel.Dao.TransaksiDAO;

public class TransaksiResponseObject {

    @SerializedName("data")
    @Expose
    private TransaksiDAO transaksi = null;

    @SerializedName("message")
    @Expose
    private String message;

    public TransaksiDAO getTransaksi() {return transaksi;}

    public void setTransaksi(TransaksiDAO transaksi) {this.transaksi = transaksi;}

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}
}
