package com.example.to_do_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class newlist extends AppCompatActivity {

    EditText addnewlist;
    TextView saveNewList , cancelList;

    LinearLayout categoryLayout;

    //    public newlist(){
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.activity_main, null);
//        categoryLayout = (LinearLayout)view.findViewById(R.id.categoryLayout);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlist);
        saveNewList = findViewById(R.id.saveNewList);
        addnewlist = findViewById(R.id.addnewlist);
        cancelList = findViewById(R.id.cancelList);


//        saveNewList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
          //      Toast.makeText(newlist.this, "Clicked", Toast.LENGTH_SHORT).show();
//                String text = addnewlist.getText().toString();
//                Button b = new Button(newlist.this);
//                b.setText(text);
//                b.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));
//                categoryLayout.addView(b);
//            }
//        });

    }
}
