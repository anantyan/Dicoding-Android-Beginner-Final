package com.google.gallerymotogp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gallerymotogp.Component.RiderComponent;
import com.google.gallerymotogp.Listener.RecyclerOnClickListener;
import com.google.gallerymotogp.MainActivity;
import com.google.gallerymotogp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RiderAdapter extends RecyclerView.Adapter<RiderAdapter.ViewHolder> {

    private ArrayList<RiderComponent> records;
    private RecyclerOnClickListener.ClickListener clickListener;
    private Context context;

    public RiderAdapter(Context context, ArrayList<RiderComponent> records){
        this.context = context;
        this.records = records;
    }

    public void setClickListener(Context context, RecyclerOnClickListener.ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.photo_rider) ImageView photoRider;
        @BindView(R.id.txt_name_rider) TextView txtNameRider;
        @BindView(R.id.txt_deskripsi_rider) TextView txtDescriptionRider;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public RiderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_main, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RiderAdapter.ViewHolder viewHolder, int i) {
        RiderComponent riderComponent = records.get(i);

        // untuk menampilkan gambar dengan inisialisasi view
        RequestOptions requestOptions = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)
                .placeholder(R.color.colorAccent)
                .fallback(R.color.colorAccent)
                .centerCrop()
                .fitCenter();
        Glide.with(viewHolder.itemView.getContext())
                .load(riderComponent.getPhotoRider())
                .apply(requestOptions)
                .into(viewHolder.photoRider);

        // untuk menampilkan data dengan inisialisasi view
        viewHolder.txtNameRider.setText(riderComponent.getNameRider());
        viewHolder.txtDescriptionRider.setText(riderComponent.getDescriptionRider());

        // jika click maka pindah activity/fragment lain
        viewHolder.photoRider.setOnClickListener(v ->
                clickListener.onImgClick(v, records.get(viewHolder.getAdapterPosition()), viewHolder.photoRider));
        viewHolder.itemView.setOnClickListener(v ->
                clickListener.onClick(v, records.get(viewHolder.getAdapterPosition())));
        viewHolder.itemView.setOnLongClickListener(v -> {
            clickListener.onLongClick(v, records.get(viewHolder.getAdapterPosition()));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }
}
