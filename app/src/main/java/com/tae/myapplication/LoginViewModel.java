package com.tae.myapplication;

import android.content.SharedPreferences;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
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

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Login> loginMutableLiveData;

    LiveData<Login> getUser() {
        if (loginMutableLiveData == null) {
            loginMutableLiveData = new MutableLiveData<>();
        }
        return loginMutableLiveData;
    }



    public void loadCredentials() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Login login = new Login(email.getValue(), password.getValue());
        Call<ResponseBody> call = api.getLogin(login);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
             //   liveData.setValue(response.headers().get("access-token"));

                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString("token",response.headers().get("access-token"));
                prefEditor.apply();
                System.out.println("THIS IS ANSWER OF THE SERVER "+response.headers().get("access-token"));

               // System.out.println("This is livedata from ViewModel -> "+liveData.getValue());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("This is livedata error! -> "+t);
            }
        });
    }
}

// доработать двусторонний дата биндинг