package com.example.favoritemovie.network;

import com.example.favoritemovie.pojo.MovieResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("/3/movie/popular?api_key=442bfb98d6070572786202373c892719")
    public Observable<MovieResponse> getResponse();
}
