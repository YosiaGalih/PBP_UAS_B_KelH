package com.pbp.tubes.uas.richhotel.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.About;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Create.CreateReservasi;
import com.pbp.tubes.uas.richhotel.Daftar.DaftarKamarUser;
import com.pbp.tubes.uas.richhotel.Daftar.DaftarReservasiUser;
import com.pbp.tubes.uas.richhotel.Location.Location;
import com.pbp.tubes.uas.richhotel.Profil.ProfilUser;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;
import com.pbp.tubes.uas.richhotel.SignOut.SignOut;
import com.pbp.tubes.uas.richhotel.SignOut.SignOutUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//USER//
public class MainActivity2 extends AppCompatActivity {

    private String email, id, test;

    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    public RelativeLayout  btnAbout, btnSignOut, btnProfile, btnKamar, btnReservasi, btnDaftarReservasi, btnLokasi;
    public static final int mode = Activity.MODE_PRIVATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getStringExtra("email");
        setContentView(R.layout.activity_main2);

        sharedPreferences = getSharedPreferences("Login", mode);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        btnAbout = (RelativeLayout) findViewById(R.id.about);
        btnSignOut = (RelativeLayout) findViewById(R.id.sign_out);
        btnProfile = (RelativeLayout) findViewById(R.id.profil);
        btnKamar = findViewById(R.id.daftarKamarUser);
        btnReservasi = findViewById(R.id.createReservasi);
        btnDaftarReservasi = findViewById(R.id.daftar_reservasi);
        btnLokasi = findViewById(R.id.lokasi_hotel);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignOutUser.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfilUser.class);
                startActivity(intent);
            }
        });

        btnKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarKamarUser.class);
                startActivity(intent);
            }
        });

        btnReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateReservasi.class);
                startActivity(intent);
            }
        });

        btnDaftarReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DaftarReservasiUser.class);
                startActivity(intent);
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Location.class);
                startActivity(intent);
            }
        });

        //load data dlu, lalu save id sm email ke shared preferences
        loadUserByEmail(email);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void loadUserByEmail(String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserByEmail(email, "data");

        add.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(MainActivity2.this, "Login Success", Toast.LENGTH_SHORT).show();
                Log.i("register", "msg: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                id = response.body().getUser().getId();
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", email);
                editor.putString("id", id);
                editor.apply();

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity2.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

}
