package com.example.roma.translater2ndrxroom.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class TranslateItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String wordIn;
    private String wordOut;
    private boolean isFavorite = false;
    private boolean isDelete = false;
    private String langIn;

//    public TranslateItem(String wordIn, String wordOut, String langIn) {
//        this.wordIn = wordIn;
//        this.wordOut = wordOut;
//        this.langIn = langIn;
//    }

    public TranslateItem(String wordIn, String wordOut, String langIn, boolean isFavorite) {
        this.wordIn = wordIn;
        this.wordOut = wordOut;
        this.langIn = langIn;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordIn() {
        return wordIn;
    }

    public void setWordIn(String wordIn) {
        this.wordIn = wordIn;
    }

    public String getWordOut() {
        return wordOut;
    }

    public void setWordOut(String wordOut) {
        this.wordOut = wordOut;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(int i) {
        if (i == 1)
            isFavorite = true;
        else isFavorite = false;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(int i) {
        if (i == 1)
            isDelete = true;
        else
            isDelete = false;
    }

    public void changeFavourite() {
        if (isFavorite)
            setFavorite(false);
        else setFavorite(true);
    }

    public String getLangIn() {
        return langIn;
    }

    public void setLangIn(String langIn) {
        this.langIn = langIn;
    }


}
