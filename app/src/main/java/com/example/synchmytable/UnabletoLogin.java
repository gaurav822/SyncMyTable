package com.example.synchmytable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UnabletoLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unableto_login);
    }

    public void ActivationPage(View view) {

        Intent intent=new Intent(this,StudentLogin.class);
        startActivity(intent);
    }
}