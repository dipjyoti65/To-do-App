package com.example.to_do_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    SQLiteDatabase DB;
    private static final String dbname="Task-data.db";
    public DBhelper( Context context) {
        super(context, "Task-data.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Taskdetails(id integer primary key autoincrement,task text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Taskdetails");
        onCreate(DB);
    }

    public Boolean addrecord(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("task",task);
        float res = db.insert("Taskdetails",null , cv);
        if(res == -1)
            return false;
        else return true;
    }

    public ArrayList<model> getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + " Taskdetails",null);
        ArrayList<model> dataholder = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                dataholder.add(new model(cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dataholder;
    }

}
