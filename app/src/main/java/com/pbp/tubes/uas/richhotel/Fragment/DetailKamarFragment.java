package com.pbp.tubes.uas.richhotel.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Create.CreateKamar;
import com.pbp.tubes.uas.richhotel.Daftar.DaftarKamarAdmin;
import com.pbp.tubes.uas.richhotel.Edit.EditKamar;
import com.pbp.tubes.uas.richhotel.Profil.ProfilAdmin;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.KamarResponse;
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailKamarFragment extends DialogFragment {
    private TextView twNama, twHarga, twKapasitas;
    private String sIdKamar, sNama, sHarga, sKapasitas, sGambar;
    private ImageView twGambar;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    private Button btnDelete, btnEdit;

    public static DetailKamarFragment newInstance() {return new DetailKamarFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.detail_kamar_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = view.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twNama = view.findViewById(R.id.twNamaKamar);
        twHarga = view.findViewById(R.id.twHarga);
        twKapasitas = view.findViewById(R.id.twKapasitas);
        twGambar = view.findViewById(R.id.twGambar);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);

        sIdKamar = getArguments().getString("id", "");
        loadKamarById(sIdKamar);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(sIdKamar);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditKamar.class);
                i.putExtra("kamarId", sIdKamar);
                startActivity(i);
                dismiss();
            }
        });

        return view;
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

                twNama.setText(sNama);
                twHarga.setText(sHarga);
                twKapasitas.setText(sKapasitas);
                Glide.with(twGambar.getContext())
                        .load(sGambar)
                        .apply(new RequestOptions().centerCrop())
                        .into(twGambar);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void delete(String id){
        ApiInterface apiDeleteKamar = ApiClient.getClient().create(ApiInterface.class);
        Call<KamarResponseObject> reqDeleteKamar = apiDeleteKamar.deleteKamar(id);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        reqDeleteKamar.enqueue(new Callback<KamarResponseObject>() {
                            @Override
                            public void onResponse(Call<KamarResponseObject> call, Response<KamarResponseObject> response) {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                                Log.i("DELETE KAMAR", response.body().getMessage());
//                                Intent intent = new Intent(getActivity(), DaftarKamarAdmin.class);
//                                startActivity(intent);
                                dismiss();
                            }

                            @Override
                            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                Log.i("DELETE KAMAR", "Msg: " +t.getMessage());
                            }
                        });
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure want to delete this ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}