package hj.gsdsd.mafdsf.lesson6.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Менеджер, управляющий базой данных. Занимается чтением/записью данных в выделенном потоке.
 * Все callback'и вызываются в потоке исполнения (не переводятся в UI-thread).
 */
class DbManager {

    private static final int VERSION = 1;

    @SuppressLint("StaticFieldLeak")
    private static final DbManager INSTANCE = new DbManager();

    private static final String TEXT_COLUMN = "TEXT_COLUMN";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String DB_NAME = "MyDatabase.db";

    static DbManager getInstance(Context context) {
        INSTANCE.context = context.getApplicationContext();
        return INSTANCE;
    }

    private final Executor executor = Executors.newSingleThreadExecutor();

    private Context context;

    private SQLiteDatabase database;

    void insert(final String text) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                insertInternal(text);
            }
        });
    }

    void readAll(final ReadAllListener<String> listener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                readAllInternal(listener);
            }
        });
    }

    void clean() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cleanInternal();
            }
        });
    }

    private void checkInitialized() {
        if (database != null) {
            return;
        }

        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                createDatabase(db);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };

        database = helper.getWritableDatabase();
    }

    private void createDatabase(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE '" + TABLE_NAME + "' (ID INTEGER PRIMARY KEY, " + TEXT_COLUMN + " TEXT NOT NULL)");
    }

    private void insertInternal(String text) {
        checkInitialized();

        database.execSQL("INSERT INTO " + TABLE_NAME + " (" + TEXT_COLUMN + ") VALUES (?)", new Object[]{text});
    }

    private void readAllInternal(final ReadAllListener<String> listener) {
        checkInitialized();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor == null) {
            listener.onReadAll(Collections.<String>emptyList());
            return;
        }

        final List<String> result = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                result.add(cursor.getString(cursor.getColumnIndex(TEXT_COLUMN)));
            }
        } finally {
            cursor.close();
        }
        listener.onReadAll(result);
    }

    private void cleanInternal() {
        checkInitialized();

        database.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public interface ReadAllListener<T> {
        void onReadAll(final Collection<T> allItems);
    }
}

