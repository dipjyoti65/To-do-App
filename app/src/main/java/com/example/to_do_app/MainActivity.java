package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    Button saveButton , showList ;
    DrawerLayout drawerLayout;
    HorizontalScrollView horizontalScrollView;
    RecyclerView recyclerView;
    RelativeLayout bottomsheet;
    BottomNavigationView bottomNavigationView;
    LinearLayout task_layout, categoryLayout;
    TextView newtask , saveNewList  ,cancelList , NewList , newTextView;
    EditText addnewlist;

    List<String> mItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        horizontalScrollView =findViewById(R.id.horizontelList);
        recyclerView        = findViewById(R.id.taskList);
        bottomNavigationView = findViewById(R.id.bottomNavbar);
        NewList = findViewById(R.id.NewList);


        mItems = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        NewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {CreateList();}
        });

        recyclerView = findViewById(R.id.taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        task_adapter c = new task_adapter(mItems);
        recyclerView.setAdapter(c);

    }


    public void  showBottomSheetDialog(){

        bottomsheet = findViewById(R.id.bottomsheet);
        task_layout = findViewById(R.id.task_layout);
        newtask = findViewById(R.id.newtask);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);


        EditText  entertask = dialog.findViewById(R.id.enterTask);
        saveButton = dialog.findViewById(R.id.saveButton);
        showList = dialog.findViewById(R.id.showList);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from edit text
                String value = entertask.getText().toString();

                // Add the task to the mItems
                mItems.add(value);

                // Notify the adapter that the data has changed
                recyclerView.getAdapter().notifyDataSetChanged();

                // Close the bottomsheetlayout
                  dialog.dismiss();
            }
        });


        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "showlist", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    public void CreateList(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.newlist);

        saveNewList = dialog.findViewById(R.id.saveNewList);
        addnewlist = dialog.findViewById(R.id.addnewlist);
        cancelList = dialog.findViewById(R.id.cancelList);
        categoryLayout =findViewById(R.id.categoryLayout);

        saveNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = addnewlist.getText().toString();
                TextView b = new TextView(MainActivity.this);
                b.setText(text);
                b.setTextColor(getResources().getColor(R.color.white));
                b.setBackgroundResource(R.drawable.custom_button);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.
                        LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10 , 0 , 10 , 0);
                b.setLayoutParams(layoutParams);
                Toast.makeText(MainActivity.this, "list-Added", Toast.LENGTH_SHORT).show();
                categoryLayout.addView(b);
            }
        });

        cancelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);

    }



}