package com.example.roma.translater2ndrxroom.retrofit;


import com.example.roma.translater2ndrxroom.pojo.Translate;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Server {

    @GET("translate")
    Single<Translate> getTranslateWord(@Query("key") String key,
                                       @Query("text") String text,
                                       @Query("lang") String lang);


}
