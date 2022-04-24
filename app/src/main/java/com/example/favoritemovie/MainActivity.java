package com.example.favoritemovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.favoritemovie.adapters.MovieAdapter;
import com.example.favoritemovie.pojo.Movie;
import com.example.favoritemovie.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements OnItemClick{

    MovieViewModel movieViewModel;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    ArrayList<Movie> list;
    List<Movie> cashedList;


    Button button;

   // NetworkConnectionReceiver networkConnectionReceiver;
    IntentFilter intentFilter;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InflatingViews();
        IntializingViewModel();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetData();
            }
        });
        Log.i("TAG", "onChanged: i ");
        swipeRefreshLayout.setRefreshing(false);
        GetData();


    }

    private void GetData() {
        if(isNetworkAvailable() == false){
            Toast.makeText(this, "No internet connection !!!", Toast.LENGTH_LONG).show();
            GetCached();
        }else {
            GetMovies();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean v = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.i("TAG", "aaaaaaisNetworkAvailable: "+v);
        return v;
    }

    private void InflatingViews() {
        swipeRefreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void IntializingViewModel() {
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovies();

    }

    void GetCached(){
        movieViewModel.getCachedMovies().observe(MainActivity.this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                list = (ArrayList<Movie>) movies;
                adapter = new MovieAdapter((ArrayList<Movie>) movies , getBaseContext(),MainActivity.this);
                Log.i("TAG", "onChanged:az "+movies.size());
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void GetMovies() {
        Log.i("TAG", "aaaaaaonChanged: ");
        movieViewModel.getMovieList().observe(this
                , new Observer<ArrayList<Movie>>() {
                    @Override
                    public void onChanged(ArrayList<Movie> movies) {
                        Log.i("TAG", "aaaaaaonChanged: "+movies.size());
                        adapter = new MovieAdapter(movies , getBaseContext(),MainActivity.this);
                        recyclerView.setAdapter(adapter);
                        list = movies;
                        cashedList = movies.subList(0,5);
                        Log.i("TAG", "onChanged:`11 "+cashedList.size());
                        movieViewModel.insertCachedMovies(cashedList);
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