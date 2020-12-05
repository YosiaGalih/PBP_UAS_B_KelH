package com.pbp.tubes.uas.richhotel.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.tubes.uas.richhotel.Dao.TransaksiDAO;
import com.pbp.tubes.uas.richhotel.Fragment.DetailReservasiFragmentAdmin;
import com.pbp.tubes.uas.richhotel.R;

import java.util.List;
import java.util.stream.Collectors;

public class ReservasiAdminRecyclerAdapter extends RecyclerView.Adapter <ReservasiAdminRecyclerAdapter.RoomViewHolder> implements Filterable {

    private List<TransaksiDAO> dataList;
    private List<TransaksiDAO> filteredDataList;
    private Context context;

    public ReservasiAdminRecyclerAdapter(List<TransaksiDAO> dataList, Context context) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReservasiAdminRecyclerAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_reservasi_admin, parent, false);
        return new ReservasiAdminRecyclerAdapter.RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasiAdminRecyclerAdapter.RoomViewHolder holder, int position) {
        final TransaksiDAO transaksi = filteredDataList.get(position);
        holder.twNamaPemesan.setText(transaksi.getNama());
        holder.twIdPemesan.setText(transaksi.getId_pemesan());
        holder.twAlamatPemesan.setText(transaksi.getAlamat());
        holder.twPilihanKamar.setText(transaksi.getPilihan_kamar());
        holder.twTanggalCheckIn.setText(transaksi.getTglCheckIn());
        holder.twTglCheckOut.setText(transaksi.getTglCheckOut());

//        holder.mParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
//                DetailReservasiFragmentAdmin dialog = new DetailReservasiFragmentAdmin();
//                dialog.show(manager, "dialog");
//
//                Bundle args = new Bundle();
//                args.putString("id", transaksi.getId_pemesan());
//                dialog.setArguments(args);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView twNamaPemesan, twIdPemesan, twAlamatPemesan, twPilihanKamar, twTanggalCheckIn, twTglCheckOut;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            twNamaPemesan = itemView.findViewById(R.id.twNamaPemesana);
            twIdPemesan = itemView.findViewById(R.id.twIDa);
            twAlamatPemesan = itemView.findViewById(R.id.twAlamata);
            twPilihanKamar = itemView.findViewById(R.id.twPilihanKamara);
            twTanggalCheckIn = itemView.findViewById(R.id.twCheckIna);
            twTglCheckOut = itemView.findViewById(R.id.twCheckOuta);
            mParent = itemView.findViewById(R.id.linearLayouta);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence charSequence) {

                filteredDataList = charSequence == null ? dataList :
                        dataList.stream().filter(data -> data.getNama().toLowerCase().contains(charSequence.toString().toLowerCase())).collect(Collectors.toList());

                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDataList = (List<TransaksiDAO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
