package com.example.myearth;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String firstName;
    private String lastName;
    private int score;

    public UserProfile(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }
}