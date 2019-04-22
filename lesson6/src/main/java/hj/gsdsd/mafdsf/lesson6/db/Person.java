package hj.gsdsd.mafdsf.lesson6.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Person {
    @PrimaryKey
    @NonNull
    String name;
    int age;
    String favoriteColor;

}
