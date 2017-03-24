package com.edu.tce.emr_sqlite.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.tce.emr_sqlite.R;
import com.edu.tce.emr_sqlite.database.DatabaseOperations;
import com.edu.tce.emr_sqlite.database.PatientDatabaseOperations;

public class Login extends AppCompatActivity {

    EditText UserName,Password;
    TextView Register;
    String mUserName,mPassword;
    CheckBox login_Mode;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView title = (TextView)findViewById(R.id.apptitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"PT_Sans-Web-BoldItalic.ttf");
        title.setTypeface(typeface);
        UserName = (EditText)findViewById(R.id.username);
        Password =(EditText)findViewById(R.id.password);
        Login = (Button)findViewById(R.id.login);
        UserName.setTypeface(Typeface.MONOSPACE);
        Password.setTypeface(Typeface.MONOSPACE);
        Register = (TextView)findViewById(R.id.register);
        login_Mode = (CheckBox)findViewById(R.id.doctorlogin);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, com.edu.tce.emr_sqlite.ui.Register.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login_Mode.isChecked()) {
                    getLoginData();
                    if (!(mUserName.isEmpty()) && !(mPassword.isEmpty())) {
                        DatabaseOperations databaseOperatios = new DatabaseOperations(getApplicationContext());
                        if (databaseOperatios.getLogin(databaseOperatios, mUserName, mPassword)) {
                            Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(com.edu.tce.emr_sqlite.ui.Login.this, DoctorHomeScreen.class);
                            startActivity(intent);
                            UserName.setText("");
                            Password.setText("");
                            login_Mode.setChecked(false);
                        } else {
                            Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    getLoginData();
                    if(!(mUserName.isEmpty()) && !(mPassword.isEmpty()))
                    {
                        PatientDatabaseOperations patientDatabaseOperations = new PatientDatabaseOperations(getApplicationContext());
                        if(patientDatabaseOperations.getLogin(patientDatabaseOperations,mUserName,mPassword))
                        {
                            Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(com.edu.tce.emr_sqlite.ui.Login.this, DoctorHomeScreen.class);
                            startActivity(intent);
                            UserName.setText("");
                            Password.setText("");
                        }else {
                            Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public void getLoginData()
    {
        mUserName = UserName.getText().toString().trim();
        mPassword = Password.getText().toString().trim();
    }
}
