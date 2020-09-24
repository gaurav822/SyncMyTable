package CRActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.synchmytable.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.Student;

public class CrHomePage extends AppCompatActivity {

    ProgressDialog pd;

    FirebaseAuth mAuth;

    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr_home_page);

        studentList=new ArrayList<>();

    }

    public void addnewStudent(View view) {

        final DatabaseReference dataRef=FirebaseDatabase.getInstance().getReference("Students");

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Students");

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

        mAuth=FirebaseAuth.getInstance();


        ViewGroup viewGroup=view.findViewById(android.R.id.content);

        View v= LayoutInflater.from(this).inflate(R.layout.addstudent,viewGroup,false);

        final EditText studroll=v.findViewById(R.id.studrollnumber);
        final EditText studname=v.findViewById(R.id.studname);
        final EditText studemail=v.findViewById(R.id.studemailid);
        final EditText studyear=v.findViewById(R.id.studyear);
        final EditText studbranch=v.findViewById(R.id.studbranch);

        Button insert=v.findViewById(R.id.addstudbtn);
        Button cancel=v.findViewById(R.id.canceldata);

        final BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(v);
        dialog.setCancelable(false);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String roll=studroll.getText().toString();
                final String name=studname.getText().toString();
                final String email=studemail.getText().toString();
                final String year=studyear.getText().toString();
                final String branch=studbranch.getText().toString();
                String password="randompassword123";
                final boolean isVerified=false;

                if(roll.isEmpty()||name.isEmpty()||email.isEmpty()||year.isEmpty()||branch.isEmpty()){
                    Toast.makeText(CrHomePage.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }

                else{

                    int temp=0;
                    pd=new ProgressDialog(CrHomePage.this);
                    pd.setMessage("Please wait...");
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setCancelable(false);
                    pd.show();

                    for(int i=0;i<studentList.size();i++){

                        if((roll.equals(studentList.get(i).getId())) || (email.equals(studentList.get(i).getEmail()))){
                            temp=temp+1;
                            Toast.makeText(CrHomePage.this, "Email or Student ID Already Exists", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            break;
                        }

                    }

                    if(temp==0) {
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Student student = new Student(name, email, roll, branch, year, isVerified);

                                    dataRef.child(roll).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            pd.dismiss();
                                            Toast.makeText(CrHomePage.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                } else {
                                    pd.dismiss();
                                    Toast.makeText(CrHomePage.this, "Can't Register with this details", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                    dialog.dismiss();

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void timetableManagement(View view) {

        Intent intent=new Intent(this,CrTimeTableManagement.class);
        startActivity(intent);
    }
}