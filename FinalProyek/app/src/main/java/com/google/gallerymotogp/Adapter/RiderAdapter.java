package com.google.gallerymotogp.Adapter;

import android.app.Activity;
import android.content.Intent;
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
import com.google.gallerymotogp.Activity.DetailGambarActivity;
import com.google.gallerymotogp.Component.RiderComponent;
import com.google.gallerymotogp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gallerymotogp.Activity.DetailGambarActivity.EXTRA_GAMBAR;

public class RiderAdapter extends RecyclerView.Adapter<RiderAdapter.ViewHolder> {

    private ArrayList<RiderComponent> records;

    public RiderAdapter(ArrayList<RiderComponent> records){
        this.records = records;
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

        // jika click photoRider maka masuk ke detail gambar
        viewHolder.photoRider.setOnClickListener(v -> {
            String dataGambar = records.get(viewHolder.getAdapterPosition()).getPhotoRider();
            Intent intent = new Intent(viewHolder.itemView.getContext(), DetailGambarActivity.class);
            intent.putExtra(EXTRA_GAMBAR, dataGambar);
            ActivityOptionsCompat options = (ActivityOptionsCompat) ActivityOptionsCompat.makeSceneTransitionAnimation(
                    (Activity) viewHolder.itemView.getContext(),
                    viewHolder.photoRider,
                    ViewCompat.getTransitionName(viewHolder.photoRider));
            viewHolder.itemView.getContext().startActivity(intent, options.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }
}
