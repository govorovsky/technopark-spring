package hj.gsdsd.mafdsf.lesson4.fragment_part.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hj.gsdsd.mafdsf.lesson4.R;

/**
 * Fragment, показывающий механизм взаимодействия Activity и Fragment'а.
 */
public class CooperationFragment extends BaseFragment {

    private ReportListener reportListener;
    private String cooperationText;

    public void setCooperationText(String cooperationText) {
        this.cooperationText = cooperationText;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        reportListener = (ReportListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cooperation, container, false);
        view.findViewById(R.id.btn_do_it).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportListener.reportMessage(cooperationText);
            }
        });
        return view;
    }

    public interface ReportListener {
        void reportMessage(String cooperationText);
    }
}
