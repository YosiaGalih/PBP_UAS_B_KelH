package com.pbp.tubes.uas.richhotel.Profil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Edit.EditUser;
import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity2;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilUser extends AppCompatActivity {

    private TextView twNama, twAge, twEmail;
    private String sIdUser, sNama, sAge, sEmail;
    private ProgressDialog progressDialog;
    private Button btnCancel, btnEdit;
    private String email;
    public static final int mode = Activity.MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("Login", mode);
        email = sp.getString("email", "");
        setContentView(R.layout.profil_user);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        twNama = findViewById(R.id.twNama);
        twAge = findViewById(R.id.twAge);
        twEmail = findViewById(R.id.twEmail);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(ProfilUser.this, MainActivity2.class);
//                startActivity(i);
//                finish();
                //lebih aman pke ini menurutku
                onBackPressed();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilUser.this, EditUser.class);
                i.putExtra("id", sIdUser);
                startActivityForResult(i, 0);
            }
        });

        //sIdUser = getIntent().getStringExtra("id");
        loadUserByEmail(email);

    }

    private void loadUserByEmail(String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserByEmail(email, "data");

        add.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.i("register", "msg: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                sNama = response.body().getUser().getName();
                sAge = response.body().getUser().getAge();
                sEmail = response.body().getUser().getEmail();
                sIdUser = response.body().getUser().getId();

                twNama.setText(sNama);
                twAge.setText(sAge);
                twEmail.setText(sEmail);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ProfilUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }
}
