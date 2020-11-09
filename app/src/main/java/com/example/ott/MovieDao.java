package com.example.ott;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("Select * from Movies_DB")
    List<MovieList> getAllList();

    @Insert
    void insert(MovieList list);

    @Update
    void update(MovieList list);

    @Delete
    void delete(MovieList list);
}
