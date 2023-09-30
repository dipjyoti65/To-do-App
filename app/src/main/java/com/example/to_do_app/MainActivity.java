package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton fab;
    Button saveButton , showList ;
    DrawerLayout drawerLayout;
    HorizontalScrollView horizontalScrollView;
    RecyclerView recyclerView;
    RelativeLayout bottomsheet;
    BottomNavigationView bottomNavigationView;
    LinearLayout task_layout, categoryLayout;
    TextView  newtask , saveNewList  ,cancelList , NewList , newTextView , Personal , categoryTextview;
    EditText addnewlist;
    ArrayList<model>dataholder;

    DBhelper DB;
    // ArrayList <String> task;
    int position = 0;
    int  i =0;
    int categoryNo;
    FrameLayout frameLayout;

    task_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        horizontalScrollView =findViewById(R.id.horizontelList);
        frameLayout = findViewById(R.id.frameLayout);
        //   recyclerView        = findViewById(R.id.taskList);
        bottomNavigationView = findViewById(R.id.bottomNavbar);
        NewList = findViewById(R.id.NewList);
        Personal = findViewById(R.id.Personal);
        categoryLayout = findViewById(R.id.categoryLayout);
        dataholder = new ArrayList<>();
        DB = new DBhelper(this);
        // task = new ArrayList<>();
        fab = findViewById(R.id.fab);
        // getting our task array
        // list from db handler class.
        dataholder = DB.getData();

        // on below line passing our array list to our adapter class.
        adapter = new task_adapter(dataholder,MainActivity.this);
        recyclerView = findViewById(R.id.taskList);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerView.setAdapter(adapter);
        //  d.showData();
        if (isTableExists(DB.getWritableDatabase(), "Taskdetails")) {

        }
        else{

            Toast.makeText(this, "No Table", Toast.LENGTH_SHORT).show();
        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);
                EditText entertask = dialog.findViewById(R.id.enterTask);
                saveButton = dialog.findViewById(R.id.saveButton);
                showList = dialog.findViewById(R.id.showList);
                DB = new DBhelper(dialog.getContext());

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the text from edit text
                        String newdata = entertask.getText().toString().trim();
                        if(!newdata.isEmpty()){
                            DB.addrecord(newdata);
                            entertask.getText().clear();
                            Toast.makeText(dialog.getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(dialog.getContext(), "Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                        dataholder = DB.getData();

                        // on below line passing our array list to our adapter class.
                        adapter = new task_adapter(dataholder,MainActivity.this);
                        recyclerView = findViewById(R.id.taskList);

                        // setting layout manager for our recycler view.
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
                        recyclerView.setLayoutManager(linearLayoutManager);

                        // setting our adapter to recycler view.
                        recyclerView.setAdapter(adapter);
                        // Notify the adapter that the data has changed
                        recyclerView.getAdapter().notifyDataSetChanged();
                        // Close the bottomsheetlayout
                        dialog.dismiss();
                    }
                });
//                showList.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MainActivity.this, "showlist", Toast.LENGTH_SHORT).show();
//                    }
//                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }
    private void saveData(model data){
        SQLiteDatabase db = DB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("task", String.valueOf(data));
        long newRowId = db.insert("Taskdetails",null,cv);
        if(newRowId!= -1){
            dataholder.add(data);
            adapter.notifyItemInserted(dataholder.size()-1);
        }
    }

    public boolean isTableExists(SQLiteDatabase DB , String tableName){
        Cursor cursor = DB.rawQuery("Select name from sqlite_master where type='table' AND name=?",new String[]{tableName});
        return cursor!= null && cursor.moveToFirst();
    }

}

