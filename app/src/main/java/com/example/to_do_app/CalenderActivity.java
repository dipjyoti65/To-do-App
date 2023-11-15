package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class CalenderActivity extends AppCompatActivity {

    CalendarView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        cv=findViewById(R.id.calendarView2);
    }
}