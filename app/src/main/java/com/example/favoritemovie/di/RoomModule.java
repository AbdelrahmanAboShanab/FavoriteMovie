package com.example.favoritemovie.di;

import android.app.Application;
import androidx.room.Room;
import com.example.favoritemovie.db.MovieDB;
import com.example.favoritemovie.db.MovieDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RoomModule {

    @Provides
    @Singleton
    public static MovieDB ProvideDB(Application application){
        return Room.databaseBuilder(application , MovieDB.class,"MovieDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static MovieDao ProvideMovieDao(MovieDB movieDB){
        return movieDB.GetMovieDao();
    }
}
