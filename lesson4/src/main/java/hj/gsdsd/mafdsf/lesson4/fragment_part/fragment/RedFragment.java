package hj.gsdsd.mafdsf.lesson4.fragment_part.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import hj.gsdsd.mafdsf.lesson4.R;


/**
 * Fragment, демонстрирующий создание UI для фрагмента через LayoutInflater.
 */
public class RedFragment extends BaseFragment {

    private final String RANDOM_NUM_EXTRA = "TEST";
    private TextView mTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String text = savedInstanceState.getString(RANDOM_NUM_EXTRA, "null");
            Log.d(getLogTag(), "onCreate. Supplied text: " + text);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String text;
        if (savedInstanceState != null) {
            text = savedInstanceState.getString("TEST", "null");
            Log.d(getLogTag(), "onCreateView. Supplied text: " + text);
        } else {
            text = String.valueOf(new Random().nextInt());
        }
        View view = inflater.inflate(R.layout.fragment_red, container, false);

        mTextView = view.findViewById(R.id.my_red_text);
        mTextView.setText(text);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RANDOM_NUM_EXTRA, (String) mTextView.getText());
        Log.d(getLogTag(), outState.getString(RANDOM_NUM_EXTRA));
    }
}
