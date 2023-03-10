package com.example.projek_shoeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlite_db extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login_db";
    public static final String TABLE_NAME = "user";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";

    public sqlite_db(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void  onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , EMAIL TEXT , PASSWORD TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    //Registration Handler
    public boolean insertData(String email , String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //Login Handler
    public Cursor login_user(String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from " + TABLE_NAME + " where EMAIL='"+email+"'AND PASSWORD='"+password+"'" , null);
        return res;
    }
}

