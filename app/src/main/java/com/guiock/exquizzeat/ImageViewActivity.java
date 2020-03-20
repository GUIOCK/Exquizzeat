package com.guiock.exquizzeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        Intent srcIntent = getIntent();
        int imgId = srcIntent.getIntExtra("imgId",0);
        ImageView img = findViewById(R.id.zoomImageView);
        img.setImageResource(imgId);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.zoomImageView){
            finish();
        }
    }
}
