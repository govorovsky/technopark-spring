package com.example.agovorovskiy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_VALUE = "value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView view = findViewById(R.id.my_text);
        view.setText("0");

        Button button = findViewById(R.id.my_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence viewText = view.getText();
                Integer i = Integer.parseInt(viewText.toString());
                i++;
                view.setText(i.toString());
            }
        });

        Context context = this;
        Button openActivity = findViewById(R.id.open_acitivty);
        openActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_VALUE, view.getText());
                startActivity(intent);
            }
        });
    }
}
