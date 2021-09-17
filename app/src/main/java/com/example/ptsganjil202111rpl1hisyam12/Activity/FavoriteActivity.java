package com.example.ptsganjil202111rpl1hisyam12.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ptsganjil202111rpl1hisyam12.Adapter.adapter;
import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import com.example.ptsganjil202111rpl1hisyam12.R;
import com.example.ptsganjil202111rpl1hisyam12.ui.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private adapter Adapter;
    private List<Model> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().hide();
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        arrayList = new ArrayList<>();
        arrayList = realmHelper.getAllMovies();
        show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Adapter.notifyDataSetChanged();
        show();
    }
    public void show() {
        recyclerView = findViewById(R.id.recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavoriteActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Adapter = new adapter(arrayList, FavoriteActivity.this, new adapter.Callback() {
            @Override
            public void calling(int v) {
                Model Operator = arrayList.get(v);
                Intent move = new Intent(getApplicationContext(), favoritedetail.class);
                move.putExtra("deskripsi", Operator.getDeskripsi());
                move.putExtra("judul", Operator.getJudul());
                move.putExtra("tanggal", Operator.getTanggal());
                move.putExtra("gambar", Operator.getGambar());
                startActivity(move);
            }
        });
        recyclerView.setAdapter(Adapter);

    }
}