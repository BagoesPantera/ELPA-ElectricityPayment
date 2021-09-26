package com.example.elpa.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.elpa.Admin.userdata;
import com.example.elpa.R;
import com.example.elpa.System.PopActivity;
import com.example.elpa.System.Request;
import com.example.elpa.System.RequestAdapterRecyclerView;
import com.example.elpa.System.RequestProfileAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    private DatabaseReference database;

    private ArrayList<Request> daftarReq;
    private RequestProfileAdapter requestProfileAdapter;

    private RecyclerView rc_list_request;
    private Button kembali,deleteacc,logout,changepass;

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
        setContentView(R.layout.activity_profile);
        final ProgressDialog pd = new ProgressDialog(profile.this);
        pd.setMessage("Loading...");
        pd.show();
            database = FirebaseDatabase.getInstance().getReference("userDatas");

            rc_list_request = findViewById(R.id.rc_profile);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rc_list_request.setLayoutManager(mLayoutManager);
            rc_list_request.setItemAnimator(new DefaultItemAnimator());

            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            database.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    daftarReq = new ArrayList<>();
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        Request requests = noteDataSnapshot.getValue(Request.class);
                        daftarReq.add(requests);
                    }
                    requestProfileAdapter = new RequestProfileAdapter(daftarReq, profile.this);
                    rc_list_request.setAdapter(requestProfileAdapter);
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


            deleteacc = (Button) findViewById(R.id.deleteacc);
            deleteacc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(profile.this, PopActivity.class);
                    startActivity(i);
                }
            });
            logout = (Button) findViewById(R.id.logoutprofile);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(profile.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }
            });
            changepass = (Button) findViewById(R.id.changepass);
            changepass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(profile.this,lks1.class);
                    startActivity(i);
                }
            });
    }
}
