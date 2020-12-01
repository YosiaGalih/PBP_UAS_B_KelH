package com.pbp.tubes.uas.richhotel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.tubes.uas.richhotel.databinding.AdapterRecyclerViewAboutBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterAbout extends RecyclerView.Adapter<RecyclerViewAdapterAbout.MyViewHolder> {
    private Context context2;
    private List<Abouts> result2;

    public RecyclerViewAdapterAbout(Context context2, ArrayList<Abouts> result2){
        this.context2 = context2;
        this.result2 = result2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRecyclerViewAboutBinding adapterRecyclerViewAboutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.adapter_recycler_view_about, parent, false);

        MyViewHolder myViewHolder2 = new MyViewHolder(adapterRecyclerViewAboutBinding);
        return myViewHolder2;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Abouts abouts = result2.get(position);
        holder.adapterRecyclerViewAboutBinding.setAbouts(abouts);
//        holder.adapterRecyclerViewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return result2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        AdapterRecyclerViewAboutBinding adapterRecyclerViewAboutBinding;

        public MyViewHolder(@NonNull AdapterRecyclerViewAboutBinding adapterRecyclerViewAboutBinding){
            super(adapterRecyclerViewAboutBinding.getRoot());
            this.adapterRecyclerViewAboutBinding = adapterRecyclerViewAboutBinding;


        }
        public void onClick(View view) {
            Toast.makeText(context2, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }
}

