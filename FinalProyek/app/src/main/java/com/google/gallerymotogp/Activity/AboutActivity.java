package com.google.gallerymotogp.Activity;

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

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.photo_about_profile) ImageView photoProfile;

    private String dataGambar = "https://lh3.googleusercontent.com/wNv4EWtczbe0f7uVBEJPo6Wfe1Fo-VQLYgDN_GhhUlOhLkyLGFO5Tvg0unoCA-e2G6qbJrxjJDQSVqMFygUJ-vb64itvS1ZjW0OQYKU9BtE6Y9xHRb0VhPcR3kNQ2cvKdTWyTyEf2g=s720-no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(AboutActivity.this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("About Us");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RequestOptions requestOptions = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)
                .placeholder(R.color.colorAccent)
                .fallback(R.color.colorAccent)
                .centerCrop()
                .fitCenter();
        Glide.with(AboutActivity.this)
                .load(dataGambar)
                .apply(requestOptions)
                .into(photoProfile);

        pictAnimation();
        photoProfile();
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

    private void photoProfile() {
        photoProfile.setOnClickListener(v -> {
            Intent i = new Intent(AboutActivity.this, DetailGambarActivity.class);
            i.putExtra(DetailGambarActivity.EXTRA_DETAIL, dataGambar);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    AboutActivity.this,
                    photoProfile,
                    ViewCompat.getTransitionName(photoProfile));
            startActivity(i, options.toBundle());
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
