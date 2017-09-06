package com.example.roma.translater2ndrxroom.data.source.local;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.roma.translater2ndrxroom.data.TranslateItem;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface LocalRoomDAO {
    @Insert
    void insertItem(TranslateItem item);

    @Update
    void setUnsetFavourite(TranslateItem item);

    @Delete
    void deleteItem(TranslateItem item);

    @Query("SELECT * FROM TranslateItem")
    Single<List<TranslateItem>> getAllItems();

    @Query("SELECT * FROM TranslateItem WHERE wordIn LIKE :word")
    Maybe<TranslateItem> checkItem(String word);

    @Query("SELECT * FROM TranslateItem WHERE wordIn LIKE :word OR wordOut LIKE :word")
    Single<List<TranslateItem>> searchLocal(String word);

    @Query("SELECT * FROM TranslateItem WHERE isFavorite = 1 AND (wordIn LIKE :word OR wordOut LIKE :word)")
    Single<List<TranslateItem>> searchLocalFavourite(String word);

//    @Query("SELECT * FROM TranslateItem WHERE isFavorite LIKE :1")
//    List<TranslateItem> getAllFavorite();
}
