package com.example.favoritemovie.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favoritemovie.adapters.MovieAdapter;
import com.example.favoritemovie.pojo.Movie;
import com.example.favoritemovie.pojo.MovieResponse;
import com.example.favoritemovie.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {


    Repository repository;
    MutableLiveData<ArrayList<Movie>> mutableLiveData = new MutableLiveData<>();
    LiveData<List<Movie>> cashedMovies;

    @ViewModelInject
    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Movie>> getMovieList(){
        return mutableLiveData;
    }

    public void getMovies(){
        Log.i("Tagg", "appl: ");
        repository.getMovieResponse()
                .subscribeOn(Schedulers.io())
                .map(new Function<MovieResponse, ArrayList<Movie>>() {
                    @Override
                    public ArrayList<Movie> apply(MovieResponse movieResponse) throws Throwable {
                        Log.i("Tagg", "appla: ");
                        ArrayList<Movie> moviesList = (ArrayList)movieResponse.getResults();
                        Log.i("Tagg", "apply: "+moviesList.size());
                        return moviesList;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> mutableLiveData.setValue(result)
                        , error -> Log.i("TAG", "getMovies: "+error.getMessage()));
    }

    public void insertCachedMovies(List<Movie> list){
        repository.insertMovies(list);
    }
    public LiveData<List<Movie>> getCachedMovies(){
        return repository.getMovies();
    }

}
