package com.edu.tce.emr_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RaamKumr on 3/23/2017.
 */

public class PatientDatabaseOperations extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public PatientDatabaseOperations(Context context) {
        super(context, TableData.patientTableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_QUERY = "CREATE TABLE "
                + TableData.patientTableInfo.PATIENT_TABLE_NAME + "("
                + TableData.patientTableInfo.PATIENT_PROFILRE + " BLOB,"
                + TableData.patientTableInfo.PATIENT_NAME + " TEXT,"
                + TableData.patientTableInfo.PATIENT_PASSWORD + " TEXT,"
                + TableData.patientTableInfo.PATIENT_AGE + " TEXT,"
                + TableData.patientTableInfo.PATIENT_ADDRESS + " TEXT,"
                + TableData.patientTableInfo.PATIENT_EMAIL + " TEXT,"
                + TableData.patientTableInfo.PATIENT_PHONE + " TEXT);";
        db.execSQL(CREATE_QUERY);
        Log.d("Database Operations","Table Created");
    }

    public void insertData(PatientDatabaseOperations databaseOperations,byte[] image,String name,String passwd,String age,String add,String email,String phn)
    {
        SQLiteDatabase sqLiteDatabase = databaseOperations.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.patientTableInfo.PATIENT_PROFILRE, image);
        contentValues.put(TableData.patientTableInfo.PATIENT_NAME, name);
        contentValues.put(TableData.patientTableInfo.PATIENT_PASSWORD, passwd);
        contentValues.put(TableData.patientTableInfo.PATIENT_AGE, age);
        contentValues.put(TableData.patientTableInfo.PATIENT_ADDRESS, add);
        contentValues.put(TableData.patientTableInfo.PATIENT_EMAIL, email);
        contentValues.put(TableData.patientTableInfo.PATIENT_PHONE, phn);
        long k = sqLiteDatabase.insert(TableData.patientTableInfo.PATIENT_TABLE_NAME, null, contentValues);
        Log.d("Database Operations","One Row Created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean getLogin(PatientDatabaseOperations databaseOperations,String user_name,String user_password)
    {
        SQLiteDatabase sqLiteDatabase = databaseOperations.getReadableDatabase();
        String[] column ={TableData.patientTableInfo.PATIENT_NAME, TableData.patientTableInfo.PATIENT_PASSWORD};
        String selection = TableData.patientTableInfo.PATIENT_NAME + "=?" + " AND " + TableData.patientTableInfo.PATIENT_PASSWORD + "=?";
        String[] selectionArgs = {user_name,user_password};
        Cursor cursor = sqLiteDatabase.query(TableData.patientTableInfo.PATIENT_TABLE_NAME,column,selection,selectionArgs,null,null,null);
        if(cursor.getCount() > 0)
        {
            return true;
        }
        cursor.close();
        sqLiteDatabase.close();
        return false;
    }
}
