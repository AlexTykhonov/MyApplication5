package com.tae.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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

        model.getUser().observe(this,new Observer<Login>() {
            @Override
            public void onChanged(Login login) {
            if (login!= null)
            {
                System.out.println("==========>> login "+login);
                newActivity(); }
            }
        });
    }

    public void newActivity () {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}

// eyJhbGciOiJIUzUxMiJ9.eyJ2YWxpZGF0ZVRpbWUiOjE1OTYzMTA4MDU5NDQsImF1dGhvcml0eVR5cGUiOiJbXSIsInVzZXJJZCI6MSwidXNlcm5hbWUiOiIxIn0.9mVztUgFbCCNV_2AUQdDSNp2ejfMidcGMPLwFuLsUcMIexMvoDs0sQBI8SCheqmOcVPGF1DCP6IXFaUjP54yAA