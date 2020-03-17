package com.carson.travelwishlist;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface WishListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE); //Ignoring new record for existing day
    void insert(WishListRecord... wr);

    @Update
    void update(WishListRecord... wr);

    @Query("SELECT * WishListRecord WHERE date = :date")
    LiveData<WishListRecord> getRecordForDate(Date date);

    @Query("SELECT * FROM WishListRecord")
    LiveData<List<WishListRecord>> getAllRecords();
}
