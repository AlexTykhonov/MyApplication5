package com.tae.myapplication.Api;

import com.tae.myapplication.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    final String BASE_URL =  "https://petshop-server.herokuapp.com/";

    @POST("auth")
    Call<ResponseBody> getLogin (@Body Login login);
}

