package com.luongthuan.assginment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.luongthuan.assginment.R;

public class PhotoGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String idPhoto=bundle.getString("idPhoto");

    }
}