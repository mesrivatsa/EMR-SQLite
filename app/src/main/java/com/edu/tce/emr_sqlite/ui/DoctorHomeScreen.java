package com.edu.tce.emr_sqlite.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.edu.tce.emr_sqlite.fragments.HomeFragment;
import com.edu.tce.emr_sqlite.fragments.Patient_Status;
import com.edu.tce.emr_sqlite.R;
import com.edu.tce.emr_sqlite.adapter.viewpageradapter;

import static com.edu.tce.emr_sqlite.constants.constant.DOCTOR_FRAGMENT_HOME;
import static com.edu.tce.emr_sqlite.constants.constant.DOCTOR_FRAGMENT_PATIENT_STATUS;

public class DoctorHomeScreen extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    viewpageradapter viewpageradapter;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_screen);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        textView = (TextView)findViewById(R.id.toolbar_title);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"PT_Sans-Web-BoldItalic.ttf");
        textView.setTypeface(typeface);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewpageradapter = new viewpageradapter(getSupportFragmentManager());
        viewpageradapter.addFragmentsFragment(new HomeFragment(),DOCTOR_FRAGMENT_HOME);
        viewpageradapter.addFragmentsFragment(new Patient_Status(),DOCTOR_FRAGMENT_PATIENT_STATUS);
        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
