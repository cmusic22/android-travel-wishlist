package com.carson.travelwishlist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//this creates the database on the device

@Database(entities = {WishListRecord.class}, version = 1, exportSchema = false)
public abstract class WishListDatabase extends RoomDatabase {

    private static volatile WishListDatabase INSTANCE;

    public abstract WishListDAO wishlistDAO(); //this is the abstract method

    static WishListDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (WishListDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WishListDatabase.class, "Wish List").build();
                }
            }
        }

        return INSTANCE;
    }

}
