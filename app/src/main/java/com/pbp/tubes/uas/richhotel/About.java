package com.pbp.tubes.uas.richhotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.tubes.uas.richhotel.databinding.AboutBinding;
import com.pbp.tubes.uas.richhotel.MainActivity.MainActivity;
import com.pbp.tubes.uas.richhotel.databinding.AboutBinding;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    private ArrayList<com.pbp.tubes.uas.richhotel.Abouts> ListAbouts;
    private RecyclerView recyclerView2;
    private com.pbp.tubes.uas.richhotel.RecyclerViewAdapterAbout adapter2;
    private RecyclerView.LayoutManager mLayoutManager2;
    AboutBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.about);
        binding.recyclerViewAbout.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewAbout.setHasFixedSize(true);

        //get data rooms
        ListAbouts = new com.pbp.tubes.uas.richhotel.DaftarAbout().ABOUTS;

        //recycler view
        recyclerView2 = findViewById(R.id.recycler_view_about);
        adapter2 = new com.pbp.tubes.uas.richhotel.RecyclerViewAdapterAbout(com.pbp.tubes.uas.richhotel.About.this, ListAbouts);
        mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_Home_Page){
            onBackPressed();
        }

        return true;
    }
}
