package com.example.myearth;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileManager {
    private static final String PREFS_NAME = "UserProfilePrefs";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_SCORE = "score";

    public static void saveUserProfile(UserProfile userProfile, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_FIRSTNAME, userProfile.getFirstName());
        editor.putString(KEY_LASTNAME, userProfile.getLastName());
        editor.putInt(KEY_SCORE, userProfile.getScore());
        editor.apply();
    }

    public static UserProfile loadUserProfile(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String firstname = prefs.getString(KEY_FIRSTNAME, "");
        String lastname = prefs.getString(KEY_LASTNAME, "");
        int score = prefs.getInt(KEY_SCORE, 0);
        return new UserProfile(firstname, lastname, score);
    }
}