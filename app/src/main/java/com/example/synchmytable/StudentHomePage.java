package com.example.synchmytable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class StudentHomePage extends AppCompatActivity {

    private Context context;
    public static final int REQUEST_ADD = 1;
    public static final int REQUEST_EDIT = 2;

    MaterialButton load_btn;



    private TimetableView timetable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        timetable = findViewById(R.id.timetable);

        load_btn=(MaterialButton) findViewById(R.id.SyncSchedules);

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Time table Loaded Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}