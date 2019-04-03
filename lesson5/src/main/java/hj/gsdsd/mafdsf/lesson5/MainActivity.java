package hj.gsdsd.mafdsf.lesson5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hj.gsdsd.mafdsf.lesson5.network.NetworkActivity;


public class MainActivity extends AppCompatActivity {

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            final Intent intent;
            switch (view.getId()) {
                case R.id.looper_and_handler:
                    intent = new Intent(MainActivity.this, HandlerActivity.class);
                    break;
                case R.id.thread_pool:
                    intent = new Intent(MainActivity.this, ThreadPoolActivity.class);
                    break;
                case R.id.intent_service:
                    intent = new Intent(MainActivity.this, IntentServiceActivity.class);
                    break;
                case R.id.network:
                    intent = new Intent(MainActivity.this, NetworkActivity.class);
                    break;
                default:
                    throw new UnsupportedOperationException("Wrong view id " + view.getId());
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.looper_and_handler).setOnClickListener(clickListener);
        findViewById(R.id.thread_pool).setOnClickListener(clickListener);
        findViewById(R.id.intent_service).setOnClickListener(clickListener);
        findViewById(R.id.network).setOnClickListener(clickListener);
    }
}
