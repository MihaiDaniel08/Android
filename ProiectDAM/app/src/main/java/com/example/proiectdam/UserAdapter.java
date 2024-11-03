package com.example.proiectdam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> userList;
    private int layoutId;
    private LayoutInflater inflater;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.userList = objects;
        this.layoutId = resource;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutId,parent,false);
        User user = userList.get(position);
        TextView tvIdUser = view.findViewById(R.id.tvIdUser);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        TextView tvPassword = view.findViewById(R.id.tvPassword);

        tvIdUser.setText(user.getId());
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        tvPassword.setText(user.getPassword());

        tvIdUser.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        return view;
    }
}
