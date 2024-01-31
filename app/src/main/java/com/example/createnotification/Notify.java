package com.example.createnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class Notify extends AppCompatActivity {

   TextView textview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        textview=findViewById(R.id.tvdata);
        String data=getIntent().getStringExtra("data");
        textview.setText(data);

    }
}