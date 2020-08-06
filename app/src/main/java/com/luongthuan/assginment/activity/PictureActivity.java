package com.luongthuan.assginment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionButton;
import com.luongthuan.assginment.R;
import com.luongthuan.assginment.adapter.ViewPagerAdapter;
import com.luongthuan.assginment.model.Image;
import com.luongthuan.assginment.model.PhotoFavorite;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PictureActivity extends AppCompatActivity {
    private static final int PERMISSION_STORAGE_CODE1 = 1000;
    private static final int PERMISSION_STORAGE_CODE2 = 2000;
    private static final int PERMISSION_STORAGE_CODE3 = 3000;
    public static List<PhotoFavorite> photoFavoriteList;
    ImageView imgPicture;
    TextView tvName;
    FloatingActionButton action1, action2, action3, action4, action5;
    String pathalias, title;
    int position;
    ArrayList<Image> arrayUrl;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        imgPicture = findViewById(R.id.imgPicture1);
        tvName = findViewById(R.id.tvName);
        viewPager = findViewById(R.id.viewPager);
        arrayUrl = new ArrayList();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        title = bundle.getString("Title");
        pathalias = bundle.getString("Pathalias");
        arrayUrl = bundle.getParcelableArrayList("url");
        viewPagerAdapter = new ViewPagerAdapter(photoFavoriteList, PictureActivity.this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        Log.e("ArrayUrl", arrayUrl + "");
//        UrlZ = bundle.getString("UrlZ");
//        UrlM = bundle.getString("UrlM");
//        UrlN = bundle.getString("UrlN");
//        UrlQ = bundle.getString("UrlQ");
//        height_Z = bundle.getString("height_Z");
//        width_Z = bundle.getString("width_Z");
//        height_M = bundle.getString("height_M");
//        width_M = bundle.getString("width_M");
//        height_N = bundle.getString("height_N");
//        width_N = bundle.getString("width_N");
//        for (int i = 0; i < arrayUrl.size(); i++) {
//            if (i == arrayUrl.size() - 1) {
//                Glide.with(this).load(arrayUrl.get(i).getUrl()).into(imgPicture);
//            }
//        }

        tvName.setText(title);
        action1 = findViewById(R.id.action1);
        action2 = findViewById(R.id.action2);
        action3 = findViewById(R.id.action3);
        action4 = findViewById(R.id.action4);
        action5 = findViewById(R.id.action5);



                action1.setLabelText(arrayUrl.get(arrayUrl.size()-1).getHeight() + " x " + arrayUrl.get(arrayUrl.size()-1).getWidth());


                action2.setLabelText(arrayUrl.get(arrayUrl.size()-2).getHeight() + " x " + arrayUrl.get(arrayUrl.size()-2).getWidth());


                action3.setLabelText(arrayUrl.get(arrayUrl.size()-3).getHeight() + " x " + arrayUrl.get(arrayUrl.size()-3).getWidth());



        action4.setLabelText("Share Facebook");
        action5.setLabelText("Set Wallpaper");

        action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission denied request it
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE1);
                    } else {
                        startDownLoading1();
                    }
                } else {
                    startDownLoading1();
                }
            }
        });
        action2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission denied request it
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE2);
                    } else {
                        startDownLoading2();
                    }
                } else {
                    startDownLoading2();
                }
            }
        });

        action3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission denied request it
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE3);
                    } else {
                        startDownLoading3();
                    }
                } else {
                    startDownLoading3();
                }
            }
        });

        action4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < arrayUrl.size(); i++) {
                    if (i == arrayUrl.size() - 1) {
                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setContentUrl(Uri.parse(arrayUrl.get(i).getUrl()))
                                .build();

                        ShareDialog shareDialog = new ShareDialog(PictureActivity.this);
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                    }
                }


            }
        });

        action5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < arrayUrl.size(); i++) {
                    if (i == arrayUrl.size() - 1) {

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        WallpaperManager wpm = WallpaperManager.getInstance(PictureActivity.this);
                        InputStream ins = null;
                        try {
                            ins = new URL(arrayUrl.get(i).getUrl()).openStream();
                            wpm.setStream(ins);
                            Toast.makeText(PictureActivity.this, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }


    private void startDownLoading1() {
        for (int i = 0; i < arrayUrl.size(); i++) {
            if (i == arrayUrl.size() - 1) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayUrl.get(i).getUrl()));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setTitle("BackgroundHD" + arrayUrl.get(i).getUrl().substring(35));
                request.setDescription(arrayUrl.get(i).getUrl());
                Toast.makeText(this, "Downloading...!", Toast.LENGTH_SHORT).show();
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title + System.currentTimeMillis());
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
            }
        }


    }

    private void startDownLoading2() {
        for (int i = 0; i < arrayUrl.size(); i++) {
            if (i == arrayUrl.size() - 2) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayUrl.get(i).getUrl()));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setTitle("BackgroundHD" + arrayUrl.get(i).getUrl().substring(35));
                request.setDescription(arrayUrl.get(i).getUrl());
                Toast.makeText(this, "Downloading...!", Toast.LENGTH_SHORT).show();
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title + System.currentTimeMillis());
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
            }
        }


    }

    private void startDownLoading3() {
        for (int i = 0; i < arrayUrl.size(); i++) {
            if (i == arrayUrl.size() - 3) {


                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayUrl.get(i).getUrl()));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setTitle("BackgroundHD" + arrayUrl.get(i).getUrl().substring(35));
                request.setDescription(arrayUrl.get(i).getUrl());
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title + System.currentTimeMillis());
                Toast.makeText(this, "Downloading...!", Toast.LENGTH_SHORT).show();
                request.allowScanningByMediaScanner();

                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownLoading1();
                } else {
                    Toast.makeText(PictureActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                }
            }
            case PERMISSION_STORAGE_CODE2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownLoading2();
                } else {
                    Toast.makeText(PictureActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                }
            }
            case PERMISSION_STORAGE_CODE3: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownLoading3();
                } else {
                    Toast.makeText(PictureActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        arrayUrl.clear();

    }
}