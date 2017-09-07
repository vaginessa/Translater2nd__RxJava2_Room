package com.example.roma.translater2ndrxroom.data.source.remote;


import android.util.Log;

import com.example.roma.translater2ndrxroom.data.TranslateItem;
import com.example.roma.translater2ndrxroom.data.source.DataSource;
import com.example.roma.translater2ndrxroom.pojo.Translate;
import com.example.roma.translater2ndrxroom.retrofit.Server;
import com.example.roma.translater2ndrxroom.util.Const;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {

    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RemoteDataSource();
        return INSTANCE;
    }


    @Override
    public void insert(TranslateItem item) {

    }

    @Override
    public void deleteItems(List<TranslateItem> listDelete) {

    }

    @Override
    public void setUnsetFavoutite(TranslateItem item) {

    }

    @Override
    public Single<List<TranslateItem>> getAllItems() {
        return null;
    }

    @Override
    public Maybe<TranslateItem> checkItem(String word) {
        return null;
    }

    @Override
    public Single<List<TranslateItem>> searchLocal(String word) {
        return null;
    }

    @Override
    public Single<List<TranslateItem>> searchLocalFavourite(String word) {
        return null;
    }

    @Override
    public Single<TranslateItem> searchRemote(final String word, final String lang) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Log.v("sdfsdfsdfg", "remote call");


        return retrofit.create(Server.class)
                .getTranslateWord(Const.API_KEY_TRANSLATE, word, lang)
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        Log.v("sdfsdfsdfg", " on rx ERRRRRROOOOOR");

                    }
                })
                .map(new Function<Translate, String>() {
                    @Override
                    public String apply(@NonNull Translate translate) throws Exception {
                        return translate.getText().get(0);
                    }
                })
                .flatMap(new Function<String, SingleSource<? extends TranslateItem>>() {
                    @Override
                    public SingleSource<? extends TranslateItem> apply(@NonNull String s) throws Exception {
                        return Single.just(new TranslateItem(word, s, lang,false));
                    }
                });

    }
}
