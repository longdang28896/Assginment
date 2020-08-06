package com.luongthuan.assginment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luongthuan.assginment.R;
import com.luongthuan.assginment.model.PhotoGP;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.Holder> {
    List<PhotoGP> photoGPList;
    Context context;

    public PhotoAdapter(List<PhotoGP> photoGPList, Context context) {
        this.photoGPList = photoGPList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Glide.with(context).load(photoGPList.get(position).getUrlS()).into(holder.imgPicture);
        holder.tvView.setText(photoGPList.get(position).getViews()+" views");
    }

    @Override
    public int getItemCount() {
        return photoGPList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imgPicture;
        TextView tvView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imgPicture=itemView.findViewById(R.id.imgPicture1);
            tvView=itemView.findViewById(R.id.tvView1);
        }
    }
}
