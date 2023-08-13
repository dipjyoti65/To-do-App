package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

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
    List<String> mItems;
 //   List<TextView> categoryTextview;
    List<Fragment> categoryFragmentMap;
    List<Integer> categoryID;

    int position = 0;
    int  i =0;
    int categoryNo;
    FrameLayout frameLayout;

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
       // categoryNo= categoryLayout.getChildCount();
       // Personal.setOnClickListener(getOnClick(i));

        mItems = new ArrayList<>();
        categoryFragmentMap = new ArrayList<>();
        categoryFragmentMap.add(new MyFragment());
        // OnClickListener applied to floatingactionbutton
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showBottomSheetDialog();
//            }
//        });

       FragmentManager f = getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = f.beginTransaction();
       fragmentTransaction.replace(R.id.listviewContainer,new Personal_fragment());
       fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
        //Creating new list by clicking on newlist textview of horizontel scrollview
        NewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {CreateList();}
        });
        recyclerView = findViewById(R.id.taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        task_adapter c = new task_adapter(mItems);
        recyclerView.setAdapter(c);

    }



//    public void  showBottomSheetDialog(){
//
////        bottomsheet = findViewById(R.id.bottomsheet);
////        task_layout = findViewById(R.id.task_layout);
////        newtask = findViewById(R.id.newtask);
//        final Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.bottomsheetlayout);
//        EditText  entertask = dialog.findViewById(R.id.enterTask);
//        saveButton = dialog.findViewById(R.id.saveButton);
//        showList = dialog.findViewById(R.id.showList);
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Get the text from edit text
//                String value = entertask.getText().toString();
//
//                // Add the task to the mItems
//                mItems.add(value);
//
//                // Notify the adapter that the data has changed
//                recyclerView.getAdapter().notifyDataSetChanged();
//
//                // Close the bottomsheetlayout
//                  dialog.dismiss();
//            }
//        });
//
//
//        showList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "showlist", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//        dialog.show();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//
//    }

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
                i++;
                String text = addnewlist.getText().toString();
                categoryTextview = new TextView(MainActivity.this);
                categoryTextview.setText(text);
                categoryTextview.setTextColor(getResources().getColor(R.color.white));
                categoryTextview.setBackgroundResource(R.drawable.custom_button);
                categoryTextview.setId(androidx.core.R.id.text);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.
                        LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10 , 0 , 10 , 0);
                categoryTextview.setLayoutParams(layoutParams);
                Toast.makeText(MainActivity.this, "list-Added", Toast.LENGTH_SHORT).show();
                categoryLayout.addView( categoryTextview);
                categoryTextview.setOnClickListener(getOnClick(i));
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

    private View.OnClickListener getOnClick(final int i){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // categoryFragmentMap.add(new MyFragment());
                Toast.makeText(MainActivity.this, "clicked "+i, Toast.LENGTH_SHORT).show();
                loadFragment(new MyFragment());
            }
        };

    }

    private void loadFragment(Fragment fragment){

        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.listviewContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        // save the changes
        fragmentTransaction.commit();

    }


}

