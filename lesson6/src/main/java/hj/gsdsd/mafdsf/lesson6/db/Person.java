package hj.gsdsd.mafdsf.lesson6.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Person {
    @PrimaryKey
    @NonNull
    String name;
    int age;
    String favoriteColor;

}
