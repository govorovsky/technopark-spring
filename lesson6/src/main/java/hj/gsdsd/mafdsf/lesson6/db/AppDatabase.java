package hj.gsdsd.mafdsf.lesson6.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Person.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDao getPersonDao();

    private static AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }


    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "my_db_name").build();
    }
}
