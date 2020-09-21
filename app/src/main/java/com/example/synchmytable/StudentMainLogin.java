package com.example.synchmytable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Models.Student;

public class StudentMainLogin extends AppCompatActivity {

    EditText et_id,et_pass;

    List<Student> studentList;

    FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_login);
        et_id=(EditText) findViewById(R.id.studid);
        et_pass=(EditText) findViewById(R.id.studpass);

        studentList=new ArrayList<>();

        mAuth=FirebaseAuth.getInstance();



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

    public void forgotPassword(View view) {

    }

    public void gotHomeStudentHome(View view) {

        String id=et_id.getText().toString();
        String pass=et_pass.getText().toString();

        String email="";

        if(id.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
        }

        else{
            pd=new ProgressDialog(this);
            pd.setMessage("Logging in");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
            pd.show();
            int temp=0;

            for(int i=0;i<studentList.size();i++){

                if(id.equals(studentList.get(i).getId())){
                    email=studentList.get(i).getEmail();
                    temp=temp+1;
                    break;
                }
            }

            if(temp==1){



                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(StudentMainLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(StudentMainLogin.this,StudentHomePage.class);
                            startActivity(i);
                        }

                        else{
                            pd.dismiss();
                            Toast.makeText(StudentMainLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            else{
                pd.dismiss();
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

        }
    }
}