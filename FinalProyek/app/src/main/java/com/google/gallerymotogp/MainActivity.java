package com.google.gallerymotogp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gallerymotogp.Activity.AboutActivity;
import com.google.gallerymotogp.Activity.DetailActivity;
import com.google.gallerymotogp.Adapter.RiderAdapter;
import com.google.gallerymotogp.Component.RiderComponent;
import com.google.gallerymotogp.Component.RiderDataComponent;
import com.google.gallerymotogp.Listener.RecyclerOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gallerymotogp.Activity.DetailActivity.EXTRA_DESCRIPTION;
import static com.google.gallerymotogp.Activity.DetailActivity.EXTRA_GELAR;
import static com.google.gallerymotogp.Activity.DetailActivity.EXTRA_NAME;
import static com.google.gallerymotogp.Activity.DetailActivity.EXTRA_PHOTO;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_main) RecyclerView recyclerView;

    private ArrayList<RiderComponent> records = new ArrayList<>();
    private RiderAdapter riderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Gallery MotoGP");
        }

        records.addAll(RiderDataComponent.getListData()); // Untuk menampilkan data dari riderDataComponent

        pictAnimation();
        recyclerView();
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

    private void recyclerView() {
        riderAdapter = new RiderAdapter(records);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(riderAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerOnClickListener(MainActivity.this, recyclerView, new RecyclerOnClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RiderComponent riderComponent = records.get(position);
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra(EXTRA_PHOTO, riderComponent.getPhotoRider());
                i.putExtra(EXTRA_NAME, riderComponent.getNameRider());
                i.putExtra(EXTRA_GELAR, riderComponent.getGelarRider());
                i.putExtra(EXTRA_DESCRIPTION, riderComponent.getDescriptionRider());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                RiderComponent riderComponent = records.get(position);
                Toast.makeText(MainActivity.this, riderComponent.getNameRider(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_about){
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
