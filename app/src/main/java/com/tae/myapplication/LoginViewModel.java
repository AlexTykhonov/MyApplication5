package com.tae.myapplication;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tae.myapplication.Api.Api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {

    SharedPreferences sharedPreferences= App.getSharedPreferences();

    private MutableLiveData<String> liveData;

    public MutableLiveData<String> getLivedata () {
        if (liveData == null) {
            liveData = new MutableLiveData<String >();
        }
        loadCredentials();
    return liveData;
    }

    public void loadCredentials() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseBody> call = api.getLogin(new Login("1","1"));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                liveData.setValue(response.headers().get("access-token"));

                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString("token",response.headers().get("access-token"));
                prefEditor.apply();

                System.out.println("This is livedata from ViewModel -> "+liveData.getValue());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("This is livedata error! -> "+t);
            }
        });
    }
}
