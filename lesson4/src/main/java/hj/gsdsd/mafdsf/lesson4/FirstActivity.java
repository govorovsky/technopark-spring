package hj.gsdsd.mafdsf.lesson4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class FirstActivity extends BaseActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.dynamic_text);
        findViewById(R.id.second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });

        findViewById(R.id.set_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.setText(String.valueOf(new Random().nextInt()));
            }
        });
    }
}
