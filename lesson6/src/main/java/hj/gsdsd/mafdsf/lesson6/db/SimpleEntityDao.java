package hj.gsdsd.mafdsf.lesson6.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface SimpleEntityDao {


    @Insert
    void insertAll(SimpleEntity... entities);


    // Получение всех Person из бд
    @Query("SELECT * FROM simple_entity")
    List<SimpleEntity> getAllEntities();
}
