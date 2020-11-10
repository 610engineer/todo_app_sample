package com.jour1.todo_app_sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;

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
        this.context = context;
    }
        @Override
        public void onCreate(SQLiteDatabase db){
            //conduct SQL
            db.execSQL(sql);
        }

        @Override
        public  void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion){
        }

     Cursor readData(){
        String query = "SELECT * FROM todoSample";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
         if (db != null) {
             cursor = db.rawQuery(query,null);
         }
         return cursor;
     }

     void addText(String todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("todo",todo);
        db.insert("todoSample",null,cv);
        
     }

     void dbDelete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete FROM todoSample");
        readData();
     }

     public void rowDelete(int num){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM todoSample where _id =" + num);
     }




}
