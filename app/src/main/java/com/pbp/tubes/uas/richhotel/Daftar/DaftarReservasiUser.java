package com.pbp.tubes.uas.richhotel.Daftar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.pbp.tubes.uas.richhotel.Adapter.ReservasiUserRecyclerAdapter;
import com.pbp.tubes.uas.richhotel.Api.ApiClient;
import com.pbp.tubes.uas.richhotel.Api.ApiInterface;
import com.pbp.tubes.uas.richhotel.Dao.TransaksiDAO;
import com.pbp.tubes.uas.richhotel.R;
import com.pbp.tubes.uas.richhotel.Response.TransaksiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarReservasiUser extends AppCompatActivity {

    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private ReservasiUserRecyclerAdapter recyclerAdapter;
    private List<TransaksiDAO> transaksi = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerFrameLayout;
    private String sIdPemesan;
    private SharedPreferences sharedPreferences;
    public static final int mode = Activity.MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_reservasi_user);

        sharedPreferences = getSharedPreferences("Login", mode);
        sIdPemesan = sharedPreferences.getString("id", "");

        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchUser);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTransaksiById(sIdPemesan);
            }
        });

        loadTransaksiById(sIdPemesan);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(requestCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void loadTransaksiById(String id_pemesan) {
        ApiInterface apiGetTransaksi = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> callGetTransaksi = apiGetTransaksi.getTransaksiByIdPemesan(id_pemesan,"data");

        callGetTransaksi.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                try {
                    generateDataList((List<TransaksiDAO>) response.body().getTransaksi());

                }catch (Exception e){
                    Toast.makeText(DaftarReservasiUser.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    shimmerFrameLayout.stopShimmer();

                }
                swipeRefreshLayout.setRefreshing(false);
                shimmerFrameLayout.stopShimmer();
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(DaftarReservasiUser.this, t.getMessage(), Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<TransaksiDAO> transaksiList) {
        recyclerView = findViewById(R.id.TransaksiRecyclerView);
        recyclerAdapter = new ReservasiUserRecyclerAdapter(transaksiList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
