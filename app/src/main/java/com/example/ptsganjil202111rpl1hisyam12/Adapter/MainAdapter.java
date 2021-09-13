package com.example.ptsganjil202111rpl1hisyam12.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import com.example.ptsganjil202111rpl1hisyam12.R;
import com.example.ptsganjil202111rpl1hisyam12.ui.RealmHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private  List<Model> model;
    private List<Model> models;
    Context context;
    Callback callback;
    Realm realm;
    RealmHelper realmHelper;
    public interface Callback{
        void call(int v);
    }
    public MainAdapter(List<Model> model, Context context, Callback callback){
        this.callback = callback;
        this.model = model;
        this.context = context;
        models = new ArrayList<>(model);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Realm.init(context);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        Model movie = model.get(position);
        holder.Judul.setText(movie.getJudul());
        holder.Tanggal.setText(movie.getTanggal());
        holder.vote.setText(movie.getVote());
        Picasso.get()
                .load(model.get(position).getGambar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.gambar);
        if (model.get(position).getFavorite()) {
            holder.favorite.setImageResource(R.drawable.favorite_red);
        } else {
            holder.favorite.setImageResource(R.drawable.favotite_black);
        }
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.get(position).getFavorite()) {
                    holder.favorite.setImageResource(R.drawable.favotite_black);
                    Toast.makeText(context, "Film dihapus dari list favorite kamu", Toast.LENGTH_SHORT).show();
                    model.get(position).setFavorite(false);
                    realmHelper.delete(model.get(position));
                } else {
                    model.get(position).setFavorite(true);
                    holder.favorite.setImageResource(R.drawable.favorite_red);
                    Toast.makeText(context, "Film ditambahkan ke list favorite kamu", Toast.LENGTH_SHORT).show();
                    realmHelper.save(model.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (model != null) ? model.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Judul, Tanggal, vote;
        private ImageView gambar, favorite;
        private RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View v) {
            super(v);
            favorite = v.findViewById(R.id.img_favorite);
            vote = v.findViewById(R.id.tv_listvote);
            Judul = v.findViewById(R.id.tv_listTitle);
            Tanggal = v.findViewById(R.id.tv_listTanggal);
            gambar = v.findViewById(R.id.img_listImage);
            favorite = v.findViewById(R.id.img_favorite);
            relativeLayout = v.findViewById(R.id.relative_layout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 callback.call(getAdapterPosition());
             }
            });
        }
    }
}
