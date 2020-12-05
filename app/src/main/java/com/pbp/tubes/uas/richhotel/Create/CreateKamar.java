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
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;
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
                if(tvNamaKamar.getText().toString().isEmpty()) {
                    tvNamaKamar.setError("Nama Kamar Harus Diisi");
                    tvNamaKamar.requestFocus();
                }
                else if(tvHarga.getText().toString().isEmpty()) {
                    tvHarga.setError("Harga Kamar Harus Diisi");
                    tvHarga.requestFocus();
                }
                else if(tvKapasitas.getText().toString().isEmpty()) {
                    tvKapasitas.setError("Kapasitas Kamar Harus Diisi");
                    tvKapasitas.requestFocus();
                }
                else if(tvGambar.getText().toString().isEmpty()){
                    tvGambar.setError("Gambar Kamar Harus Diisi");
                    tvGambar.requestFocus();
                }
                else{
                    createKamar();
                }

            }
        });
    }

    private void createKamar() {
        ApiInterface apiServiceCreate = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponseObject> addKamar = apiServiceCreate.createKamar(tvNamaKamar.getText().toString(), tvKapasitas.getText().toString(),
                                                                    tvHarga.getText().toString(), tvGambar.getText().toString());
        addKamar.enqueue(new Callback<KamarResponseObject>() {
            @Override
            public void onResponse(Call<KamarResponseObject> call, Response<KamarResponseObject> response) {
                try {
                    Toast.makeText(CreateKamar.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    onBackPressed();

                }catch (Exception e){
                    Toast.makeText(CreateKamar.this, "Nama kamar sudah ada / nama terlalu panjang", Toast.LENGTH_SHORT).show();
                    return;
                }

//                Log.i("create", "msg: "+ new GsonBuilder().setPrettyPrinting().create().toJson(response));
//                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                Toast.makeText(CreateKamar.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());

            }
        });

    }
}
