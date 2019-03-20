package hj.gsdsd.mafdsf.lesson4.fragment_part.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import hj.gsdsd.mafdsf.lesson4.R;
import hj.gsdsd.mafdsf.lesson4.fragment_part.fragment.CooperationFragment;


/**
 * Activity, показывающая механизм взаимодействия Activity и Fragment'а.
 */
public class CooperationActivity extends AppCompatActivity implements CooperationFragment.ReportListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperation);

        CooperationFragment fragment = (CooperationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.cooperation);
        fragment.setCooperationText("Hello, cooperation!");
    }

    @Override
    public void reportMessage(String cooperationText) {
        Toast.makeText(CooperationActivity.this,
                "Activity listening. Fragment send: " + cooperationText,
                Toast.LENGTH_SHORT).show();
    }
}
