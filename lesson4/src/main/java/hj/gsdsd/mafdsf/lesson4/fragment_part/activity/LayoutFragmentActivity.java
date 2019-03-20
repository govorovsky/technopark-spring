package hj.gsdsd.mafdsf.lesson4.fragment_part.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hj.gsdsd.mafdsf.lesson4.R;


/**
 * Activity, которая демонстрирует как Fragment'ы могут быть добавлены через layout.
 */
public class LayoutFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fragment);
    }
}
