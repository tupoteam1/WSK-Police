package com.example.WSKPolice;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "users.db";
    public DBHelper(Context context) {
        super(context,"users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(login TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public Boolean insertData(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("login", login);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkLogin(String login) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login=?", new String[] {login});
        if (cursor.getCount()>0) {
            return true;
        }
        else{
            return  false;
        }
    }

    public Boolean checkUserPassword(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login=? and password=?", new String[] {login,password});
        if (cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }
}
