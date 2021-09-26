package com.example.elpa.System;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elpa.R;

import java.util.List;

public class RequestProfileAdapter extends RecyclerView.Adapter<RequestProfileAdapter.MyViewHolder> {
    private List<Request> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView username,email,meterandata;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rlprofile);
            username = view .findViewById(R.id.usernameprofile);
            email = view.findViewById(R.id.emailprofile);
            meterandata = view.findViewById(R.id.meteranprofile);
        }
    }

    public RequestProfileAdapter(List<Request> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public RequestProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_list, parent, false);

        return new RequestProfileAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RequestProfileAdapter.MyViewHolder holder, final int position) {
        final Request movie = moviesList.get(position);

        holder.username.setText(movie.getUsername());
        holder.email.setText(": " + movie.getEmail());
        holder.meterandata.setText(": " + movie.getMeteran());

//        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent goDetail = new Intent(mActivity, userdata.class);
//                goDetail.putExtra("key", movie.getKey());
//                goDetail.putExtra("username", movie.getUsername());
//                goDetail.putExtra("email", movie.getEmail());
//                goDetail.putExtra("meteran", movie.getMeteran());
//
//                mActivity.startActivity(goDetail);
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
