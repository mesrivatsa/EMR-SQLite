package com.edu.tce.emr_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RaamKumr on 3/20/2017.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY = "CREATE TABLE "
                + TableData.TableInfo.TABLE_NAME + "("
                + TableData.TableInfo.DOCTOR_PROFILE + " BLOB,"
                + TableData.TableInfo.DOCTOR_NAME + " TEXT,"
                + TableData.TableInfo.DOCTOR_PASSWORD + " TEXT,"
                + TableData.TableInfo.DOCTOR_AGE + " TEXT,"
                + TableData.TableInfo.DOCTOR_ADDRESS + " TEXT,"
                + TableData.TableInfo.DOCTOR_EMAIL + " TEXT,"
                + TableData.TableInfo.DOCTOR_PHONE + " TEXT,"
                + TableData.TableInfo.DOCTOR_SPECIALIZATION + " TEXT);";
        db.execSQL(CREATE_QUERY);
        Log.d("Database Operations","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(DatabaseOperations databaseOperations,byte[] image,String name,String passwd,String age,String add,String email,String phn,String sepcialization)
    {
        SQLiteDatabase sqLiteDatabase = databaseOperations.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.DOCTOR_PROFILE, image);
        contentValues.put(TableData.TableInfo.DOCTOR_NAME, name);
        contentValues.put(TableData.TableInfo.DOCTOR_PASSWORD, passwd);
        contentValues.put(TableData.TableInfo.DOCTOR_AGE, age);
        contentValues.put(TableData.TableInfo.DOCTOR_ADDRESS, add);
        contentValues.put(TableData.TableInfo.DOCTOR_EMAIL, email);
        contentValues.put(TableData.TableInfo.DOCTOR_PHONE, phn);
        contentValues.put(TableData.TableInfo.DOCTOR_SPECIALIZATION, sepcialization);
        long k = sqLiteDatabase.insert(TableData.TableInfo.TABLE_NAME, null,contentValues);
        Log.d("Database Operations","One Row Created");
    }

    public boolean getLogin(DatabaseOperations databaseOperations,String user_name,String user_password)
    {
        SQLiteDatabase sqLiteDatabase = databaseOperations.getReadableDatabase();
        String[] column ={TableData.TableInfo.DOCTOR_NAME, TableData.TableInfo.DOCTOR_PASSWORD};
        String selection = TableData.TableInfo.DOCTOR_NAME + "=?" + " AND " + TableData.TableInfo.DOCTOR_PASSWORD + "=?";
        String[] selectionArgs = {user_name,user_password};
        Cursor cursor = sqLiteDatabase.query(TableData.TableInfo.TABLE_NAME,column,selection,selectionArgs,null,null,null);
        if(cursor.getCount() > 0)
        {
            return true;
        }
        cursor.close();
        sqLiteDatabase.close();
        return false;
    }
}
