package com.google.gallerymotogp.Listener;

import android.view.View;
import android.widget.ImageView;

import com.google.gallerymotogp.Component.RiderComponent;

public class RecyclerOnClickListener {

    public interface ClickListener {
        void onClick(View view, RiderComponent riderComponent);
        void onLongClick(View view, RiderComponent riderComponent);
        void onImgClick(View view, RiderComponent riderComponent, ImageView imageView);
    }
}
