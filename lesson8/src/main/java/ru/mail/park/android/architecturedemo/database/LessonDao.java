package ru.mail.park.android.architecturedemo.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Lesson... lesson);

    @Query("select * from lesson order by mDate asc")
    LiveData<List<Lesson>> all();

    @Query("select * from lesson order by mDate asc")
    List<Lesson> allNow();
}
