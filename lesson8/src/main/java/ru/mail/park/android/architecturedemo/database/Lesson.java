package ru.mail.park.android.architecturedemo.database;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
@TypeConverters(value = {DateConverter.class})
public class Lesson {

    @PrimaryKey
    private int mId;
    private String mName;
    private Date mDate;
    private String mPlace;
    private boolean mIsRk;
    private int mRating;

    public Lesson(int id, String name, Date date, String place, boolean isRk, int rating) {
        mId = id;
        mName = name;
        mDate = date;
        mPlace = place;
        mIsRk = isRk;
        mRating = rating;
    }

    public Lesson() {}

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Date getDate() {
        return mDate;
    }

    public String getPlace() {
        return mPlace;
    }

    public boolean isRk() {
        return mIsRk;
    }

    public int getRating() {
        return mRating;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setPlace(String place) {
        mPlace = place;
    }

    public void setIsRk(boolean rk) {
        mIsRk = rk;
    }

    public void setRating(int rating) {
        mRating = rating;
    }
}

