package hj.gsdsd.mafdsf.lesson4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import hj.gsdsd.mafdsf.lesson4.fragment_part.activity.CooperationActivity;
import hj.gsdsd.mafdsf.lesson4.fragment_part.activity.LayoutFragmentActivity;
import hj.gsdsd.mafdsf.lesson4.fragment_part.activity.MultiActionActivity;
import hj.gsdsd.mafdsf.lesson4.fragment_part.activity.StateLossActivity;
import hj.gsdsd.mafdsf.lesson4.fragment_part.activity.TransactionActivity;

public class FirstActivity extends BaseActivity {

    public static final String STATE = "state";
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.dynamic_text);
        restoreState(savedInstanceState);
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

        initFragmentRelated();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String string = savedInstanceState.getString(STATE);
            mText.setText(string);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE, mText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initFragmentRelated() {
        findViewById(R.id.btn_layout_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, LayoutFragmentActivity.class));
            }
        });

        findViewById(R.id.btn_transactions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, TransactionActivity.class));
            }
        });

        findViewById(R.id.btn_multi_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, MultiActionActivity.class));
            }
        });

        findViewById(R.id.btn_state_loss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, StateLossActivity.class));
            }
        });

        findViewById(R.id.btn_cooperation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, CooperationActivity.class));
            }
        });
    }
}
