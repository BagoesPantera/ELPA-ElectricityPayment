package com.example.elpa.System;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.elpa.Admin.pbpdata;
import com.example.elpa.Admin.sendemail;
import com.example.elpa.Admin.userdata;
import com.example.elpa.R;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapterPBP extends RecyclerView.Adapter<RequestAdapterPBP.MyViewHolder> {
    private List<pbpinput> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView email,input;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rllayoutpbp);
            email = view.findViewById(R.id.emailpbp);
            input = view.findViewById(R.id.inputpbp);
        }
    }

    public RequestAdapterPBP(ArrayList<pbpinput> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public RequestAdapterPBP.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pbp_list, parent, false);

        return new RequestAdapterPBP.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RequestAdapterPBP.MyViewHolder holder, final int position) {
        final pbpinput movie = moviesList.get(position);

        holder.email.setText(": " + movie.getEMail());
        holder.input.setText(": " + movie.getInput());

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goDetail = new Intent(mActivity, sendemail.class);
                goDetail.putExtra("emailss", movie.getEMail());
                goDetail.putExtra("input", movie.getInput());
                mActivity.startActivity(goDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

