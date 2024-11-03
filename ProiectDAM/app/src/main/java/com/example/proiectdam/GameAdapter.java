package com.example.proiectdam;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {

    private Context context;
    private List<Game> gameList;
    private int layoutId;
    private LayoutInflater inflater;

    public GameAdapter(@NonNull Context context, int resource, @NonNull List<Game> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.gameList = objects;
        this.layoutId = resource;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = inflater.inflate(layoutId,parent,false);
       Game game = gameList.get(position);
       TextView tvId = view.findViewById(R.id.tvId);
       TextView tvTitle = view.findViewById(R.id.tvTitle);
       TextView tvDescription = view.findViewById(R.id.tvDescription);
       TextView tvGenre = view.findViewById(R.id.tvGenre);

        tvId.setText(game.getId());
        tvTitle.setText(game.getTitle());
        tvDescription.setText(game.getDescription());
        tvGenre.setText(game.getGenre());

        if(game.getGenre().equalsIgnoreCase("action")){
            tvGenre.setTextColor(Color.RED);
        } else if (game.getGenre().equalsIgnoreCase("moba")) {
            tvGenre.setTextColor(Color.BLUE);
        } else if (game.getGenre().equalsIgnoreCase("farming-sim")) {
            tvGenre.setTextColor(Color.GREEN);
        } else if (game.getGenre().equalsIgnoreCase("souls-like")) {
            tvGenre.setTextColor(Color.MAGENTA);
        }else tvGenre.setTextColor(Color.GRAY);
        return view;
    }
}
