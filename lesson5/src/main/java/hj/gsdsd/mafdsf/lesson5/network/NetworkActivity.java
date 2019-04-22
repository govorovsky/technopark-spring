package hj.gsdsd.mafdsf.lesson5.network;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import hj.gsdsd.mafdsf.lesson5.R;


/**
 * Пример, демонстрирующий выполнение сетевых запросов. В данном коде есть несколько допущений,
 * упрощающих понимание кода, но которые недопустимо использовать в реальных и семетровых проектах.
 * А именно:
 * <ul>
 *     <li>Нет отписки listener'а от получения результата - утечка памяти</li>
 *     <li>listener не подразумевает обработку ошибок</li>
 * </ul>
 */
public class NetworkActivity extends AppCompatActivity {

    private final NetworkManager.OnRequestCompleteListener listener =
            new NetworkManager.OnRequestCompleteListener() {
                @Override
                public void onRequestComplete(final String body) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NetworkActivity.this.body.setText(body);
                            progress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            };

    private TextView body;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        body = findViewById(R.id.body);
        progress = findViewById(R.id.progress);

        findViewById(R.id.user_agent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                body.setText("");
                progress.setVisibility(View.VISIBLE);
                NetworkManager.getInstance().get("https://httpbin.org/user-agent", listener);
            }
        });
    }
}
