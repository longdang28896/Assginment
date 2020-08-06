package com.luongthuan.assginment.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luongthuan.assginment.R;
import com.luongthuan.assginment.activity.PictureActivity;
import com.luongthuan.assginment.model.Image;
import com.luongthuan.assginment.model.PhotoFavorite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements Filterable {
    private List<PhotoFavorite> photoFavoriteList;
    private List<PhotoFavorite> photoFavoriteListFull;
    Context context;
    public ConstraintSet set;
    String url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o,
            width_sq, width_t, width_s, width_q, width_m, width_n, width_z, width_c, width_l, width_o,
            height_sq, height_t, height_s, height_q, height_m, height_n, height_z, height_c, height_l, height_o;

    ArrayList<Image> listUrl;
    Image image;

    public MyAdapter(List<PhotoFavorite> photoFavoriteList, Context context) {
        this.photoFavoriteList = photoFavoriteList;
        this.context = context;
        photoFavoriteListFull = new ArrayList<>(photoFavoriteList);
        listUrl = new ArrayList();
        image = new Image();

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        set = new ConstraintSet();
        int width = photoFavoriteList.get(position).getWidthS();
        int height = photoFavoriteList.get(position).getHeightS();
        String ratio = String.format("%d:%d", width, height);
        set.clone(holder.constraintLayout);
        set.setDimensionRatio(holder.imgPicture.getId(), ratio);
        set.applyTo(holder.constraintLayout);
        Glide.with(context).load(photoFavoriteList.get(position).getUrlS()).into(holder.imgPicture);
        holder.tvView.setText(photoFavoriteList.get(position).getViews() + " views");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PictureActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) context, holder.imgPicture, "imageTransition");
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("Title", photoFavoriteList.get(position).getTitle());
                bundle.putString("Pathalias", photoFavoriteList.get(position).getPathalias());

//                bundle.putString("UrlZ", photoFavoriteList.get(position).getUrlZ());
//                bundle.putString("UrlN", photoFavoriteList.get(position).getUrlN());
//                bundle.putString("UrlM", photoFavoriteList.get(position).getUrlM());
//                bundle.putString("UrlQ", photoFavoriteList.get(position).getUrlQ());
//
//                bundle.putString("height_Z", photoFavoriteList.get(position).getHeightZ().toString());
//                bundle.putString("width_Z", photoFavoriteList.get(position).getWidthZ().toString());
//                bundle.putString("height_N", photoFavoriteList.get(position).getHeightN().toString());
//                bundle.putString("width_N", photoFavoriteList.get(position).getWidthN().toString());
//                bundle.putString("height_M", photoFavoriteList.get(position).getHeightM().toString());
//                bundle.putString("width_M", photoFavoriteList.get(position).getWidthM().toString());
//                bundle.putString("height_Q", photoFavoriteList.get(position).getHeightQ().toString());
//                bundle.putString("width_Q", photoFavoriteList.get(position).getWidthQ().toString());


//                url_sq = photoFavoriteList.get(position).getUrlSq();
//                width_sq=photoFavoriteList.get(position).getWidthSq().toString();
//                height_sq=photoFavoriteList.get(position).getHeightSq().toString();

//                url_t = photoFavoriteList.get(position).getUrlT();
//                width_t=photoFavoriteList.get(position).getWidthT().toString();
//                height_t=photoFavoriteList.get(position).getHeightT().toString();

//                url_s = photoFavoriteList.get(position).getUrlS();
//                width_s=photoFavoriteList.get(position).getWidthS().toString();
//                height_s=photoFavoriteList.get(position).getHeightS().toString();

//                url_q = photoFavoriteList.get(position).getUrlQ();
//                width_q=photoFavoriteList.get(position).getWidthQ().toString();
//                height_q=photoFavoriteList.get(position).getHeightQ().toString();

//                url_m = photoFavoriteList.get(position).getUrlM();
//                width_m=photoFavoriteList.get(position).getWidthM().toString();
//                height_m=photoFavoriteList.get(position).getHeightM().toString();

//                url_n = photoFavoriteList.get(position).getUrlN();
//                width_n=photoFavoriteList.get(position).getWidthN().toString();
//                height_n=photoFavoriteList.get(position).getHeightN().toString();

//                url_z = photoFavoriteList.get(position).getUrlZ();
//                width_n=photoFavoriteList.get(position).getWidthN().toString();
//                height_n=photoFavoriteList.get(position).getHeightN().toString();

//                url_c = photoFavoriteList.get(position).getUrlC();
//                width_c=photoFavoriteList.get(position).getWidthC().toString();
//                height_c=photoFavoriteList.get(position).getHeightC().toString();

//                url_l = photoFavoriteList.get(position).getUrlL();
//                width_l=photoFavoriteList.get(position).getWidthL().toString();
//                height_l=photoFavoriteList.get(position).getHeightL().toString();

//                url_o = photoFavoriteList.get(position).getUrlO();
//                width_o=photoFavoriteList.get(position).getWidthO().toString();
//                height_o=photoFavoriteList.get(position).getHeightO().toString();

                if (photoFavoriteList.get(position).getUrlSq() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlSq());
                    image.setWidth(photoFavoriteList.get(position).getWidthSq().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightSq().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlT() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlT());
                    image.setWidth(photoFavoriteList.get(position).getWidthT().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightT().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlS() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlS());
                    image.setWidth(photoFavoriteList.get(position).getWidthS().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightS().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlQ() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlQ());
                    image.setWidth(photoFavoriteList.get(position).getWidthQ().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightQ().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlM() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlM());
                    image.setWidth(photoFavoriteList.get(position).getWidthM().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightM().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlN() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlN());
                    image.setWidth(photoFavoriteList.get(position).getWidthN().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightN().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlZ() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlZ());
                    image.setWidth(photoFavoriteList.get(position).getWidthZ().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightZ().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlC() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlC());
                    image.setWidth(photoFavoriteList.get(position).getWidthC().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightC().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlL() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlL());
                    image.setWidth(photoFavoriteList.get(position).getWidthL().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightL().toString());
                    listUrl.add(image);
                }
                if (photoFavoriteList.get(position).getUrlO() != null) {
                    image.setUrl(photoFavoriteList.get(position).getUrlO());
                    image.setWidth(photoFavoriteList.get(position).getWidthO().toString());
                    image.setHeight(photoFavoriteList.get(position).getHeightO().toString());
                    listUrl.add(image);
                }
                Log.e("Size", listUrl.size() + "");

                bundle.putParcelableArrayList("url", listUrl);
                for (int i = 0; i <listUrl.size() ; i++) {
                    Log.e("GGGG",listUrl.get(i).getUrl());
                }
                intent.putExtras(bundle);

                context.startActivity(intent, options.toBundle());
                listUrl.clear();


            }
        });
    }

    @Override
    public int getItemCount() {
        return photoFavoriteList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PhotoFavorite> filterList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(photoFavoriteList);
            } else {
                String Pattern = charSequence.toString().toLowerCase().trim();
                for (PhotoFavorite item : photoFavoriteList) {
                    if (item.getTitle().toLowerCase().contains(Pattern)) {
                        Log.e("XxX", item.getTitle());
                        filterList.add(item);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            photoFavoriteList.clear();
            photoFavoriteList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgPicture;
        TextView tvView;
        ConstraintLayout constraintLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgPicture = itemView.findViewById(R.id.imgPicture1);
            constraintLayout = itemView.findViewById(R.id.contraintLayout);
            tvView = itemView.findViewById(R.id.tvView1);
        }
    }
}
