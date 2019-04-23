package ru.mail.park.android.architecturedemo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private LoginViewModel mLoginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        final EditText login = view.findViewById(R.id.login);
        final EditText password = view.findViewById(R.id.password);
        final Button loginBtn = view.findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginViewModel.login(login.getText().toString(), password.getText().toString());
            }
        });

        mLoginViewModel.getProgress().observe(this, new Observer<LoginViewModel.LoginState>() {
            @Override
            public void onChanged(LoginViewModel.LoginState loginState) {
                if (loginState == LoginViewModel.LoginState.FAILED) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                } else if (loginState == LoginViewModel.LoginState.ERROR) {
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    loginBtn.setEnabled(true);
                } else if (loginState == LoginViewModel.LoginState.IN_PROGRESS) {
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    loginBtn.setEnabled(false);
                } else if (loginState == LoginViewModel.LoginState.SUCCESS) {
                    Router router = (Router) getActivity();
                    if (router != null) {
                        router.openLessons();
                    }
                } else {
                    loginBtn.setBackground(getContext().getDrawable(android.R.drawable.btn_default));
                    loginBtn.setEnabled(true);
                }
            }
        });
    }
}
