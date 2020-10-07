package com.example.synchmytable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Synching please wait...");

        timetable = findViewById(R.id.timetable);

        load_btn=(MaterialButton) findViewById(R.id.SyncSchedules);

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSavedData();
            }
        });
    }

    private void loadSavedData(){
        pd.show();
        timetable.removeAll();
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(this);
        String savedData = mPref.getString("timetable_demo","");
        if(savedData == null && savedData.equals("")) return;
        timetable.load(savedData);
        Toast.makeText(this,"loaded!",Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }
}