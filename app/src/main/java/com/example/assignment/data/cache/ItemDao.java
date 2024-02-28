package com.example.assignment.data.cache;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.assignment.data.model.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Item> myDataList);

    @Query("SELECT * FROM items")
    List<Item> getAllItems();

    @Query("DELETE FROM items")
    void  deleteAllItems();


}