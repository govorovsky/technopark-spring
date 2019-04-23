package ru.mail.park.android.architecturedemo;

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
import ru.mail.park.android.architecturedemo.network.ApiRepo;
import ru.mail.park.android.architecturedemo.network.LessonApi;

public class LessonRepo {
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
    private final Context mContext;
    private static MutableLiveData<List<Lesson>> mLessons = new MutableLiveData<>();

    static {
        mLessons.setValue(Collections.<Lesson>emptyList());
    }

    public LessonRepo(Context context) {
        mContext = context;
    }

    public LiveData<List<Lesson>> getLessons() {
        return mLessons;
    }

    public void refresh() {
        LessonApi api = ApiRepo.from(mContext).getLessonApi();
        api.getAll().enqueue(new Callback<List<LessonApi.LessonPlain>>() {
            @Override
            public void onResponse(Call<List<LessonApi.LessonPlain>> call,
                                   Response<List<LessonApi.LessonPlain>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Lesson> result = new ArrayList<>();
                    for (LessonApi.LessonPlain lessonPlain : response.body()) {
                        try {
                            Lesson lesson = map(lessonPlain);
                            result.add(lesson);
                            Log.e("LessonRepo", "Loaded " + lesson.getName() + " #" + lesson.getId());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    mLessons.postValue(result);
                }
            }

            @Override
            public void onFailure(Call<List<LessonApi.LessonPlain>> call, Throwable t) {
                Log.e("LessonRepo", "Failed to load", t);
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
