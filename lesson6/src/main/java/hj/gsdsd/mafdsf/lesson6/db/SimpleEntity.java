package hj.gsdsd.mafdsf.lesson6.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "simple_entity")
public class SimpleEntity {

    @PrimaryKey(autoGenerate = true)
    int id;

    String text;
}
