package com.example.synchmytable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StudentLoginPage(View view) {

        Intent intent=new Intent(this,StudentMainLogin.class);
        startActivity(intent);
    }

    public void CrLoginPage(View view) {

        Intent intent=new Intent(this, CrLogin.class);
        startActivity(intent);

    }
}