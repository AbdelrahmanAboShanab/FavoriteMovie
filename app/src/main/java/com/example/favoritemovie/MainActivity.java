package com.example.favoritemovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.favoritemovie.adapters.MovieAdapter;
import com.example.favoritemovie.pojo.Movie;
import com.example.favoritemovie.viewmodels.MovieViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements OnItemClick{

    MovieViewModel movieViewModel;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    ArrayList<Movie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        adapter = new MovieAdapter(this);
//        recyclerView.setAdapter(adapter);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieViewModel.getMovies();
        Log.i("TAG", "onChanged: i ");
        movieViewModel.getMovieList().observe(this
                , new Observer<ArrayList<Movie>>() {
                    @Override
                    public void onChanged(ArrayList<Movie> movies) {
                        Log.i("TAG", "onChanged: w "+movies.size());
                        list = movies;
                        adapter = new MovieAdapter(movies , getBaseContext(),MainActivity.this);
                        Log.i("TAG", "onChanged:a "+adapter);
                        recyclerView.setAdapter(adapter);
                    }
                });


    }

    @Override
    public void setOnItemClick(int position) {
        Log.i("TAG", "setOnItemClick: "+list.get(position).getTitle());
        Intent intent = new Intent(MainActivity.this , MovieActivity.class);
        intent.putExtra("name",list.get(position).getTitle());
        intent.putExtra("image",list.get(position).getBackdrop_path());
        intent.putExtra("date",list.get(position).getRelease_date());
        intent.putExtra("rate",list.get(position).getVote_average());
        Log.i("TAG", "setOnItemClick:2 "+list.get(position).getVote_average());
        intent.putExtra("lang",list.get(position).getOriginal_language());
        intent.putExtra("overView",list.get(position).getOverview());
        startActivity(intent);

    }
}