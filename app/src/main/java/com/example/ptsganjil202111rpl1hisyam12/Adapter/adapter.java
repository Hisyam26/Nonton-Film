package com.example.ptsganjil202111rpl1hisyam12.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ptsganjil202111rpl1hisyam12.Activity.FavoriteActivity;
import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import com.example.ptsganjil202111rpl1hisyam12.R;
import com.example.ptsganjil202111rpl1hisyam12.ui.RealmHelper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.callback.Callback;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Callback callback;
    Realm realm;
    RealmHelper realmHelper;
    Context context;
    public List<Model> dataList;
    boolean hapus = false;
    public interface Callback{
        void calling(int v);
    }
    public adapter(List<Model> dataList, Context context, Callback callback){
        Realm.init(context);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        this.callback = callback;
        this.dataList = dataList;
        this.context = context;
    }
    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        Realm.init(context);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        Model m = dataList.get(position);
        holder.judul.setText(m.getJudul());
        holder.tanggal.setText(m.getTanggal());
        holder.vote.setText(m.getVote());
        Picasso.get().load(m.getGambar())
                .fit()
                .into(holder.gambar);
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hapus) {
                    deleteItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView judul, tanggal, vote;
        private ImageView favorite, gambar;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vote = itemView.findViewById(R.id.tv_listvote);
            judul = itemView.findViewById(R.id.tv_listTitle);
            tanggal = itemView.findViewById(R.id.tv_listTanggal);
            gambar = itemView.findViewById(R.id.img_listImage);
            favorite = itemView.findViewById(R.id.img_favorite);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.calling(getAdapterPosition());
                }
            });
        }
    }
    private void deleteItem(int position) {
        List<Model> storeList = realmHelper.delete(dataList.get(position));
        dataList = storeList;
        FavoriteActivity favoriteActivity = new FavoriteActivity();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
