package com.example.favoritemovie.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.favoritemovie.OnItemClick;
import com.example.favoritemovie.R;
import com.example.favoritemovie.pojo.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {

    ArrayList<Movie> list;
    Context context;
    OnItemClick onItemClick;

    public MovieAdapter(ArrayList<Movie> list, Context context, OnItemClick onItemClick) {
        this.list = list;
        this.context = context;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        MoviewViewHolder viewHolder = new MoviewViewHolder(v);
        Log.i("TAG", "onCreateViewHolder:1 ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {
        holder.name.setText(list.get(position).getTitle());
        holder.lang.setText("Language : "+list.get(position).getOriginal_language());
        holder.rate.setText("Rate : "+list.get(position).getVote_average());
        holder.date.setText(list.get(position).getRelease_date());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+list.get(position).getPoster_path())
                .resize(300,400).centerCrop().into(holder.img);
//        Glide.with(context).load(list.get(position).getPoster_path())
//                .apply(new RequestOptions().override(400, 400))
//                .into(holder.img);
        Log.i("TAG", "onBindViewHolder:1 "+list.get(position).getPoster_path());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MoviewViewHolder extends RecyclerView.ViewHolder{

        TextView name, lang , rate ,date;
        ImageView img;
        public MoviewViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.movieName);
            lang = itemView.findViewById(R.id.movieLanguage);
            rate = itemView.findViewById(R.id.movieRate);
            date = itemView.findViewById(R.id.movieDate);
            img = itemView.findViewById(R.id.movieImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.setOnItemClick(getAdapterPosition());
                }
            });
        }
    }
}
