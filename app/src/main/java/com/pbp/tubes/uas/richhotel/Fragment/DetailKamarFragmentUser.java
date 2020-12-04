package com.pbp.tubes.uas.richhotel.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Edit.EditKamar;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.KamarResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailKamarFragmentUser extends DialogFragment {

    private TextView twNama, twHarga, twKapasitas;
    private String sIdKamar, sNama, sHarga, sKapasitas, sGambar;
    private ImageView twGambar;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    public static DetailKamarFragmentUser newInstance() {return new DetailKamarFragmentUser();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.detail_kamar_fragment_user, container, false);

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

        sIdKamar = getArguments().getString("id", "");
        loadKamarById(sIdKamar);

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
//                try{
//                    twGambar.setImageResource(Integer.parseInt(sGambar));
//
//                }catch (NumberFormatException e){}
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KamarResponseObject> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

}