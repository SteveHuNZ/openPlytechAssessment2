package com.example.bit603_a2_stevehu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private InventoryDbHelper inventoryDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //find control views
        EditText nameInput = findViewById(R.id.add_name);
        EditText quantityInput = findViewById(R.id.add_quantity);
        Button addButton = findViewById(R.id.add_submit);

        // initialize database helper
        inventoryDbHelper = new InventoryDbHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString().trim();
                String quantityText = quantityInput.getText().toString().trim();

                //validate input values
                if(name.length()==0||quantityText.length()==0){
                    Toast.makeText(AddItemActivity.this, R.string.invalid_add_item_input,Toast.LENGTH_LONG).show();
                    return;
                }
                int quantity = Integer.parseInt(quantityText);

                // save data to database
                boolean success = inventoryDbHelper.updateItem(name, quantity);
                if(!success){
                    // failed tips
                    Toast.makeText(AddItemActivity.this, R.string.failed_msg,Toast.LENGTH_LONG).show();
                }else{
                    //success tips
                    Toast.makeText(AddItemActivity.this, R.string.add_success,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // release database
        inventoryDbHelper.close();
        super.onDestroy();
    }
}