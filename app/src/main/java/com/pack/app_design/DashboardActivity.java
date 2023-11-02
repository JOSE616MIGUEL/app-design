package com.pack.app_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pack.app_design.comercio.MainActivityComercio;
import com.pack.app_design.dashboard.MainActivityDashboard;
import com.pack.app_design.finanzas.MainActivityFinanza;
import com.pack.app_design.gastronomia.MainActivityRestaurant;
import com.pack.app_design.medicinasalud.MainActivityMedicina;
import com.pack.app_design.turismo.MainActivityTurismo;

public class DashboardActivity extends AppCompatActivity {

    Button btnturismo, btngastronomia, btnmedicina, btnfinanzas, btncomercio, btndashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnturismo = findViewById(R.id.btnturismo);
        btngastronomia = findViewById(R.id.btngastronomia);
        btnmedicina = findViewById(R.id.btnmedicina);
        btnfinanzas = findViewById(R.id.btnfinanza);
        btncomercio = findViewById(R.id.btncomercio);
        btndashboard = findViewById(R.id.btndashboard);

        btnturismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivityTurismo.class));
                finish();
            }
        });

        btngastronomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivityRestaurant.class));
                finish();
            }
        });

        btnmedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivityMedicina.class));
                finish();
            }
        });

        btnfinanzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivityFinanza.class));
                finish();
            }
        });

        btncomercio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivityComercio.class));
                finish();
            }
        });

        btndashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivityDashboard.class));
                finish();
            }
        });
    }
}