package com.example.roma.translater2ndrxroom.data.source;


import android.util.Log;

import com.example.roma.translater2ndrxroom.data.TranslateItem;
import com.example.roma.translater2ndrxroom.data.source.local.LocalDataSource;
import com.example.roma.translater2ndrxroom.data.source.remote.RemoteDataSource;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class Repository implements DataSource {

    LocalDataSource local;
    RemoteDataSource remote;

    private static Repository INSTANCE;

    private Repository(LocalDataSource local, RemoteDataSource remote) {
        this.local = local;
        this.remote = remote;
    }

    public static Repository getInstance(LocalDataSource local, RemoteDataSource remote) {
        if (INSTANCE == null)
            INSTANCE = new Repository(local, remote);
        return INSTANCE;
    }

    @Override
    public void insert(TranslateItem item) {
        local.insert(item);
    }

    @Override
    public void deleteItems(List<TranslateItem> listDelete) {
        local.deleteItems(listDelete);
    }

    @Override
    public void setUnsetFavoutite(TranslateItem item) {
        local.setUnsetFavoutite(item);
    }

    @Override
    public Single<List<TranslateItem>> getAllItems() {
        return local.getAllItems();
    }

    @Override
    public Maybe<TranslateItem> checkItem(String word) {
        return local.checkItem(word);
    }

    @Override
    public Single<List<TranslateItem>> searchLocal(String word) {
        return local.searchLocal(word);
    }

    @Override
    public Single<List<TranslateItem>> searchLocalFavourite(String word) {
        return local.searchLocalFavourite(word);
    }

    @Override
    public Single<TranslateItem> searchRemote(String word, String lang) {
        Log.v("sdfsdfsdfg", "rep call");

        return remote.searchRemote(word, lang);
    }
}
