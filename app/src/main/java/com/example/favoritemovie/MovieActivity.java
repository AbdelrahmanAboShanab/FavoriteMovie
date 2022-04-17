package com.example.favoritemovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ImageView imageView;
    TextView name, overview , lang, date , rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        
        CheckInternetConnection();
        ratingBar = findViewById(R.id.rating);
        ratingBar.setRating(3.2f);

        imageView = findViewById(R.id.imagee);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+getIntent().getStringExtra("image"))
            .fit().into(imageView);

        name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));

        overview = findViewById(R.id.overview);
        overview.setText(getIntent().getStringExtra("overView"));
        Log.i("TAG", "onCreate:dw "+getIntent().getStringExtra("overview"));
        lang = findViewById(R.id.language);
        lang.setText(getIntent().getStringExtra("lang"));

        date = findViewById(R.id.date);
        date.setText(getIntent().getStringExtra("date"));

        rate = findViewById(R.id.rate);
        rate.setText(Double.toString(getIntent().getDoubleExtra("rate",10)));
        Log.i("TAG", "onCreate:dw "+getIntent().getStringExtra("rate"));
    }

    private void CheckInternetConnection() {
    }
}