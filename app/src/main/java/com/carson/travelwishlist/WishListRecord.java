package com.carson.travelwishlist;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class WishListRecord {
    @NonNull
    @PrimaryKey


    private String place;
    private Date mDate;
    private String reason;

    public WishListRecord(@NonNull String place, Date mDate, String reason){
        this.place = place;
        this.mDate = mDate;
        this.reason = reason;
    }

    @NonNull
    public String getPlace() {
        return place;
    }

    public void setPlace(@NonNull String place) {
        this.place = place;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    @Override
    public String toString() {
        return "WishListRecord{" +
                "place='" + place + '\'' +
                ", mDate=" + mDate +
                ", reason='" + reason + '\'' +
                '}';
    }
}
