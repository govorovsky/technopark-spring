package ru.mail.park.android.architecturedemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.mail.park.android.architecturedemo.ApplicationModified;

@Database(entities = {Lesson.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LessonDao getLessonsDao();

    public static AppDatabase from(Context context) {
        return ApplicationModified.from(context).getDatabase();
    }
}

