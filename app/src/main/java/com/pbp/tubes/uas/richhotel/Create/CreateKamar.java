package com.pbp.tubes.uas.richhotel.Create;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Dao.UserDAO;
import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Register.LoginUser;
import com.pbp.tubes.uas.richhotel.Register.User;
import com.pbp.tubes.uas.richhotel.Register.UserRegister;
import com.pbp.tubes.uas.richhotel.Response.KamarResponse;
import com.pbp.tubes.uas.richhotel.Response.UserResponse;
import com.pbp.tubes.uas.richhotel.SignOut.SignOut;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateKamar extends AppCompatActivity {

    TextInputEditText tvNamaKamar, tvHarga, tvKapasitas, tvGambar;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_kamar);

        tvNamaKamar = (TextInputEditText) findViewById(R.id.tvNamaKamar);
        tvHarga = (TextInputEditText) findViewById(R.id.tvHarga);
        tvKapasitas = (TextInputEditText) findViewById(R.id.tvKapasitas);
        tvGambar = (TextInputEditText) findViewById(R.id.tvGambar);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createKamar();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createKamar() {
        String namaKamar = tvNamaKamar.getText().toString().trim();
        String hargaKamar = tvHarga.getText().toString().trim();
        String kapasitas = tvKapasitas.getText().toString().trim();
        String gambar = tvGambar.getText().toString().trim();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponse> add = apiService.createKamar(tvNamaKamar.getText().toString(), tvHarga.getText().toString(),
                tvKapasitas.getText().toString(), tvGambar.getText().toString());

        if(namaKamar.isEmpty()){
            tvNamaKamar.setError("Nama Kamar is required!");
            tvNamaKamar.requestFocus();
            return;
        }
        if(hargaKamar.isEmpty()){
            tvHarga.setError("Harga Kamar is required!");
            tvHarga.requestFocus();
            return;
        }
        if(kapasitas.isEmpty()){
            tvKapasitas.setError("Kapasitas Kamar is required!");
            tvKapasitas.requestFocus();
            return;
        }
        if(gambar.isEmpty()){
            tvGambar.setError("Gambar Kamar is required!");
            tvGambar.requestFocus();
            return;
        }

        ApiInterface apiServiceCreate = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponse> addKamar = apiServiceCreate.createKamar(tvNamaKamar.getText().toString(), tvKapasitas.getText().toString(),
                                                                    tvHarga.getText().toString(), tvGambar.getText().toString());
        addKamar.enqueue(new Callback<KamarResponse>() {
            @Override
            public void onResponse(Call<KamarResponse> call, Response<KamarResponse> response) {
                Toast.makeText(CreateKamar.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                onBackPressed();

            }

            @Override
            public void onFailure(Call<KamarResponse> call, Throwable t) {
                Toast.makeText(CreateKamar.this, "Kesalahan Jaringan", Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());

            }
        });


    }
}
