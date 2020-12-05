package com.pbp.tubes.uas.richhotel.Edit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponse;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponseObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReservasiUser extends AppCompatActivity {

    TextInputEditText twNamaPemesan, twIDPemesan, twAlamatPemesan, twNamaKamar;
    AutoCompleteTextView twTglCheckIn, twTglCheckOut;
    Button btnUpdate, btnCancel, btnDelete;

    private ProgressDialog progressDialog;
    private String idTransaksi;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reservasi);

        idTransaksi = getIntent().getStringExtra("idTransaksi");

        twNamaPemesan = findViewById(R.id.etNamaPemesan);
        twAlamatPemesan = findViewById(R.id.etAlamatPemesan);
        twIDPemesan = findViewById(R.id.etIDPemesan);
        twNamaKamar = findViewById(R.id.etPilihanKamar);
        twTglCheckIn = findViewById(R.id.etTglCheckIn);
        twTglCheckOut = findViewById(R.id.etTglCheckOut);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReservasi();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {

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
                    updateReservasi();

                }
            }
        });

        twTglCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        twTglCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog2();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadTransaksiById(idTransaksi);
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                twTglCheckIn.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showDateDialog2(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                twTglCheckOut.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void loadTransaksiById(String id) {
        ApiInterface apiServiceTransaksiId = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponseObject> getTransaksi = apiServiceTransaksiId.getTransaksiById(id, "data");

        getTransaksi.enqueue(new Callback<TransaksiResponseObject>() {
            @Override
            public void onResponse(Call<TransaksiResponseObject> call, Response<TransaksiResponseObject> response) {
                twNamaPemesan.setText(response.body().getTransaksi().getNama());
                twNamaKamar.setText(response.body().getTransaksi().getPilihan_kamar());
                twIDPemesan.setText(response.body().getTransaksi().getId_pemesan());
                twAlamatPemesan.setText(response.body().getTransaksi().getAlamat());
                twTglCheckIn.setText(response.body().getTransaksi().getTglCheckIn());
                twTglCheckOut.setText(response.body().getTransaksi().getTglCheckOut());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TransaksiResponseObject> call, Throwable t) {
                Toast.makeText(EditReservasiUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void updateReservasi() {
        ApiInterface apiServiceUpdateReservasi = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponseObject> updateReservasi = apiServiceUpdateReservasi.updateTransaksi(idTransaksi, twNamaPemesan.getText().toString(), twIDPemesan.getText().toString(),
                                                                                        twAlamatPemesan.getText().toString(), twNamaKamar.getText().toString(),
                                                                                        twTglCheckIn.getText().toString(), twTglCheckOut.getText().toString());

        updateReservasi.enqueue(new Callback<TransaksiResponseObject>() {
            @Override
            public void onResponse(Call<TransaksiResponseObject> call, Response<TransaksiResponseObject> response) {
                try {
                    Toast.makeText(EditReservasiUser.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }catch (Exception e){
                    Toast.makeText(EditReservasiUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

//                Log.i("createReservasi", "msg: "+ new GsonBuilder().setPrettyPrinting().create().toJson(response));
//                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TransaksiResponseObject> call, Throwable t) {
                Toast.makeText(EditReservasiUser.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("response", "msg: " +t.getMessage());
            }
        });
    }

    private void deleteReservasi(){
        ApiInterface apiDeleteReservasi = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponseObject> deleteReservasi = apiDeleteReservasi.deleteTransaksi(idTransaksi);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteReservasi.enqueue(new Callback<TransaksiResponseObject>() {
                            @Override
                            public void onResponse(Call<TransaksiResponseObject> call, Response<TransaksiResponseObject> response) {
                                Toast.makeText(EditReservasiUser.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }

                            @Override
                            public void onFailure(Call<TransaksiResponseObject> call, Throwable t) {
                                Toast.makeText(EditReservasiUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to delete this ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
