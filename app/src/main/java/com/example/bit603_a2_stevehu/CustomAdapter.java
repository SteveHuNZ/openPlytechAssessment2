package com.example.bit603_a2_stevehu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<Map.Entry<String,Integer>> data;

    public CustomAdapter(Context context, Map<String,Integer> data) {
        this.context = context;
        this.data = new ArrayList<>(data.entrySet());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // build view for the list item
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // get data by position
        Map.Entry<String, Integer> item = data.get(position);

        //set data to the list item
        holder.nameText.setText(item.getKey());
        holder.quantityText.setText(String.valueOf(item.getValue()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameText,quantityText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.item_name);
            quantityText=itemView.findViewById(R.id.item_quantity);
        }
    }
}
