package ru.mail.park.android.architecturedemo;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.mail.park.android.architecturedemo.database.Lesson;
import ru.mail.park.android.architecturedemo.repo.LessonRepo;

public class LessonsViewModel extends AndroidViewModel {

    private final LessonRepo mRepo = new LessonRepo(getApplication());
    private LiveData<List<Lesson>> mLessons = mRepo.getLessons();

    public LessonsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Lesson>> getLessons() {
        return mLessons;
    }

    public void like(Lesson lesson) {
        mRepo.like(lesson);
    }
}
