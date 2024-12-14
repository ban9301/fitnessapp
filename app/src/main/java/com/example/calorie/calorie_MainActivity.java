package com.example.calorie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aichatbot.R;

public class calorie_MainActivity extends AppCompatActivity {
Button login1,signup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie_activity_main);

        login1=findViewById(R.id.BTNlogin1);
        signup1=findViewById(R.id.BTNsignup1);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(calorie_MainActivity.this,Loginpage.class);
                startActivity(i);
                finish();
            }
        });

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(calorie_MainActivity.this,Signuppage.class);
                startActivity(ii);
                finish();
            }
        });
    }
}