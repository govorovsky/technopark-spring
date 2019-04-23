package ru.mail.park.android.architecturedemo.database;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {

    @TypeConverter
    public Long convert(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public Date convert(long date) {
        return new Date(date);
    }
}
