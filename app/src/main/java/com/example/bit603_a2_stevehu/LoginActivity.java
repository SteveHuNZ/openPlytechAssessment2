package com.example.bit603_a2_stevehu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    /**
     * initialize the built-in users
     */
    private static final List<User> USERS = Arrays.asList(
            new User("Jason", "Sword", R.color.Red),
            new User("Billy", "Dinosaur", R.color.Blue),
            new User("Zack", "Elephant", R.color.Black),
            new User("Trini", "Tiger", R.color.Yellow),
            new User("Kimberly", "Bird", R.color.Pink)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CustomApplication app = (CustomApplication) getApplication();

        EditText usernameInput = findViewById(R.id.login_username);
        EditText passwordInput = findViewById(R.id.login_password);
        Button login = findViewById(R.id.login_submit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tUser = usernameInput.getText().toString().trim();
                String tPass = passwordInput.getText().toString().trim();

                User user = validate(tUser, tPass);

                if (user == null) {
                    //show invalid tips
                    Toast.makeText(LoginActivity.this, R.string.invalid_login_input, Toast.LENGTH_LONG).show();
                    //clear the password input
                    passwordInput.setText("");
                    return;
                }

                //set the logged-in user
                app.setCurrentUser(user);
                // jump to the menu screen
                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                finish();
            }
        });
    }

    /**
     * use the built-in users to validate the input data
     *
     * @param tUser
     * @param tPass
     * @return
     */
    private User validate(String tUser, String tPass) {
        for (User one : USERS) {
            if (Objects.equals(one.getName(), tUser) && Objects.equals(one.getPassword(), tPass)) {
                return one;
            }
        }
        return null;
    }
}