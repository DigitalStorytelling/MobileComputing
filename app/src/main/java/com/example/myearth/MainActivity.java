package com.example.myearth;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private HashMap<String, UserProfile> userMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build()); // Not recommended for production; use AsyncTask or similar approaches

        loadJsonFromAssets();

        Button loginButton = findViewById(R.id.button_login);
        usernameField = findViewById(R.id.username);
        ImageView imageView = findViewById(R.id.imageview);

        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/4778/4778362.png").into(imageView);

        loginButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            UserProfile userProfile = userMap.get(username);
            if (userProfile != null) {
                UserProfileManager.saveUserProfile(userProfile, this);
                Intent intent = new Intent(MainActivity.this, Navigator.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Benutzername nicht gefunden", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadJsonFromAssets() {
        try {
            InputStream is = getAssets().open("profiles.JSON");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            parseUserProfiles(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading local JSON", Toast.LENGTH_SHORT).show();
        }
    }

    private void parseUserProfiles(JSONObject jsonObject) {
        try {
            JSONArray profiles = jsonObject.getJSONArray("UserProfiles");
            for (int i = 0; i < profiles.length(); i++) {
                JSONObject profile = profiles.getJSONObject(i);
                String username = profile.getString("username");
                String firstName = profile.getString("firstName");
                String lastName = profile.getString("lastName");
                int score = profile.getInt("score");
                UserProfile userProfile = new UserProfile(firstName, lastName, score);
                userMap.put(username, userProfile);
            }
            Toast.makeText(MainActivity.this, "Profiles loaded!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error parsing JSON", Toast.LENGTH_LONG).show();
        }
    }
}
