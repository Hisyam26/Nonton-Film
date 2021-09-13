package com.example.ptsganjil202111rpl1hisyam12.Model;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
public class Model extends RealmObject implements Comparable {
    @PrimaryKey
    private Integer id;
    private Boolean Favorite;
    private String vote;
    private String judul;
    private String deskripsi;
    private String gambar;
    private String tanggal;
    public Model(Integer id, String judul, String deskripsi, String gambar, String tanggal, String vote, Boolean favorite) {
        this.id = id;
        this.judul = judul;
        this.vote = vote;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.Favorite = favorite;
    }
    public  Model(){

    }
    public String getVote() { return vote; }

    public void setVote(String vote) { this.vote = vote; }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return Favorite;
    }

    public void setFavorite(Boolean favorite) {
        Favorite = favorite;
    }

    @Override
    public int compareTo(Object o) {
        int compareage=((Model)o).getId();
        return this.id-compareage;
    }
}
