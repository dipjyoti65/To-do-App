package com.example.to_do_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class fragmnet_adapter extends RecyclerView.Adapter<fragmnet_adapter.ViewHolder> {

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

    public List<String> mitems2;

    public fragmnet_adapter(List<String>mitems2){
        this.mitems2 = mitems2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new View holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the text from the mItems
        String text = mitems2.get(position);
        // Set the text to the TextView
        holder.getTextView().setText(text);
    }


    @Override
    public int getItemCount() {
        // Return the number of items in the recycler view
        return mitems2.size();
    }

}
