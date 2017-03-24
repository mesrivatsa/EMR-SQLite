package com.edu.tce.emr_sqlite.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.tce.emr_sqlite.R;
import com.edu.tce.emr_sqlite.database.DatabaseOperations;
import com.edu.tce.emr_sqlite.database.PatientDatabaseOperations;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Register extends AppCompatActivity {

    private Boolean check = false;
    private EditText Name,Password,Age,Address,Email,Phn_No,Specialization;
    private String mName,mPassword,mAge,mAddress,mEmail,mPhn_No,mSpecialization;
    private ImageButton Profile_Picture;
    private final int SELECT_PHOTO = 1;
    private TextView screenName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = (EditText)findViewById(R.id.name);
        Password = (EditText)findViewById(R.id.userpassword);
        Age = (EditText)findViewById(R.id.age);
        Address = (EditText)findViewById(R.id.address);
        Email = (EditText)findViewById(R.id.mail);
        Phn_No = (EditText)findViewById(R.id.phnno);
        Specialization = (EditText)findViewById(R.id.specialization);
        Profile_Picture = (ImageButton)findViewById(R.id.profile);
        Button register_Button = (Button) findViewById(R.id.Register);
        screenName = (TextView)findViewById(R.id.registerscreen);
        Profile_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent,SELECT_PHOTO);
            }
        });
        setHintFont();
        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check)
                {
                    initialize();
                    if(validateRequest())
                    {
                       // Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                        PatientDatabaseOperations patientDatabaseOperations = new PatientDatabaseOperations(getApplicationContext());
                        patientDatabaseOperations.insertData(patientDatabaseOperations,imagetoByte(Profile_Picture),mName,mPassword,mAge,mAddress,mEmail,mPhn_No);
                        Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    initialize();
                    mSpecialization = Specialization.getText().toString().trim();
                    if(validateRequest())
                    {
                        if(!(mSpecialization.isEmpty()))
                        {
                            DatabaseOperations databaseOperatios = new DatabaseOperations(getApplicationContext());
                            databaseOperatios.insertData(databaseOperatios,imagetoByte(Profile_Picture),mName,mPassword,mAge,mAddress,mEmail,mPhn_No,mSpecialization);
                            Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });
    }

    private byte[] imagetoByte(ImageButton profile_picture) {
        Bitmap bitmap = ((BitmapDrawable)profile_picture.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private boolean validateRequest() {
        boolean result= true;
        if(mName.isEmpty())
        {
            Name.setError("Plz Enter Name");
            result = false;
        }else if(mPassword.isEmpty())
        {
            Password.setError("Plz Enter Valid Password");
            result = false;
        }else if(mAge.isEmpty())
        {
            Age.setError("Plz Enter Age");
            result = false;
        }else if(mAddress.isEmpty())
        {
            Address.setError("Plz Enter Address");
            result = false;
        }else if(mEmail.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()))
        {
            Email.setError("Plz Enter Valid E-mail");
            result = false;
        }else if(mPhn_No.isEmpty() || !(Patterns.PHONE.matcher(mPhn_No).matches()))
        {
            Phn_No.setError("Plz Enter Valid Phone No");
            result = false;
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK)
                {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectImage = BitmapFactory.decodeStream(imageStream);
                        Profile_Picture.setImageBitmap(selectImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    private void initialize() {
        mName = Name.getText().toString().trim();
        mPassword = Password.getText().toString().trim();
        mAge = Age.getText().toString().trim();
        mAddress = Address.getText().toString().trim();
        mEmail = Email.getText().toString().trim();
        mPhn_No = Phn_No.getText().toString().trim();
    }

    public void chooseRegistrationMode(View v)
    {
        Boolean isChecked = ((RadioButton)v).isChecked();
        switch (v.getId())
        {
            case R.id.patient :
                    if(isChecked) {
                        Specialization.setVisibility(View.GONE);
                        check = true;
                    }
                break;
            case R.id.doctor :
                if(isChecked) {
                    Specialization.setVisibility(View.VISIBLE);
                    check = false;
                }
                break;
        }
    }

    public void setHintFont()
    {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"PT_Sans-Web-BoldItalic.ttf");
        screenName.setTypeface(typeface);
        Name.setTypeface(Typeface.MONOSPACE);
        Password.setTypeface(Typeface.MONOSPACE);
        Age.setTypeface(Typeface.MONOSPACE);
        Address.setTypeface(Typeface.MONOSPACE);
        Email.setTypeface(Typeface.MONOSPACE);
        Phn_No.setTypeface(Typeface.MONOSPACE);
        Specialization.setTypeface(Typeface.MONOSPACE);
    }
}
