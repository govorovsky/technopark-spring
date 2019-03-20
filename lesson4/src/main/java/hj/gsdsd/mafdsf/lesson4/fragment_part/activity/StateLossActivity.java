package hj.gsdsd.mafdsf.lesson4.fragment_part.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import hj.gsdsd.mafdsf.lesson4.R;
import hj.gsdsd.mafdsf.lesson4.fragment_part.fragment.RedFragment;


/**
 * Activity для демонстрации ситуации потери состояния. Для работы примера нужно:
 * <ol>
 *     <li>Нажать кнопку "DO IT!";</li>
 *     <li>Уйти с Activity быстрее, чем за 3 секунды.</li>
 * </ol>
 * Потом повторить указанные действия, но с кнопкой "DO IT AND LOSS".
 */
public class StateLossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_loss);

        findViewById(R.id.btn_do_it).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIt(false);
            }
        });

        findViewById(R.id.btn_do_it_allowing_loss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIt(true);
            }
        });
    }

    private void doIt(final boolean allowLoss) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("STATE_LOSS", "Performing fragment transaction");
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new RedFragment());
                if (allowLoss) {
                    transaction.commitAllowingStateLoss();
                } else {
                    transaction.commit();
                }
            }
        }, 3000);
    }
}
