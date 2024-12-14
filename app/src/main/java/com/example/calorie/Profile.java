package com.example.calorie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aichatbot.R;

public class Profile extends AppCompatActivity {
TextView infodisplay,BMR;
Button the_eaten_food,Return,reset,delete;

SQLiteDatabase myDb;
Double R_BMR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        infodisplay=findViewById(R.id.TVinfodisplay);
        the_eaten_food=findViewById(R.id.BTNchooseeatenfood);
        Return=findViewById(R.id.BTNreturn);
        reset=findViewById(R.id.BTNreset);
        delete=findViewById(R.id.BTNdelete);
        BMR=findViewById(R.id.TVBMR);

        myDb = openOrCreateDatabase("productsDB", Context.MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS products(id VARCHAR,username VARCHAR,age VARCHAR,weight VARCHAR,height VARCHAR,gender VARCHAR,bmr VARCHAR)");

        Cursor c = myDb.rawQuery("SELECT * FROM products WHERE id='" + Loginpage.I + "'", null);
        Cursor cc = myDb.rawQuery("SELECT * FROM products WHERE username='" + Loginpage.U + "'", null);

        if(c.moveToFirst() && cc.moveToFirst()) {
            if (c.getString(1).trim().equals(Loginpage.U)) {
                infodisplay.setText("ID: " + c.getString(0) +
                        "\nUsername: " + c.getString(1) +
                        "\nAge: " + c.getString(2) +
                        "\nWeight: " + c.getString(3) + " Kg" +
                        "\nHeight: " + c.getString(4) + " cm" +
                        "\nGender: " + c.getString(5) +
                        "\n-------------------------------------------------\n");
                int IntBMR1 = (int) Math.round(Double.parseDouble(c.getString(6)));
                BMR.setText("Your Daily Calories: " + IntBMR1);
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = myDb.rawQuery("SELECT * FROM products WHERE id='" + Loginpage.I + "'", null);
                if (c.moveToFirst()) {
                    if (c.getString(5).trim().equals("Male")) {
                        R_BMR = 88.362 + (13.397 * Double.parseDouble(c.getString(3)))
                                + (4.799 * Double.parseDouble(c.getString(4))) - (5.677 * Double.parseDouble(c.getString(2)));
                    } else if (c.getString(5).trim().equals("Female")) {
                        R_BMR = 447.593 + (9.247 * Double.parseDouble(c.getString(3)))
                                + (3.098 * Double.parseDouble(c.getString(4))) - (4.330 * Double.parseDouble(c.getString(2)));
                    }
                    myDb.execSQL("UPDATE products set bmr='" + Double.toString(R_BMR) + "' WHERE id='" + Loginpage.I + "'");
                    int IntBMR2 = (int) Math.round(R_BMR);
                    BMR.setText("Your Daily Calories: " + IntBMR2);
                }
            }
        });

        the_eaten_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Profile.this,Foodlist.class);
                startActivity(i);
                finish();
            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(Profile.this,Loginpage.class);
                startActivity(ii);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = myDb.rawQuery("SELECT * FROM products WHERE id='" + Loginpage.I + "'", null);
                if(c.moveToFirst()){
                    myDb.execSQL("DELETE FROM products WHERE id='" + Loginpage.I + "'");
                }
                Intent iii=new Intent(Profile.this,Loginpage.class);
                startActivity(iii);
                finish();
            }
        });
    }
}