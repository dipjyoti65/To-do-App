package com.example.to_do_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class task_adapter extends RecyclerView.Adapter<task_adapter.ViewHolder> {

    private ArrayList<model> dataholder;
    private Context context;
    private OnItemClickListener mListener;
    private SharedPreferences sharedPreferences;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView newtask,textID;
        public ToggleButton deleteTask;

        public ViewHolder(View view , final OnItemClickListener listener) {
            super(view);
            newtask = view.findViewById(R.id.newtask);
            textID=view.findViewById(R.id.textID);
            deleteTask =view.findViewById(R.id.deleteTask);
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
        ViewHolder viewHolder = new ViewHolder(view, (OnItemClickListener) mListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textID.setText(dataholder.get(position).getPrimaryKey());
        holder.newtask.setText(dataholder.get(position).getTask());
        // Set a click listener for the delete button
        holder.deleteTask.setOnClickListener(new View.OnClickListener() {

            final int itemPosition=holder.getAdapterPosition();
            final String Value= (String) holder.textID.getText();
            final int idValue= Integer.parseInt(Value);

            @Override
            public void onClick(View v) {
                    Toast.makeText(context, "idvalue" + idValue, Toast.LENGTH_SHORT).show();
                        mListener.onDeleteClick(idValue);
                        //Remove the item from the list

                        dataholder.remove(itemPosition);

                        //Notify the adapter of the deletion

                        notifyItemRemoved(itemPosition);
                        // Update the positions for the remaining items
                        notifyItemRangeChanged(itemPosition, getItemCount());

            }
        });
    }



    @Override
    public int getItemCount() {
        // Return the number of items in the recycler view
        return dataholder.size();
    }

}
