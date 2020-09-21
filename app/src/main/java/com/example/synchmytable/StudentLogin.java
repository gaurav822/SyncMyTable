package com.example.synchmytable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Models.Student;

public class StudentLogin extends AppCompatActivity {

    EditText email,id;

    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        email=(EditText) findViewById(R.id.input_email);
        id=(EditText) findViewById(R.id.input_studid);
        studentList=new ArrayList<>();



        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Students");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student studs=dataSnapshot.getValue(Student.class);
                    studentList.add(studs);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void studentmainLogin(View view) {

        Intent intent=new Intent(this,StudentMainLogin.class);
        startActivity(intent);
    }

    public void verifyEmailandIDStud(View view) {

        boolean isVerified = false;

        final String str_email = email.getText().toString();
       final String str_id = id.getText().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if (str_email.isEmpty() || str_id.isEmpty()) {
            Toast.makeText(this, "Fields Can't be blank", Toast.LENGTH_SHORT).show();
        }


        else{

            int temp = 0;

            for (int i = 0; i < studentList.size(); i++) {
                String studmail=studentList.get(i).getEmail();
                String studid=studentList.get(i).getId();
                if(str_email.equals(studmail) && str_id.equals(studid)){
                    temp=temp+1;
                    isVerified=studentList.get(i).isVerified();
                    break;
                }
          }


            if(temp!=1) {
                Toast.makeText(this, "Student ID or Email is Incorrect. Contact CR", Toast.LENGTH_SHORT).show();

            }

          else if(temp==1 && isVerified==false){


                final DatabaseReference reference2=FirebaseDatabase.getInstance().getReference();

                mAuth.sendPasswordResetEmail(str_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            reference2.child("Students").child(str_id).child("verified").setValue(true);
                            Intent intent=new Intent(StudentLogin.this,StudentMainLogin.class);
                            startActivity(intent);
                            Toast.makeText(StudentLogin.this, "Open your email to Reset Password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(StudentLogin.this, "Email Address is Incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
          }


          else  {

              Toast.makeText(this, "Already Registered. You can Login now from below link", Toast.LENGTH_SHORT).show();
        }


    }

    }
}