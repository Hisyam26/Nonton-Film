package com.example.ptsganjil202111rpl1hisyam12.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import com.example.ptsganjil202111rpl1hisyam12.R;
import com.example.ptsganjil202111rpl1hisyam12.ui.RealmHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {
    String judul, tanggal, deskripsi, gambar,  vote;
    Integer id;
    Boolean favorite;
    Bundle bundle;
    TextView tv_judul, tv_tanggalrilis, tv_deskripsi;
    ImageView img;
    RealmHelper realmHelper;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
//        ImageView imageView;
//        imageView = findViewById(R.id.fav);
        tv_judul= (TextView) findViewById(R.id.tv_judul);
        tv_tanggalrilis = (TextView) findViewById(R.id.tv_tanggal);
        img = (ImageView) findViewById(R.id.img_gambar);
        tv_deskripsi = (TextView) findViewById(R.id.tv_deskripsi);
        bundle = getIntent().getExtras();
//        Realm.init(DetailActivity.this);
//        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
//        realm = Realm.getInstance(configuration);
//        realmHelper = new RealmHelper(realm);
        if (bundle != null) {
            judul = bundle.getString("judul");
            deskripsi = bundle.getString("deskripsi");
            gambar = bundle.getString("gambar");
            tanggal = bundle.getString("tanggal");
            favorite = bundle.getBoolean("favorite");
            id = bundle.getInt("id");
            vote = bundle.getString("vote");
            tv_judul.setText(judul);
            tv_tanggalrilis.setText(tanggal);
            tv_deskripsi.setText(deskripsi);
            Picasso.get()
                    .load(gambar)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher_round)
                    .into(img);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    realmHelper = new RealmHelper(realm);
//                    realmHelper.save(new Model(id, judul, deskripsi, gambar, tanggal, vote, favorite ));
//                }
//            });
        }

    }
}