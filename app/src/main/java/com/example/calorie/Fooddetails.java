package com.example.calorie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aichatbot.R;

public class Fooddetails extends AppCompatActivity {
ImageView foodimage;
TextView foodinfo;
EditText quantity;
Button add,cancel;
SQLiteDatabase myDb;
Double t = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetails);

        foodimage=findViewById(R.id.IVfoodimage);
        foodinfo=findViewById(R.id.TVfoodinfo);
        quantity=findViewById(R.id.ETquantity);
        add=findViewById(R.id.BTNadd);
        cancel=findViewById(R.id.BTNcancel);

        myDb = openOrCreateDatabase("productsDB", Context.MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS products(id VARCHAR,username VARCHAR,age VARCHAR,weight VARCHAR,height VARCHAR,gender VARCHAR,bmr VARCHAR)");

        Intent i=getIntent();
        int position=i.getIntExtra("POSITION",0);
        foodimage.setImageResource(FoodData.foodimages[position]);
        foodinfo.setText("Calories per 100 grams: " + FoodData.foodcalories[position]);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t = Double.parseDouble(quantity.getText().toString()) * FoodData.foodcalories[position];
                Cursor c=myDb.rawQuery("SELECT * FROM products WHERE id='"+Loginpage.I+"'",null);
                if(c.moveToFirst()) {
                        Double T = Double.parseDouble(c.getString(6)) - t;
                    myDb.execSQL("UPDATE products set bmr='"+Double.toString(T)+"' WHERE id='"+Loginpage.I+"'");
                }
                Intent i=new Intent(Fooddetails.this,Profile.class);
                startActivity(i);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(Fooddetails.this,Profile.class);
                startActivity(ii);
                finish();
            }
        });
    }
}