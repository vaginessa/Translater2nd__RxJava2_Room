package com.example.roma.translater2ndrxroom.data.source.local;

import com.example.roma.translater2ndrxroom.data.TranslateItem;
import com.example.roma.translater2ndrxroom.data.source.DataSource;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;


public class LocalDataSource implements DataSource {

    private LocalRoomDAO dao;

    private static LocalDataSource INSTANCE;


    private LocalDataSource(LocalRoomDAO dao) {
        this.dao = dao;
    }

    public static LocalDataSource getInstance(LocalRoomDAO dao) {
        if (INSTANCE == null)
            INSTANCE = new LocalDataSource(dao);
        return INSTANCE;
    }


    @Override
    public void insert(TranslateItem item) {
        dao.insertItem(item);
    }

    @Override
    public void deleteItems(final List<TranslateItem> listDelete) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (TranslateItem item : listDelete)
                    dao.deleteItem(item);
            }
        }).start();

    }

    @Override
    public void setUnsetFavoutite(TranslateItem item) {
        dao.setUnsetFavourite(item);
    }

    @Override
    public Single<List<TranslateItem>> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Maybe<TranslateItem> checkItem(String word) {
        return dao.checkItem(word);
    }

    @Override
    public Single<List<TranslateItem>> searchLocal(String word) {
        String wordEdit = "%"+word+"%";
        return dao.searchLocal(wordEdit);
    }

    @Override
    public Single<List<TranslateItem>> searchLocalFavourite(String word) {
        String wordEdit = "%"+word+"%";

        return dao.searchLocalFavourite(wordEdit);
    }

    @Override
    public Single<TranslateItem> searchRemote(String word, String lang) {
        return null;
    }

}
