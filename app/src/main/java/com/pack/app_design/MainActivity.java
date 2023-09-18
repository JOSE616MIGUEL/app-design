package com.pack.app_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pack.app_design.dashboard.MainActivityDashboard;
import com.pack.app_design.dashboard.SplashActivityDashboard;

public class MainActivity extends AppCompatActivity {

    Button btndashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndashboard = findViewById(R.id.btndashboard);
        btndashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SplashActivityDashboard.class));
                finish();
            }
        });

    }
}