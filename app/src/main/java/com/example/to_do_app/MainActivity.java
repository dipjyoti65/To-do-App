package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
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
    Button saveButton , showList;
    DrawerLayout drawerLayout;
    HorizontalScrollView horizontalScrollView;
    RecyclerView recyclerView;
    RelativeLayout bottomsheet;
    BottomNavigationView bottomNavigationView;
    LinearLayout task_layout;
    TextView newtask;

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


        mItems = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
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
}