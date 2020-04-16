package com.tae.myapplication.Api;

import com.tae.myapplication.Login;
import com.tae.myapplication.Pet;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    final String BASE_URL =  "https://petshop-server.herokuapp.com/";

    @POST("auth")
    Call<ResponseBody> getLogin (@Body Login login);

    @GET("api")
    Call<List<Pet>> petList(@Header("Authorization") String credentials);
}

