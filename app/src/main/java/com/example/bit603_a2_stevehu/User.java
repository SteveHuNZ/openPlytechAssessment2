package com.example.bit603_a2_stevehu;

/**
 * used to store the account information
 */
public class User {
    private final String name;
    private final String password;
    private final int favouriteColour;

    public User(String name, String password, int favouriteColour) {
        this.name = name;
        this.password = password;
        this.favouriteColour = favouriteColour;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getFavouriteColour() {
        return favouriteColour;
    }
}
