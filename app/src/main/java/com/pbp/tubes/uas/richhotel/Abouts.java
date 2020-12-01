package com.pbp.tubes.uas.richhotel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Abouts {
    public String npm;
    public String nama;
    public String fakultas;
    public String jurusan;
    public String contact;
    public String imageURL;

    public Abouts(String nama, String npm, String fakultas, String jurusan, String contact, String imageURL) {
        this.npm = npm;
        this.nama = nama;
        this.fakultas = fakultas;
        this.jurusan = jurusan;
        this.contact = contact;
        this.imageURL = imageURL;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact =contact;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView)
                .load(imageURL)
                .into(imageView);
    }
}
