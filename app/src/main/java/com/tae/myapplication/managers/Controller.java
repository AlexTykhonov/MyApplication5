package com.tae.myapplication.managers;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "https://petshop-server.herokuapp.com/";

    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

        sHttpClient.addInterceptor(new HeaderInterceptor());
        Retrofit retrofit = sBuilder
                .client(sHttpClient.build())
                .build();
        return  retrofit.create(serviceClass);
    }
}
