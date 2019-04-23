package ru.mail.park.android.architecturedemo;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LessonsViewModel extends AndroidViewModel {

    private LiveData<List<Lesson>> mLessons = new LessonRepo().getLessons();

    public LessonsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Lesson>> getLessons() {
        return mLessons;
    }
}
