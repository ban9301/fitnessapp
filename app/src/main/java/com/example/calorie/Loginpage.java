package com.example.calorie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aichatbot.R;

public class Loginpage extends AppCompatActivity {
Button login2,back1;
EditText id1,username1;
SQLiteDatabase myDb;
static String I,U;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        login2=findViewById(R.id.BTNlogin2);
        back1=findViewById(R.id.BTNback1);
        id1=findViewById(R.id.ETid1);
        username1=findViewById(R.id.ETusername1);

        myDb = openOrCreateDatabase("productsDB", Context.MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS products(id VARCHAR,username VARCHAR,age VARCHAR,weight VARCHAR,height VARCHAR,gender VARCHAR,bmr VARCHAR)");

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Loginpage.this, calorie_MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id1.getText().toString().trim().length() == 0 ||
                    username1.getText().toString().trim().length() == 0) {
                    Toast.makeText(Loginpage.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = myDb.rawQuery("SELECT * FROM products WHERE id='" + id1.getText() + "'", null);
                Cursor cc = myDb.rawQuery("SELECT * FROM products WHERE username='" + username1.getText() + "'", null);

                if(c.moveToFirst() && cc.moveToFirst()) {
                    if(c.getString(1).trim().equals(username1.getText().toString())) {
                        I = id1.getText().toString();
                        U = username1.getText().toString();
                        Intent a = new Intent(Loginpage.this, Profile.class);
                        startActivity(a);
                        finish();
                    }else Toast.makeText(Loginpage.this, " ID and Username are not Related", Toast.LENGTH_SHORT).show();
                } else

                    Toast.makeText(Loginpage.this, " ID or Username incorrect", Toast.LENGTH_SHORT).show();

            }
        });

    }
}