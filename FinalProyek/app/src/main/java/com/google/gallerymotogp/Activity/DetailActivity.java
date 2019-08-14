package com.google.gallerymotogp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gallerymotogp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gallerymotogp.Activity.DetailGambarActivity.EXTRA_DETAIL;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.photo_rider_profile) ImageView photoProfile;
    @BindView(R.id.txt_name_rider) TextView txtNameRider;
    @BindView(R.id.txt_gelar_rider) TextView txtGelarRider;
    @BindView(R.id.txt_deskripsi_rider) TextView txtDeskripsiRider;
    @BindView(R.id.progress_circular) ProgressBar progressBar;

    public static final String EXTRA_PHOTO = "extra_gambar";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_GELAR = "extra_gelar";
    public static final String EXTRA_DESCRIPTION = "extra_description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(DetailActivity.this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Biodata");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        pictAnimation();
        data();
        clickImage();
    }

    private void clickImage() {
        photoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String photo = getIntent().getStringExtra(EXTRA_PHOTO);

                Intent i = new Intent(DetailActivity.this, DetailGambarActivity.class);
                i.putExtra(EXTRA_DETAIL, photo);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        DetailActivity.this,
                        photoProfile,
                        ViewCompat.getTransitionName(photoProfile));
                startActivity(i, options.toBundle());
            }
        });
    }

    private void data() {
        String photo        = getIntent().getStringExtra(EXTRA_PHOTO);
        String name         = getIntent().getStringExtra(EXTRA_NAME);
        String gelar        = getIntent().getStringExtra(EXTRA_GELAR);
        String description  = getIntent().getStringExtra(EXTRA_DESCRIPTION);

        RequestOptions requestOptions = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)
                .centerCrop()
                .fitCenter();
        Glide.with(DetailActivity.this)
                .load(photo)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(requestOptions)
                .into(photoProfile);

        txtNameRider.setText(name);
        txtGelarRider.setText(gelar);
        txtDeskripsiRider.setText(description);
    }

    private void pictAnimation(){
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
