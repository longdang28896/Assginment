package com.luongthuan.assginment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.luongthuan.assginment.EndlessRecyclerViewScrollListener;
import com.luongthuan.assginment.MyAdapter;
import com.luongthuan.assginment.R;
import com.luongthuan.assginment.model.ExampleFavorite;
import com.luongthuan.assginment.model.Photo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList;
    private static final String FULL_EXTRAS = "views,media,path_alias,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o";
    private static final String USER_ID = "187015156@N07";
    private static final String KEY_TOKEN = "9d788c3ae7173a1cda830edcc1be5792";
    private static final String GET_FAVORITE = "flickr.favorites.getList";
    int pages = 0;
    List<Photo> photoList;
    MyAdapter myAdapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    ExampleFavorite exampleFavorite;
    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    SwipeRefreshLayout srlRefesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidNetworking.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = findViewById(R.id.rvList);
        srlRefesh = findViewById(R.id.srlRefesh);
        AndroidFast();
        // gọi androidfast khi refesh
        srlRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AndroidFast();
                srlRefesh.setRefreshing(false);
            }
        });

        photoList=new ArrayList<>();
        myAdapter = new MyAdapter(photoList, MainActivity.this);
        rvList.setAdapter(myAdapter);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvList.setLayoutManager(staggeredGridLayoutManager);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                pages++;
                Log.e("11",11111+"");
                AndroidFast();

            }
        };
        rvList.addOnScrollListener(endlessRecyclerViewScrollListener);
    }


    public void AndroidFast() {
        AndroidNetworking.get("https://www.flickr.com/services/rest/")
                .addQueryParameter("method", GET_FAVORITE)
                .addQueryParameter("api_key", KEY_TOKEN)
                .addQueryParameter("user_id", USER_ID)
                .addQueryParameter("extras", FULL_EXTRAS)
                .addQueryParameter("per_page", "10")
                .addQueryParameter("page", String.valueOf(pages))
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        exampleFavorite = new Gson().fromJson(response.toString(), ExampleFavorite.class);
                        photoList.addAll(exampleFavorite.getPhotos().getPhoto());
                        rvList.setHasFixedSize(true);
                        myAdapter.notifyDataSetChanged();


                        if (exampleFavorite.getPhotos().getPhoto().size() == 0) {
                            rvList.addOnScrollListener(null);
                        }


                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.e("Lõi", error.getMessage());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.galleries:
                startActivity(new Intent(MainActivity.this, GalleriesActivity.class));
                overridePendingTransition(R.xml.slide_in_from_right, R.xml.slide_out_to_left);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}