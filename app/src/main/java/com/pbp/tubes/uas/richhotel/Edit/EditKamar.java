package com.pbp.tubes.uas.richhotel.Edit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.KamarResponse;
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKamar extends AppCompatActivity {
    TextInputEditText tvNamaKamar, tvHarga, tvKapasitas, tvGambar;
    Button btnAdd;

    //private TextView twNama, twHarga, twKapasitas;
    private String sIdKamar, sNama, sHarga, sKapasitas, sGambar;
    //private ImageView twGambar;
    //private ImageButton ibClose;
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_kamar);

        id = getIntent().getStringExtra("kamarId");

        tvNamaKamar = (TextInputEditText) findViewById(R.id.tvNamaKamar);
        tvHarga = (TextInputEditText) findViewById(R.id.tvHarga);
        tvKapasitas = (TextInputEditText) findViewById(R.id.tvKapasitas);
        tvGambar = (TextInputEditText) findViewById(R.id.tvGambar);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

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
                    updateKamar();
                }

            }
        });

        loadKamarById(id);
    }

    private void updateKamar() {
        ApiInterface apiServiceCreate = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponseObject> addKamar = apiServiceCreate.updateKamar(id, tvNamaKamar.getText().toString(), tvKapasitas.getText().toString(),
                tvHarga.getText().toString(), tvGambar.getText().toString());
        addKamar.enqueue(new Callback<KamarResponseObject>() {
            @Override
            public void onResponse(Call<KamarResponseObject> call, Response<KamarResponseObject> response) {
                try {
                    Toast.makeText(EditKamar.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    onBackPressed();

                }catch (Exception e){
                    Toast.makeText(EditKamar.this, "Nama kamar sudah ada / nama terlalu panjang", Toast.LENGTH_SHORT).show();
                }

//                Log.i("create", "msg: "+ new GsonBuilder().setPrettyPrinting().create().toJson(response));
//                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                Toast.makeText(EditKamar.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());

            }
        });

    }

    private void loadKamarById(String id){
        ApiInterface apiServiceKamarId = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponseObject> getKamar = apiServiceKamarId.getKamarById(id, "data");

        getKamar.enqueue(new Callback<KamarResponseObject>() {
            @Override
            public void onResponse(Call<KamarResponseObject> call, Response<KamarResponseObject> response) {
                sNama = response.body().getKamar().getNama_kamar();
                sHarga = response.body().getKamar().getHarga();
                sKapasitas = response.body().getKamar().getKapasitas();
                sGambar = response.body().getKamar().getImageURL();

                tvNamaKamar.setText(sNama);
                tvHarga.setText(sHarga);
                tvKapasitas.setText(sKapasitas);
                try{
                    tvGambar.setText(sGambar);

                }catch (NumberFormatException e){}
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                Toast.makeText(EditKamar.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}
