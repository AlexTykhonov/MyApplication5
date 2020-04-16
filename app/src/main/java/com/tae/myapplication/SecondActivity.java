package com.tae.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tae.myapplication.databinding.ActivitySecondBinding;

import static com.tae.myapplication.App.sharedPreferences;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences= App.getSharedPreferences();

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        String s = sharedPreferences.getString("token",null);

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
