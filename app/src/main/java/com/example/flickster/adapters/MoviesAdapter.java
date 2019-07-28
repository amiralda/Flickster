package com.example.flickster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flickster.DetailActivity;
import com.example.flickster.R;
import com.example.flickster.models.Movie;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("smile", "OnCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("smile", "OnBindViewHolder" + position);
      Movie movie = movies .get(position);
      holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
      TextView tvTitle;
      TextView tvOverview;
      ImageView ivPoster;
      RelativeLayout container;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tvTitle = itemView.findViewById(R.id.tvTitle);
           tvOverview = itemView.findViewById(R.id.tvOverview);
           ivPoster = itemView.findViewById(R.id.ivPoster);
           container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl = movie.getPosterPath();
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            }
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
            tvTitle.setText(movie.getTitle());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                   context.startActivity(i);
                }
            });


                    ;
            };
        }
    }
