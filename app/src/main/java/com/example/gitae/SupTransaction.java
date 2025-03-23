package com.example.gitae;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SupTransaction extends RecyclerView.Adapter<SupTransaction.ViewHolder> {

    private List<Transaction> transactionList;

    public SupTransaction(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sup_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.tvJenis.setText("Jenis Transaksi : "+ transaction.getJenis());
        holder.tvJumlah.setText("Jumlah : Rp " + transaction.getJumlah());
        holder.tvDeskripsi.setText("Deksripsi : " + transaction.getDeskripsi());
        holder.tvTanggal.setText("Tanggal Transaksi : " + transaction.getTanggal());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJenis, tvJumlah, tvDeskripsi, tvTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJenis = itemView.findViewById(R.id.tvJenis);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }
}