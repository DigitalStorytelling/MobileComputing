package com.example.myearth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_station);
        ImageButton back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SecondStation.this, Navigator.class);
            startActivity(intent);
        });

        Button increaseScoreButton = findViewById(R.id.trash_to_points);
        increaseScoreButton.setOnClickListener(v -> {
            UserProfileManager.updateScore(this, 500);
        });
    }
}