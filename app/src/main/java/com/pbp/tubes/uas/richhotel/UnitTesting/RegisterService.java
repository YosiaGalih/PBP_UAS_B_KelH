package com.pbp.tubes.uas.richhotel.UnitTesting;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
import com.pbp.tubes.uas.richhotel.Register.UserRegister;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterService {
    public void register(final RegisterView view, String email, String password, String nama,
                         String age, final RegisterCallback callback){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.createUser(email, password, nama, age);
        add.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if(response.body().getMessage().equalsIgnoreCase("Register User Berhasil"
                )){
                    callback.onSuccess(true, response.body().getUser().get(0));
                }
                else{
                    callback.onError();
                    view.showRegisterError(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }
    public Boolean getValid(final RegisterView view, String email, String password, String nama,
                            String age){
        final Boolean[] bool = new Boolean[1];
        register(view, email, password, nama, age, new RegisterCallback() {
            @Override
            public void onSuccess(boolean value, UserDAO user) {
                bool[0] = true;
            }
            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }

}

