package com.example.WSKPolice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity {

   Intent intent;
   LinearLayout criminalCases;
   LinearLayout departments;
   LinearLayout wanted;
   LinearLayout detectives;
   LinearLayout photoRobot;
   LinearLayout aboutUs;
   LinearLayout logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        criminalCases = findViewById(R.id.CriminalLayout);
        departments = findViewById(R.id.DepartLayout);
        wanted = findViewById(R.id.WantedLayout);
        detectives = findViewById(R.id.DetectivesLayout);
        photoRobot = findViewById(R.id.PhotoRobotLayout);
        aboutUs = findViewById(R.id.AboutLayout);
        logout = findViewById(R.id.LogoutLayout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                String checkbox = preferences.getString("remember", "");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                finish();
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity("About");
            }
        });

        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity("Department");
            }
        });

        wanted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity("Wanted");
            }
        });

        photoRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity("PhotoRobot");
            }
        });

        criminalCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity("CriminalCases");
            }
        });

        detectives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity("Detectives");
            }
        });
    }

    public void goToActivity(String activity) {
        switch (activity) {
            case "About":
                intent = new Intent(this, About.class);
                break;
            case "Department":
                intent = new Intent(this, Department.class);
                break;
            case "Wanted":
                intent = new Intent(this, Wanted.class);
                break;
            case "PhotoRobot":
                intent = new Intent(this, PhotoRobot.class);
                break;
            case "CriminalCases":
                intent = new Intent(this, CriminalCases.class);
                break;
            case "Detectives":
                intent = new Intent(this, Detectives.class);
            default:
                break;
        }
        startActivity(intent);
    }
}