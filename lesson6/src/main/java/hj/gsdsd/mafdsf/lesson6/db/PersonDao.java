package hj.gsdsd.mafdsf.lesson6.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    // Добавление Person в бд
    @Insert
    void insertAll(Person... people);

    // Удаление Person из бд
    @Delete
    void delete(Person person);

    // Получение всех Person из бд
    @Query("SELECT * FROM person")
    List<Person> getAllPeople();

    // Получение всех Person из бд с условием
    @Query("SELECT * FROM person WHERE favoriteColor LIKE :color")
    List<Person> getAllPeopleWithFavoriteColor(String color);

}