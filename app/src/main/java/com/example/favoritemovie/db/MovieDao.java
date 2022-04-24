package com.example.favoritemovie.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.favoritemovie.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    public void InsertMovies(List<Movie> list);

    @Query("select * from movieTable")
    public LiveData<List<Movie>> GetMovies();
}
