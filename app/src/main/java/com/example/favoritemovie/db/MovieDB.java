package com.example.favoritemovie.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.favoritemovie.pojo.Movie;

@Database(entities = Movie.class, version = 1 ,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class MovieDB extends RoomDatabase {

    public abstract MovieDao GetMovieDao();
}
