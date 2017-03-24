package com.edu.tce.emr_sqlite.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.tce.emr_sqlite.R;

public class DoctorHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_screen);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
