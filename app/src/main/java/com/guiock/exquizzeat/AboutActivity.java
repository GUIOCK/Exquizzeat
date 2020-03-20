package com.guiock.exquizzeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView aboutVersionNumber = findViewById(R.id.versionNumberTextView);
        aboutVersionNumber.setText("V " + BuildConfig.VERSION_NAME);
    }
}
