package com.example.gitae;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {
        private Switch switchDarkMode, switchNotifikasi;
    private SeekBar seekBarFontSize;
    private TextView textFontSize;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchNotifikasi = findViewById(R.id.switchNotifikasi);
        seekBarFontSize = findViewById(R.id.seekBarFontSize);
        textFontSize = findViewById(R.id.textFontSize);

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Load Pengaturan
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        boolean isNotifikasi = sharedPreferences.getBoolean("notifikasi", true);
        int fontSize = sharedPreferences.getInt("font_size", 16);

        switchDarkMode.setChecked(isDarkMode);
        switchNotifikasi.setChecked(isNotifikasi);
        seekBarFontSize.setProgress(fontSize);
        textFontSize.setText("Ukuran Font: " + fontSize + "sp");

        // Event untuk Dark Mode
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate();
        });

        // Event untuk Notifikasi
        switchNotifikasi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("notifikasi", isChecked);
            editor.apply();
        });

        // Event untuk Ukuran Font
        seekBarFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textFontSize.setText("Ukuran Font: " + progress + "sp");
                editor.putInt("font_size", progress);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
