package com.example.gitae;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {
    private List<Transaksi> transaksiList;

    public TransferAdapter(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaksi transaksi = transaksiList.get(position);
        holder.tvNomorTujuan.setText("Tujuan: " + transaksi.nomorTujuan);
        holder.tvJumlah.setText("Jumlah: Rp" + transaksi.jumlah);
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomorTujuan, tvJumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomorTujuan = itemView.findViewById(R.id.tvNomorTujuan);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
        }
    }
}

