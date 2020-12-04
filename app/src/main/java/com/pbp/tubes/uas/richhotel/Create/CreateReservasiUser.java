package com.pbp.tubes.uas.richhotel.Create;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.GsonBuilder;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Edit.EditKamar;
import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity2;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReservasiUser extends AppCompatActivity {

    TextInputEditText twNamaPemesan, twIDPemesan, twAlamatPemesan, twNamaKamar, twTglCheckIn, twTglCheckOut;
    Button btnCreate, btnCancel;

    private String sNamaKamar, sIdPemesan;
    private ProgressDialog progressDialog;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reservasi);

        id = getIntent().getStringExtra("namaKamarId");

        twNamaPemesan = findViewById(R.id.etNamaPemesan);
        twAlamatPemesan = findViewById(R.id.etAlamatPemesan);
        twIDPemesan = findViewById(R.id.etIDPemesan);
        twNamaKamar = findViewById(R.id.etPilihanKamar);
        twTglCheckIn = findViewById(R.id.etTglCheckIn);
        twTglCheckOut = findViewById(R.id.etTglCheckOut);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        btnCancel = findViewById(R.id.btnCancel);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(twNamaPemesan.getText().toString().isEmpty()){
                    twNamaPemesan.setError("Nama Pemesan Harus Diisi");
                    twNamaPemesan.requestFocus();
                } else if(twIDPemesan.getText().toString().isEmpty()){
                    twIDPemesan.setError("ID Pemesan Harus Diisi");
                    twIDPemesan.requestFocus();
                } else if(twAlamatPemesan.getText().toString().isEmpty()){
                    twAlamatPemesan.setError("Alamat Pemesan Harus Diisi");
                    twAlamatPemesan.requestFocus();
                } else if(twNamaKamar.getText().toString().isEmpty()) {
                    twNamaKamar.setError("Nama Kamar Harus Diisi");
                    twNamaKamar.requestFocus();
                } else if(twTglCheckIn.getText().toString().isEmpty()) {
                    twTglCheckIn.setError("Tanggal Check In Harus Diisi");
                    twTglCheckIn.requestFocus();
                } else if(twTglCheckOut.getText().toString().isEmpty()) {
                    twTglCheckOut.setError("Tanggal Check Out Harus Diisi");
                    twTglCheckOut.requestFocus();
                } else {
                    createReservasi();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadKamarById(id);
    }

    private void loadKamarById(String id) {
        ApiInterface apiServiceKamarId = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponseObject> getKamar = apiServiceKamarId.getKamarById(id, "data");

        getKamar.enqueue(new Callback<KamarResponseObject>() {
            @Override
            public void onResponse(Call<KamarResponseObject> call, Response<KamarResponseObject> response) {
                sNamaKamar = response.body().getKamar().getNama_kamar();
                twNamaKamar.setText(sNamaKamar);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                Toast.makeText(CreateReservasiUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void createReservasi() {
        ApiInterface apiServiceCreateReservasi = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> addReservasi = apiServiceCreateReservasi.createTransaksi(twNamaPemesan.getText().toString(), twIDPemesan.getText().toString(),
                                                                                        twAlamatPemesan.getText().toString(), twNamaKamar.getText().toString(),
                                                                                        twTglCheckIn.getText().toString(), twTglCheckOut.getText().toString());

        addReservasi.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                Toast.makeText(CreateReservasiUser.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                onBackPressed();

                Log.i("createReservasi", "msg: "+ new GsonBuilder().setPrettyPrinting().create().toJson(response));
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(CreateReservasiUser.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());
            }
        });
    }
}
