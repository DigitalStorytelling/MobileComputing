package com.example.myearth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.ekn.gruzer.gaugelibrary.FullGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import android.widget.TextView;

public class Motivation extends AppCompatActivity {
    private FullGauge gauge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_motivation);

        gauge = findViewById(R.id.fullGauge);
        gauge.setMaxValue(850);

        Range range = new Range();
        range.setColor(Color.parseColor("#ce0000"));
        range.setFrom(0.0);
        range.setTo(283.0);

        Range range2 = new Range();
        range2.setColor(Color.parseColor("#E3E500"));
        range2.setFrom(283.0);
        range2.setTo(566.0);

        Range range3 = new Range();
        range3.setColor(Color.parseColor("#00b20b"));
        range3.setFrom(566.0);
        range3.setTo(850.0);

        List<Range> ranges = Arrays.asList(range, range2, range3);

        gauge.setRanges(ranges);

        UserProfile userProfile = UserProfileManager.loadUserProfile(this);
        gauge.setValue(userProfile.getScore());


        ImageButton back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Motivation.this, Navigator.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}