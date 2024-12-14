package com.example.aichatbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class HomePage extends AppCompatActivity {
    ImageButton btnMap;
    ImageButton btnChatBot;
    ImageButton btnSOS;
    ImageButton btnCalorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        btnMap = findViewById(R.id.imgBtnMap);
        btnChatBot = findViewById(R.id.imgBtnChatBot);
        btnSOS = findViewById(R.id.img_sos_sms_phonecall);
        btnCalorie = findViewById(R.id.imgCalorie);

        btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomePage.this, MapsActivity.class));
                }
            });

            btnChatBot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomePage.this, MainActivity.class));
                }
            });
            btnCalorie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomePage.this, com.example.calorie.calorie_MainActivity.class));
                }
            });
            btnSOS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomePage.this, com.example.sparkwomen.SplashActivity.class));
                }
            });

        }
    }

