package com.example.bit603_a2_stevehu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check logged-in user. jump to login screen as needed
        CustomApplication application = (CustomApplication) getApplication();
        User user = application.getCurrentUser();
        if (user == null) {
            goToLogin();
            return;
        }

        setContentView(R.layout.activity_menu);

        TextView welcomeView = findViewById(R.id.menu_welcome);
        Button listButton = findViewById(R.id.menu_list);
        Button addButton = findViewById(R.id.menu_add);
        Button logoutButton = findViewById(R.id.menu_logout);

        //set the welcome message with user's name
        welcomeView.setText(getString(R.string.menu_welcome,user.getName()));
        //set the colour of the welcome message
        welcomeView.setTextColor(getResources().getColor(user.getFavouriteColour()));

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jump to the Inventory Status screen
                startActivity(new Intent(MenuActivity.this, ItemListActivity.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jump to the Item Add Screen
                startActivity(new Intent(MenuActivity.this, AddItemActivity.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear the logged-in user
                application.setCurrentUser(null);
                //jump to the login screen
                goToLogin();
            }
        });
    }

    private void goToLogin() {
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
        finish();
    }
}