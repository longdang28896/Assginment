package com.luongthuan.assginment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.luongthuan.assginment.adapter.GalleryAdapter;
import com.luongthuan.assginment.R;
import com.luongthuan.assginment.model.ExampleGallery;
import com.luongthuan.assginment.model.Gallery;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleriesActivity extends AppCompatActivity {
    RecyclerView rvGallery;
    SwipeRefreshLayout srlGallery;
    List<Gallery> galleryList;
    GalleryAdapter galleryAdapter;
    GridLayoutManager gridLayoutManager;
    ExampleGallery exampleGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleries);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvGallery = findViewById(R.id.rvGallery);
        srlGallery = findViewById(R.id.srlGallery);
        galleryList = new ArrayList<>();
        FastGallery();
        srlGallery.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FastGallery();
                srlGallery.setRefreshing(false);
            }
        });
    }

    private void FastGallery() {
        AndroidNetworking.get("https://www.flickr.com/services/rest/?method=flickr.galleries.getList&api_key=ef1045dcda144840d9b3dfda972c199c&user_id=187015156%40N07&per_page=10&page=1&format=json&nojsoncallback=1")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject responseq) {
                        exampleGallery = new Gson().fromJson(responseq.toString(), ExampleGallery.class);
                        galleryList = exampleGallery.getGalleries().getGallery();
                        galleryAdapter = new GalleryAdapter(galleryList, GalleriesActivity.this);
                        rvGallery.setAdapter(galleryAdapter);
                        rvGallery.setHasFixedSize(true);
                        gridLayoutManager = new GridLayoutManager(GalleriesActivity.this,2);
                        rvGallery.setLayoutManager(gridLayoutManager);
                        Log.e("GL", galleryList.size() + "");
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.xml.slide_in_from_left, R.xml.slide_out_to_right);

    }
}