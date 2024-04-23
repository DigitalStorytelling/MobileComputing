package com.example.myearth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Navigator extends AppCompatActivity {
    private TextView welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigator);

        welcomeText = findViewById(R.id.welcome_text);

        UserProfile userProfile = UserProfileManager.loadUserProfile(this);
        welcomeText.setText("Hallo " + userProfile.getFirstName() + ",");



        Button firstStation = findViewById(R.id.button_first_station);
        firstStation.setOnClickListener(v -> {
            Intent intent = new Intent(Navigator.this, FirstStation.class);
            startActivity(intent);
        });
        Button secondStation = findViewById(R.id.button_second_station);
        secondStation.setOnClickListener(v -> {
            Intent intent = new Intent(Navigator.this, SecondStation.class);
            startActivity(intent);
        });
        Button thirdStation = findViewById(R.id.button_third_station);
        thirdStation.setOnClickListener(v -> {
            Intent intent = new Intent(Navigator.this, ThirdStation.class);
            startActivity(intent);
        });
        Button fourthStation = findViewById(R.id.button_fourth_station);
        fourthStation.setOnClickListener(v -> {
            Intent intent = new Intent(Navigator.this, FourthStation.class);
            startActivity(intent);
        });
        Button fithStation = findViewById(R.id.button_fith_station);
        fithStation.setOnClickListener(v -> {
            Intent intent = new Intent(Navigator.this, FithStation.class);
            startActivity(intent);
        });
        Button checkReward = findViewById(R.id.check_reward);
        checkReward.setOnClickListener(v -> {
            Intent intent = new Intent(Navigator.this, Motivation.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.navigator), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}