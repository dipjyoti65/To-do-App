package com.example.to_do_app;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Personal_fragment extends Fragment {

    FloatingActionButton fab2, fab;
    RecyclerView recyclerView;
    List<String> mItems2;
    Button saveButton , showList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myfragment, container, false);
        // Get the view with the ID
//        TextView textView = (TextView) view.findViewById(R.id.fragtext);
//        String text = "Fragment";
//        textView.setText(text);
        // recyclerView = view.findViewById(R.id.taskList);
        fab2 = view.findViewById(R.id.fab2);
        mItems2 = new ArrayList<>();
        // setting the adapter
        recyclerView = view.findViewById(R.id.taskList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmnet_adapter f = new fragmnet_adapter(mItems2);
        recyclerView.setAdapter(f);
        // OnClickListener applied to floatingactionbutton
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        bottomsheet = findViewById(R.id.bottomsheet);
//        task_layout = findViewById(R.id.task_layout);
//        newtask = findViewById(R.id.newtask);
                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);
                EditText entertask = dialog.findViewById(R.id.enterTask);
                saveButton = dialog.findViewById(R.id.saveButton);
                showList = dialog.findViewById(R.id.showList);

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the text from edit text
                        String value = entertask.getText().toString();

                        // Add the task to the mItems2
                        mItems2.add(value);

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
        return  view;

    }

}