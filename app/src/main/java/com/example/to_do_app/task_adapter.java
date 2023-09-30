package com.example.to_do_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class task_adapter extends RecyclerView.Adapter<task_adapter.ViewHolder> {

    private ArrayList<model> dataholder;
    private Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView newtask;

        public ViewHolder(View view) {
            super(view);
            newtask = view.findViewById(R.id.newtask);
        }

        public TextView getTextView() {
            return newtask;
        }
    }
    public task_adapter(ArrayList<model>dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new View holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newtask.setText(dataholder.get(position).getTask());
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the recycler view
        return dataholder.size();
    }

}
