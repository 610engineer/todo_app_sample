package com.jour1.todo_app_sample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    //field of database name
    private static final String DATABASE_NAME = "todoSample.db";
    //field of version information
    private static final int DATABASE_VERSION = 1;

    //make String text for make SQLt table
    private static final String sql =  "CREATE TABLE todoSample ( _id INTEGER PRIMARY KEY, todo TEXT)";
    private static final String sqlDelete ="DROP TABLE IF EXISTS todoSample";

    //call parent constracter
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


        @Override
        public void onCreate(SQLiteDatabase db){
            //conduct SQL
            db.execSQL(sql);
        }

        @Override
        public  void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion){
        }



}
