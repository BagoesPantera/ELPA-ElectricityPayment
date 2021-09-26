package com.example.elpa.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elpa.R;
import com.example.elpa.System.Request;
import com.example.elpa.System.RequestAdapterRecyclerView;
import com.example.elpa.User.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userdata extends AppCompatActivity {
    private DatabaseReference database;

    private ArrayList<Request> daftarReq;
    private RequestAdapterRecyclerView requestAdapterRecyclerView;

    private RecyclerView rc_list_request;
    private Button kembali;

//    private FloatingActionButton fab_add; tunggu dulu bos !!!
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
        //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
        Toast.makeText(getApplicationContext(),"Tombol kembali di non aktifkan",Toast.LENGTH_LONG).show();
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);

        final ProgressDialog pd = new ProgressDialog(userdata.this);
        pd.setMessage("Loading...");
        pd.show();

        database = FirebaseDatabase.getInstance().getReference("userData");

        rc_list_request = findViewById(R.id.rc_list_request);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc_list_request.setLayoutManager(mLayoutManager);
        rc_list_request.setItemAnimator(new DefaultItemAnimator());

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarReq = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Request requests = noteDataSnapshot.getValue(Request.class);
                    daftarReq.add(requests);
                }
                requestAdapterRecyclerView = new RequestAdapterRecyclerView(daftarReq, userdata.this);
                rc_list_request.setAdapter(requestAdapterRecyclerView);
                pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        kembali = (Button) findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        fab_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ListActivity.this, MainActivity.class) // FLOAT BUTTON
//                        .putExtra("id", "")
//                        .putExtra("title", "")
//                        .putExtra("email", "")
//                        .putExtra("desk", ""));
//            }
//        });

    }
}
