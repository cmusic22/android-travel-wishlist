package com.carson.travelwishlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class WishListViewModel extends AndroidViewModel {
    private WishListRepository repository;

    private LiveData<List<WishListRecord>> allRecords;

    public WishListViewModel(@NonNull Application application){
        super(application);
        repository = new WishListRepository(application);
        allRecords = repository.getAllRecords();
    }

    public LiveData<List<WishListRecord>> getAllRecords(){
        return allRecords;
    }

    public LiveData<WishListRecord> getRecordForDate(String wishListRecords){
        return repository.getRecordForDay(wishListRecords);
    }

    public void insert(WishListRecord record){
        repository.insert(record);
    }

    public void update(WishListRecord record){
        repository.update(record);
    }
}
