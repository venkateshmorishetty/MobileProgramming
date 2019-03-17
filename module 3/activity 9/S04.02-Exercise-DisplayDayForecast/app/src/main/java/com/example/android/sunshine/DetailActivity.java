package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private String recievedData;
    private TextView di;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        di = findViewById(R.id.display);
        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent in = getIntent();
        if(in.hasExtra(Intent.EXTRA_TEXT)){
            recievedData = in.getStringExtra(Intent.EXTRA_TEXT);
            di.setText(recievedData);
        }
    }
}