package com.tae.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.tae.myapplication.databinding.ActivityMainBinding;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences= App.getSharedPreferences();

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        LoginViewModel model = ViewModelProviders.of(this).get(LoginViewModel.class);

        activityMainBinding.setViewmodel(model);

        model.getLivedata().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("This is livedata MA - Shared Preferences -> "+sharedPreferences.getString("token","null"));
                System.out.println("This is livedata from MainActivity-> "+s);
            }
        });
    }
}

// с помощью 2хстороннего дата биндинга сделать чтобы паспорт и пароль можно было вводить в форму - чтобы форма заработала.