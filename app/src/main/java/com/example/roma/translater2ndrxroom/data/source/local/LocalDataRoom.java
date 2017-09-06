package com.example.roma.translater2ndrxroom.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;

import com.example.roma.translater2ndrxroom.data.TranslateItem;


@Database(entities = {TranslateItem.class}, version = 1)
public abstract class LocalDataRoom extends RoomDatabase {
    public static LocalDataRoom INSTANCE;

    public abstract LocalRoomDAO getDAO();

    public static LocalDataRoom getInstance(Context context){
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context,LocalDataRoom.class,"database_translate").build();
        return INSTANCE;
    }
}
