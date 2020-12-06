package com.pbp.tubes.uas.richhotel.Dao;

import com.google.gson.annotations.SerializedName;

public class UserDAO {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private String age;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public UserDAO(String id, String nama, String age, String email, String password) {
        this.id = id;
        this.name = nama;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nama) {
        this.name = nama;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDAO get(int i) {
        return get(i);
    }
}
