package com.example.gitae;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView view;
    ImageButton transfer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        view = findViewById(R.id.bottom_navigation);

        view.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_home) {
                    return true;
                } else if (id == R.id.action_profile) {
                    startActivity(new Intent(HomeActivity.this, Profile.class));
                    return true;
                } else if (id == R.id.action_settings) {
                    startActivity(new Intent(HomeActivity.this, Settings.class));
                }
                return false;
            }
        });

        transfer = findViewById(R.id.transfer);
        transfer.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, TranferActivity.class));
        });
        transfer = findViewById(R.id.CatatanKeuangan);
        transfer.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, CatatanKeuangan.class));
        });


    }
}