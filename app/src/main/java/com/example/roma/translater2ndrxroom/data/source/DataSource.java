package com.example.roma.translater2ndrxroom.data.source;


import com.example.roma.translater2ndrxroom.data.TranslateItem;
import com.example.roma.translater2ndrxroom.pojo.Translate;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public interface DataSource {


    void insert(TranslateItem item);

    void deleteItems(List<TranslateItem> listDelete);

    void setUnsetFavoutite(TranslateItem item);

    Single<List<TranslateItem>> getAllItems();

    Maybe<TranslateItem> checkItem(String word);

    Single<List<TranslateItem>> searchLocal(String word);

    Single<List<TranslateItem>> searchLocalFavourite(String word);

    Single<TranslateItem> searchRemote(String word, String lang);


}
