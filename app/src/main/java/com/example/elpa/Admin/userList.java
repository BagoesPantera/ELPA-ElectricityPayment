package com.example.elpa.Admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elpa.R;
import com.example.elpa.System.Request;

import java.util.List;

public class userList extends ArrayAdapter<Request> {

    private Activity context;
    private List<Request> userlist;

    public userList(Activity context, List<Request> userlist) {
        super(context, R.layout.item_list, userlist);
        this.context = context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View Listviewitem = inflater.inflate(R.layout.item_list,null,true);

        TextView username = (TextView) Listviewitem.findViewById(R.id.usernamedata);
        TextView email = (TextView) Listviewitem.findViewById(R.id.emaildata);
        TextView meteran = (TextView) Listviewitem.findViewById(R.id.nometerdata);

        Request userdata = userlist.get(position);

        username.setText(userdata.getUsername());
        email.setText(userdata.getEmail());
        meteran.setText(userdata.getMeteran());

        return Listviewitem;
    }
}
