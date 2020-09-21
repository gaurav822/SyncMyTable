package com.example.synchmytable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import CRActivities.CrHomePage;

public class CrLogin extends AppCompatActivity {

    MaterialButton cr_login;

    EditText cr_id,cr_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr_login);

        cr_id=(EditText) findViewById(R.id.cr_id);
        cr_password=(EditText) findViewById(R.id.cr_password);

        cr_login=(MaterialButton) findViewById(R.id.cr_loginclick);

        cr_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String crid=cr_id.getText().toString();
                String crpass=cr_password.getText().toString();

                if(crid.equals("") || crpass.equals("")){
                    Toast.makeText(CrLogin.this, "Please enter details", Toast.LENGTH_SHORT).show();
                }

                else if(crid.equals("AP17110010128") && crpass.equals("12345")){
                    Intent intent=new Intent(CrLogin.this,CrHomePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(CrLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(CrLogin.this, "Authorization Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}