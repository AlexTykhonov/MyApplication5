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
    Login login;
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<Login> loginMutableLiveData;

    MutableLiveData<Login> getUser() {
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
//
        Api api = retrofit.create(Api.class);
//        login = new Login(email.getValue(), password.getValue());
//        System.out.println("!!!!!!!!!!!!!!!!!! loginMutableLiveData: "+loginMutableLiveData.getValue());

        final Login login1 = new Login(email.getValue(),password.getValue());
        Call<ResponseBody> call = api.getLogin(login1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
             //   liveData.setValue(response.headers().get("access-token"));

                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString("token",response.headers().get("access-token"));
                prefEditor.apply();

                if (response.headers().get("access-token")!= null) {
                    loginMutableLiveData.setValue(login1);
                }
                else {
                    loginMutableLiveData.setValue(null);
                }

                System.out.println("THIS IS ANSWER OF THE SERVER "+response.headers().get("access-token"));
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("This is livedata error! -> "+t);
                getUser().setValue(null);
            }
        });
    }

}
