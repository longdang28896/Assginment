package com.luongthuan.assginment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luongthuan.assginment.model.Photo;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    List<Photo> photoList;
    Context context;

    public MyAdapter(List<Photo> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide.with(context).load(photoList.get(position).getUrlS()).into(holder.imgPicture);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgPicture;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgPicture=itemView.findViewById(R.id.imgPicture);
        }
    }
}
