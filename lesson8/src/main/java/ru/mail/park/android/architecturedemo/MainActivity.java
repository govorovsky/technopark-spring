package ru.mail.park.android.architecturedemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ru.mail.park.android.architecturedemo.repo.AuthRepo;
import ru.mail.park.android.architecturedemo.repo.LessonRepo;

public class MainActivity extends AppCompatActivity implements Router {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            if (needAuth()) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment(), "Login")
                        .commit();
            } else {
                openLessons();
            }
        }
    }

    private boolean needAuth() {
        return !new AuthRepo(this).isLoggedIn();
    }

    @Override
    public void openLessons() {
        new LessonRepo(this).refresh();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LessonsFragment(), "Lessons")
                .commit();
    }
}

