package ru.mail.park.android.architecturedemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (needAuth()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment(), "Login")
                    .commit();
        }
    }

    private boolean needAuth() {
        return !new AuthRepo(this).isLoggedIn();
    }
}

