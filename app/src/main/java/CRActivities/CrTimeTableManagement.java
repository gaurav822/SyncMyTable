package CRActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.synchmytable.R;
import com.github.tlaabs.timetableview.TimetableView;

public class CrTimeTableManagement extends AppCompatActivity {

    private TimetableView timetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr_time_table_management);

        timetable=findViewById(R.id.crtimetable);
    }
}