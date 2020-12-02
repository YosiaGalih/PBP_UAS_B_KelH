package com.pbp.tubes.uas.richhotel.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbp.tubes.uas.richhotel.Dao.UserDAO;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    @Expose
    private UserDAO user = null;

    @SerializedName("message")
    @Expose
    private String message;

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}
}
