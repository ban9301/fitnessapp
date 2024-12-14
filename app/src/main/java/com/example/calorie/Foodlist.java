package com.example.calorie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aichatbot.R;

import java.util.ArrayList;

public class Foodlist extends AppCompatActivity {
ListView foodlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        foodlist=findViewById(R.id.LVfoodlist);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Kiwi");
        arrayList.add("Tomato");
        arrayList.add("Cucumber");
        arrayList.add("Bell Pepper");
        arrayList.add("Potato");
        arrayList.add("Salad");
        arrayList.add("Fish");
        arrayList.add("Steak");
        arrayList.add("Chicken");
        arrayList.add("Rice");
        arrayList.add("Egg");
        arrayList.add("Milk");
        arrayList.add("Orange Juice");
        arrayList.add("Carrot Juice");

        ArrayAdapter arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        foodlist.setAdapter(arrayAdapter);

        foodlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent I=new Intent(Foodlist.this,Fooddetails.class);
                I.putExtra("POSITION",i);
                startActivity(I);
                finish();
            }
        });
    }
}