package ru.mail.park.android.architecturedemo.repo;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mail.park.android.architecturedemo.database.AppDatabase;
import ru.mail.park.android.architecturedemo.database.Lesson;
import ru.mail.park.android.architecturedemo.database.LessonDao;
import ru.mail.park.android.architecturedemo.network.ApiRepo;
import ru.mail.park.android.architecturedemo.network.LessonApi;

public class LessonRepo {
    private final LessonDao mLessonsDao;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
    private final Context mContext;
    private LessonApi mLessonApi;

    public LessonRepo(Context context) {
        mContext = context;
        mLessonsDao = AppDatabase.from(context).getLessonsDao();
        mLessonApi = ApiRepo.from(mContext).getLessonApi();
    }

    public LiveData<List<Lesson>> getLessons() {
        return mLessonsDao.all();
    }

    public void refresh() {
        mLessonApi.getAll().enqueue(new Callback<List<LessonApi.LessonPlain>>() {
            @Override
            public void onResponse(Call<List<LessonApi.LessonPlain>> call,
                                   Response<List<LessonApi.LessonPlain>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (LessonApi.LessonPlain lessonPlain : response.body()) {
                        try {
                            Lesson lesson = map(lessonPlain);
                            mLessonsDao.insert(lesson);
                            Log.e("LessonRepo", "Loaded " + lesson.getName() + " #" + lesson.getId());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LessonApi.LessonPlain>> call, Throwable t) {
                Log.e("LessonRepo", "Failed to load", t);
            }
        });
    }

    public void like(final Lesson lesson) {
        mLessonApi.like(lesson.getId(), new LessonApi.Like(lesson.getRating() + 1)).enqueue(new Callback<LessonApi.Like>() {
            @Override
            public void onResponse(Call<LessonApi.Like> call,
                                   Response<LessonApi.Like> response) {
                if (response.isSuccessful()) {
                    refresh();
                }
            }

            @Override
            public void onFailure(Call<LessonApi.Like> call, Throwable t) {
                Log.d("Test", "Failed to like " + lesson.getId(), t);
                t.printStackTrace();
            }
        });
    }

    private Lesson map(LessonApi.LessonPlain lessonPlain) throws ParseException {
        return new Lesson(
                lessonPlain.id,
                lessonPlain.name,
                mSimpleDateFormat.parse(lessonPlain.date),
                lessonPlain.place,
                lessonPlain.is_rk,
                lessonPlain.rating
        );
    }
}
