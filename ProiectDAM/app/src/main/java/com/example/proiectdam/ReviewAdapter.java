package com.example.proiectdam;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {
    private Context context;
    private List<Review> reviewList;
    private int layoutId;
    private LayoutInflater inflater;

    public ReviewAdapter(@NonNull Context context, int resource, @NonNull List<Review> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.reviewList = objects;
        this.layoutId = resource;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutId,parent,false);
        Review review = reviewList.get(position);
        TextView tvUserId = view.findViewById(R.id.tvUserId);
        TextView tvGameId = view.findViewById(R.id.tvGameId);
        TextView tvContent = view.findViewById(R.id.tvContent);
        RatingBar ratingBar = view.findViewById(R.id.ratingBar);

        tvUserId.setText(review.getUserId());
        tvGameId.setText(review.getGameId());
        tvContent.setText(review.getContent());
        ratingBar.setRating(review.getRating());

        if(review.getRating()>=0 && review.getRating()<=1){
            ratingBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#8B0000")));
        } else if (review.getRating()>1 && review.getRating()<=2) {
            ratingBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("##008000FF0000")));
        } else if (review.getRating()>2 && review.getRating()<=3) {
            ratingBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFA500")));
        } else if (review.getRating()>3 && review.getRating()<=4) {
            ratingBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFFF00")));
        }else {
            ratingBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#008000")));
        }

        return view;
    }
}
