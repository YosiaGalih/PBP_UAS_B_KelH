package com.pbp.tubes.uas.richhotel.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pbp.tubes.uas.richhotel.Dao.KamarDAO;
import com.pbp.tubes.uas.richhotel.Fragment.DetailKamarFragment;
import com.pbp.tubes.uas.richhotel.Fragment.DetailKamarFragmentUser;
import com.pbp.tubes.uas.richhotel.R;

import java.util.List;
import java.util.stream.Collectors;

public class KamarRecyclerAdapterUser extends RecyclerView.Adapter <KamarRecyclerAdapterUser.RoomViewHolder> implements Filterable {

    private List<KamarDAO> dataList;
    private List<KamarDAO> filteredDataList;
    private Context context;

    public KamarRecyclerAdapterUser(List<KamarDAO> dataList, Context context) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public KamarRecyclerAdapterUser.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_kamar_user, parent, false);
        return new KamarRecyclerAdapterUser.RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KamarRecyclerAdapterUser.RoomViewHolder holder, int position) {
        final KamarDAO kmr = filteredDataList.get(position);
        holder.twNamaKamar.setText(kmr.getNama_kamar());
        holder.twHargaKamar.setText(kmr.getHarga());
        holder.twKapasitas.setText(kmr.getKapasitas());
        Glide.with(holder.twGambar.getContext())
                .load(kmr.getImageURL())
                .into(holder.twGambar);

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailKamarFragmentUser dialog = new DetailKamarFragmentUser();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", kmr.getId());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView twNamaKamar, twHargaKamar, twKapasitas;
        private ImageView twGambar;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            twNamaKamar = itemView.findViewById(R.id.twNamaKamar);
            twHargaKamar = itemView.findViewById(R.id.twHargaKamar);
            twKapasitas = itemView.findViewById(R.id.twKapasitas);
            twGambar = itemView.findViewById(R.id.imageTw);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence charSequence) {

                filteredDataList = charSequence == null ? dataList :
                        dataList.stream().filter(data -> data.getNama_kamar().toLowerCase().contains(charSequence.toString().toLowerCase())).collect(Collectors.toList());

                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDataList = (List<KamarDAO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
