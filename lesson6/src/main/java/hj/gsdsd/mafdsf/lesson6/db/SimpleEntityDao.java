package hj.gsdsd.mafdsf.lesson6.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface SimpleEntityDao {


    @Insert
    void insertAll(SimpleEntity... entities);


    // Получение всех Person из бд
    @Query("SELECT * FROM simple_entity")
    List<SimpleEntity> getAllEntities();
}
