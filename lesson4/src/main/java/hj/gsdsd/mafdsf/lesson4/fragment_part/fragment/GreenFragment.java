package hj.gsdsd.mafdsf.lesson4.fragment_part.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import hj.gsdsd.mafdsf.lesson4.R;


/**
 * Fragment, демонстрирующий создание UI для фрагмента путем ручного построения иерархии View.
 */
public class GreenFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createView();
    }

    private View createView() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setBackgroundColor(Color.GREEN);

        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        );
        textView.setText(R.string.green_fragment);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        layout.addView(textView);

        return layout;
    }
}
