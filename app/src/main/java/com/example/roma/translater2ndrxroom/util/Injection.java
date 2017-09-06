package com.example.roma.translater2ndrxroom.util;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.roma.translater2ndrxroom.data.source.Repository;
import com.example.roma.translater2ndrxroom.data.source.local.LocalDataRoom;
import com.example.roma.translater2ndrxroom.data.source.local.LocalDataSource;
import com.example.roma.translater2ndrxroom.data.source.remote.RemoteDataSource;


public class Injection {
    public  static Repository provideRepository(Context context) {
        LocalDataRoom db = LocalDataRoom.getInstance(context);
        return Repository.getInstance(LocalDataSource.getInstance(db.getDAO()), RemoteDataSource.getInstance());
    }
}
