package com.example.favoritemovie.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.favoritemovie.pojo.Movie;

import java.util.ArrayList;

@Dao
public interface MovieDao {

    @Insert
    public void InsertMovies(ArrayList<Movie> list);

    @Query("select * from movieTable")
    public LiveData<ArrayList<Movie>> GetMovies();
}
