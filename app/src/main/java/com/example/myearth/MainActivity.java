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

        fetchUserProfiles();

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

    private void fetchUserProfiles() {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://192.168.188.73/UserProfiles");
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
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
        } catch (Exception e) {
            Log.e("Error", "Failed to fetch user profiles", e);
            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to fetch user profiles", Toast.LENGTH_LONG).show());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
