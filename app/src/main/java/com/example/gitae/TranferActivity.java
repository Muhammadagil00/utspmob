package com.example.gitae;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gitae.Transaksi;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class TranferActivity extends AppCompatActivity {
    private EditText edtNomorTujuan, edtJumlah;
    private Button btnTransfer;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<Transaksi> transaksiList;
    private TransferAdapter transaksiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranfer);

        edtNomorTujuan = findViewById(R.id.edtNomorTujuan);
        edtJumlah = findViewById(R.id.edtJumlah);
        btnTransfer = findViewById(R.id.btnTransfer);
        recyclerView = findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference("transaksi");
        transaksiList = new ArrayList<>();
        transaksiAdapter = new TransferAdapter(transaksiList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(transaksiAdapter);

        btnTransfer.setOnClickListener(v -> lakukanTransfer());

        loadDataTransaksi();
    }

    private void lakukanTransfer() {
        String nomorTujuan = edtNomorTujuan.getText().toString().trim();
        String jumlahStr = edtJumlah.getText().toString().trim();

        if (TextUtils.isEmpty(nomorTujuan) || TextUtils.isEmpty(jumlahStr)) {
            Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        int jumlah;
        try {
            jumlah = Integer.parseInt(jumlahStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan jumlah yang valid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (jumlah <= 0) {
            Toast.makeText(this, "Jumlah harus lebih dari 0", Toast.LENGTH_SHORT).show();
            return;
        }

        String transaksiId = databaseReference.push().getKey();
        Transaksi transaksi = new Transaksi(transaksiId, nomorTujuan, jumlah);
        databaseReference.child(transaksiId).setValue(transaksi)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Transfer berhasil", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Transfer gagal", Toast.LENGTH_SHORT).show());
    }

    private void loadDataTransaksi() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transaksiList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Transaksi transaksi = data.getValue(Transaksi.class);
                    transaksiList.add(transaksi);
                }
                transaksiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TranferActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
