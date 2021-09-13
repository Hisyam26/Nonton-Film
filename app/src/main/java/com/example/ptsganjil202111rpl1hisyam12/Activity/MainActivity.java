package com.example.ptsganjil202111rpl1hisyam12.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ptsganjil202111rpl1hisyam12.Adapter.MainAdapter;
import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import com.example.ptsganjil202111rpl1hisyam12.R;
import com.example.ptsganjil202111rpl1hisyam12.ui.RealmHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    final String API = "https://api.themoviedb.org/3/movie/popular";
    final String Api_key = "a008cc3d5b543d48437262c1d83800ec";
    private List<Model> arrayList;
    MainAdapter main;
    String judul, deskripsi , gambar, tanggal, vote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        AndroidNetworking.initialize(getApplicationContext());
        addData();
    }
    private void addData() {
        arrayList = new ArrayList<>();
        AndroidNetworking.get(API)
                .addQueryParameter("api_key", Api_key )
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", "yes");
                        try {
                            JSONArray resultArray = response.getJSONArray("results");
                            for (int i = 0; i < resultArray.length(); i++) {
                                JSONObject resultObj = resultArray.getJSONObject(i);
                                judul = resultObj.getString("title");
                                deskripsi = resultObj.getString("overview");
                                gambar = "https://image.tmdb.org/t/p/w500/".concat(resultObj.getString("poster_path"));
                                tanggal = resultObj.getString("release_date");
                                vote = resultObj.getString("vote_average");
                                arrayList.add(new Model(i, judul, deskripsi, gambar, tanggal, vote, false));
                                final RealmResults<Model> model = realm.where(Model.class).equalTo("tanggal", tanggal).findAll();
                                if (!model.isEmpty()) {
                                    arrayList.get(i).setFavorite(true);
                                }
                            }

                            main = new MainAdapter(arrayList, MainActivity.this, new MainAdapter.Callback() {
                                @Override
                                public void call(int v) {
                                    Model Operator = arrayList.get(v);
                                    Intent move = new Intent(getApplicationContext(), DetailActivity.class);
                                    move.putExtra("deskripsi", Operator.getDeskripsi());
                                    move.putExtra("judul", Operator.getJudul());
                                    move.putExtra("tanggal", Operator.getTanggal());
                                    move.putExtra("gambar", Operator.getGambar());
                                    startActivity(move);
                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(main);

                        } catch (Exception e) {
                            Log.d("Error: ", e.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Something error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}