package com.example.favoritemovie.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.favoritemovie.db.MovieDao;
import com.example.favoritemovie.network.MovieApiService;
import com.example.favoritemovie.pojo.Movie;
import com.example.favoritemovie.pojo.MovieResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {

    private MovieApiService movieApiService;
    MovieDao movieDao;


    @Inject
    public Repository(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    public Observable<MovieResponse> getMovieResponse(){
        return movieApiService.getResponse();
    }

    public void insertMovies(ArrayList<Movie> list){
        movieDao.InsertMovies(list);
    }

    public LiveData<ArrayList<Movie>> getMovies(){
        return movieDao.GetMovies();
    }
}
