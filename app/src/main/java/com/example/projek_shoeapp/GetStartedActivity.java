package com.example.projek_shoeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GetStartedActivity extends AppCompatActivity {
    Button buttonStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        buttonStarted=findViewById(R.id.btnStarted);
        buttonStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(GetStartedActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}