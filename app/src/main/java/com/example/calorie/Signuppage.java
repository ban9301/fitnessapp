package com.example.calorie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aichatbot.R;

public class Signuppage extends AppCompatActivity {
Button signup2,back2;
EditText id2,username2,age,height,weight;
Spinner gender;
SQLiteDatabase myDb;
Double BMR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        signup2=findViewById(R.id.BTNsignup2);
        back2=findViewById(R.id.BTNback2);
        id2=findViewById(R.id.ETid2);
        username2=findViewById(R.id.ETusername2);
        age=findViewById(R.id.ETage);
        height=findViewById(R.id.ETheight);
        weight=findViewById(R.id.ETweight);
        gender=findViewById(R.id.SPgender);

        myDb = openOrCreateDatabase("productsDB", Context.MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS products(id VARCHAR,username VARCHAR,age VARCHAR,weight VARCHAR,height VARCHAR,gender VARCHAR,bmr VARCHAR)");

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signuppage.this, calorie_MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id2.getText().toString().trim().length() == 0 ||
                        username2.getText().toString().trim().length() == 0 ||
                        age.getText().toString().trim().length() == 0 ||
                        height.getText().toString().trim().length() == 0 ||
                        weight.getText().toString().trim().length() == 0) {
                    Toast.makeText(Signuppage.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = myDb.rawQuery("SELECT * FROM products WHERE id='" + id2.getText() + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(Signuppage.this, "Select a Diff ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (gender.getSelectedItem().toString().trim().equals("Male")) {
                    BMR = 88.362 + (13.397 * Double.parseDouble(weight.getText().toString()))
                            + (4.799 * Double.parseDouble(height.getText().toString())) - (5.677 * Double.parseDouble(age.getText().toString()));
                } else if (gender.getSelectedItem().toString().trim().equals("Female")) {
                    BMR = 447.593 + (9.247 * Double.parseDouble(weight.getText().toString()))
                            + (3.098 * Double.parseDouble(height.getText().toString())) - (4.330 * Double.parseDouble(age.getText().toString()));
                }

                myDb.execSQL("INSERT INTO products VALUES('" + id2.getText() + "','" + username2.getText() + "','"+ age.getText() + "','" + weight.getText() + "','"+ height.getText() + "','" + gender.getSelectedItem().toString() + "','" + BMR.toString() + "');");
                Toast.makeText(Signuppage.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                Intent back = new Intent(Signuppage.this,Loginpage.class);
                startActivity(back);
                finish();
            }
        });
    }
}