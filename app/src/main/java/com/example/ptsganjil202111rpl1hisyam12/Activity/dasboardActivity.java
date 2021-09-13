package com.example.ptsganjil202111rpl1hisyam12.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import com.example.ptsganjil202111rpl1hisyam12.R;

import java.util.ArrayList;
import java.util.List;

public class dasboardActivity extends AppCompatActivity {
    CardView main, favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        main = findViewById(R.id.main);
        favorite = findViewById(R.id.favorite);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(dasboardActivity.this, MainActivity.class);
                startActivity(move);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(dasboardActivity.this, FavoriteActivity.class);
                startActivity(move);
            }
        });

    }
}