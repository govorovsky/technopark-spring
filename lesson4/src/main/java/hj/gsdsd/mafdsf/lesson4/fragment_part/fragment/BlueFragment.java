package hj.gsdsd.mafdsf.lesson4.fragment_part.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hj.gsdsd.mafdsf.lesson4.R;


/**
 * Fragment, демонстрирующий создание UI для фрагмента через LayoutInflater.
 */
public class BlueFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blue, container, false);
    }
}
