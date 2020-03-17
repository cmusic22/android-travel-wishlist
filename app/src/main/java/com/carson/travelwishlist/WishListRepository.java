package com.carson.travelwishlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WishListRepository {

    private WishListDAO wishListDAO;

    public WishListRepository(Application application){
        WishListDatabase db =WishListDatabase.getDatabase(application);
        wishListDAO = db.wishlistDAO();
    }

    public void insert(WishListRecord record){
        //insert record asynchronous
        new InsertWishListAsync(wishListDAO).execute(record);
    }

    static class InsertWishListAsync extends AsyncTask<WishListRecord, Void, Void>{
        private WishListDAO wishListDAO;

        InsertWishListAsync(WishListDAO wishListDAO){
            this.wishListDAO = wishListDAO;
        }

        @Override
        protected Void doInBackground(WishListRecord... wishListRecords){
            wishListDAO.insert(wishListRecords);
            return null;
        }
    }

    public void update(WishListRecord record){
//inserts record asynchronously
        new UpdateWishListAsync(wishListDAO).execute(record);
    }

    static class UpdateWishListAsync extends AsyncTask<WishListRecord, Void, Void>{
        private WishListDAO wishListDAO;

        UpdateWishListAsync(WishListDAO wishListDAO){
            this.wishListDAO = wishListDAO;
        }

        @Override
        protected Void doInBackground(WishListRecord... wishListRecords){
            wishListDAO.update(wishListRecords);
            return null;
        }
    }

    public LiveData<List<WishListRecord>> getAllRecords(){
        return wishListDAO.getAllRecords();
    }

    public LiveData<WishListRecord> getRecordForDay(String wishListRecords){
        return wishListDAO.update(wishListRecords);
    }
}
