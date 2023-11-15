package com.example.to_do_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.to_do_app.databinding.ActivityLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

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

    ArrayList<model>dataholder;

    DBhelper DB;
    // ArrayList <String> task;
    FrameLayout frameLayout;

    task_adapter adapter;
    SQLiteDatabase mDatabase;

    ActivityLoginBinding binding;
    private task_adapter.OnItemClickListener mListener;

    private SharedPreferences sharedPreferences;
    private int primaryKeyCounter;


     int value=1;
        int id;

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
        mDatabase = DB.getWritableDatabase();
        // task = new ArrayList<>();
        fab = findViewById(R.id.fab);
        // getting our task array
        // list from db handler class.
        dataholder = DB.getData();

        // on below line passing our array list to our adapter class.
        adapter = new task_adapter(dataholder,MainActivity.this);
        recyclerView = findViewById(R.id.taskList);

        sharedPreferences=getSharedPreferences("to-do_app_pref",Context.MODE_PRIVATE);
        primaryKeyCounter=sharedPreferences.getInt("primary_key_counter",0);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerView.setAdapter(adapter);

        //creating binding to add email to each task record
        binding=ActivityLoginBinding.inflate(getLayoutInflater());

       adapter.setOnItemClickListener(new task_adapter.OnItemClickListener() {
           @Override
           public void onItemClick(int position) {

           }

           @Override
           public void onDeleteClick(int position) {
             //  Toast.makeText(MainActivity.this, "Postion is : "+position, Toast.LENGTH_SHORT).show();
                removeItem(position);
           }
       });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);
                EditText entertask = dialog.findViewById(R.id.enterTask);
                TextView valueID = dialog.findViewById(R.id.valueID);
                saveButton = dialog.findViewById(R.id.saveButton);
                showList = dialog.findViewById(R.id.showList);
                DB = new DBhelper(dialog.getContext());

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get the text from edit text
                        String newdata = entertask.getText().toString().trim();
                        String primaryKey=Integer.toString(primaryKeyCounter);
                        String UserEmail = LoginActivity.UserEmail;
                        if(!newdata.isEmpty()){

                            DB.addrecord(primaryKey,newdata,UserEmail);
                            incrementPrimaryKeyCounter();
                            entertask.getText().clear();

                            Toast.makeText(dialog.getContext(), "email"+UserEmail, Toast.LENGTH_SHORT).show();
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

                        adapter.setOnItemClickListener(new task_adapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                            }

                            @Override
                            public void onDeleteClick(int position) {
                                //  Toast.makeText(MainActivity.this, "Postion is : "+position, Toast.LENGTH_SHORT).show();
                                removeItem(position);
                            }
                        });
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

        // Set an OnNavigationItemSelectedListener for the BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.calender:
                       Intent intent = new Intent(MainActivity.this,CalenderActivity.class);
                       startActivity(intent);
                    case R.id.drawer:

                    case R.id.task:

                    case R.id.profile:
                }
                return false;
            }
        });
    }

    private void incrementPrimaryKeyCounter(){
        primaryKeyCounter++;
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("primary_key_counter",primaryKeyCounter);
        editor.apply();
    }

    public void removeItem(int position){
        mDatabase.delete("Taskdetails","primaryKey=?",new String[]{String.valueOf(position)});
      //  dataholder.remove(position);
        adapter.notifyItemRemoved(position);

    }
}

