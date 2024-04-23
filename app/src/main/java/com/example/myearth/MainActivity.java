package com.example.myearth;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.squareup.picasso.Picasso;
import android.widget.Toast;

import android.widget.EditText;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private EditText usernameField;
    private HashMap<String, UserProfile> userMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        userMap.put("jba", new UserProfile("Janina", "Bach",  0));
        userMap.put("lsc", new UserProfile("Lena", "Scheit", 0));

        Button loginButton = findViewById(R.id.button_login);
        usernameField = findViewById(R.id.username);
        ImageView imageView = findViewById(R.id.imageview);

        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/4778/4778362.png")
                .into(imageView);

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
}