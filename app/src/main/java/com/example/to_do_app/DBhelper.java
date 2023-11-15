package com.example.to_do_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.to_do_app.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    SQLiteDatabase DB;
    String UserEmail= LoginActivity.UserEmail;

    private static final String dbname="Task-data.db";
    public DBhelper( Context context) {
        super(context, "Task-data.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Taskdetails(primaryKey String primary key ,task text,email TEXT ,FOREIGN kEY(email) REFERENCES allusers(email))");
        DB.execSQL("create table allusers(email TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Taskdetails");
        DB.execSQL("drop table if exists allusers");
        onCreate(DB);
    }

    public void deleteItem(int itemID){
        DB = this.getWritableDatabase();
        DB.delete("Taskdetails","id=?",new String[]{String.valueOf(itemID)});
        DB.close();
    }

    public Boolean addrecord(String primaryKey,String task,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("primaryKey",primaryKey);
        cv.put("task",task);
        cv.put("email",email);
        float res = db.insert("Taskdetails",null , cv);
        if(res == -1)
            return false;
        else return true;
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        long result=db.insert("allusers",null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from allusers where email=?",new String[]{email});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkEmailPassword(String email,String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from allusers where email= ? and password= ?",new String[]{email,password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<model> getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Taskdetails.primaryKey, Taskdetails.task " + "FROM Taskdetails " +
                    "INNER JOIN allusers ON Taskdetails.email = allusers.email"+" WHERE allusers.email=?";
        Cursor cursor = db.rawQuery(query,new String[]{UserEmail},null);
        ArrayList<model> dataholder = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                dataholder.add(new model(cursor.getString(0),cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dataholder;
    }

}
