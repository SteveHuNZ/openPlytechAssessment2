package com.example.bit603_a2_stevehu;

import android.app.Application;

public class CustomApplication extends Application {
    /**
     * store the login user
     */
    private User currentUser;

    /**
     * obtain the current user
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * set the current user
     * @param currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
