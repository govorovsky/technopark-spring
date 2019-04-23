package ru.mail.park.android.architecturedemo;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import ru.mail.park.android.architecturedemo.database.AppDatabase;
import ru.mail.park.android.architecturedemo.network.ApiRepo;

public class ApplicationModified extends Application {

    private AppDatabase mDb;
    private ApiRepo mApiRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiRepo = new ApiRepo();
        mDb = Room.databaseBuilder(this, AppDatabase.class, "main_db")
                .allowMainThreadQueries() // TODO() don't forget to remove this line in release! Only for debug purpose!
                .fallbackToDestructiveMigration()
                .build();
    }

    public AppDatabase getDatabase() {
        return mDb;
    }

    public ApiRepo getApis() {
        return mApiRepo;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}

