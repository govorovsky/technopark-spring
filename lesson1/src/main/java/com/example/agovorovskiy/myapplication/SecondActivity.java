package com.example.agovorovskiy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(MainActivity.EXTRA_VALUE);
        TextView v = findViewById(R.id.my_text_1);
        v.setText(stringExtra);

        v = findViewById(R.id.my_text_2);
        v.setText(stringExtra);
    }
}
