package com.example.bit603_a2_stevehu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ItemListActivity extends AppCompatActivity {
    private InventoryDbHelper inventoryDbHelper;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //initialize the database helper
        inventoryDbHelper = new InventoryDbHelper(this);

        //encapsulate data into the CustomAdapter
        customAdapter = new CustomAdapter(this, inventoryDbHelper.getAllItems());
        //set the CustomAdapter to the recyclerView
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        inventoryDbHelper.close();
        super.onDestroy();
    }
}