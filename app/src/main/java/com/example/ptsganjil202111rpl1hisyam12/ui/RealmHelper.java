package com.example.ptsganjil202111rpl1hisyam12.ui;
import android.util.Log;
import com.example.ptsganjil202111rpl1hisyam12.Model.Model;
import java.util.Collections;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;
    List<Model> storeList;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final Model model) {
        realm.executeTransaction( new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(Model.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    model.setId(nextId);
                    Model itemModel = realm.copyToRealm(model);
                    final RealmResults<Model> item = realm.where(Model.class).findAll();
                } else {
                    Log.e("Log", "execute: Database not Exist");
                }
            }
        });
    }


    public List<Model> getAllMovies() {
        RealmResults<Model> results = realm.where(Model.class).findAll();
        return results;
    }

    public List delete(Model movie){
        final RealmResults<Model> model = realm.where(Model.class).equalTo("tanggal", movie.getTanggal()).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteAllFromRealm();
                final RealmResults<Model> allItems = realm.where(Model.class).findAll();
                storeList = realm.copyFromRealm(allItems);;
                Collections.sort(storeList);
            }
        });
        Log.d("Store List", ""+storeList.size());
        return storeList;
    }

}

