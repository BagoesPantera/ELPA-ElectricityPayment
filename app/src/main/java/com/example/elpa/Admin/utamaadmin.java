package com.example.elpa.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.elpa.R;
import com.example.elpa.User.MainActivity;
import com.example.elpa.User.bulanbayar;
import com.example.elpa.User.menuutama;
import com.example.elpa.User.pemilihanbank;
import com.google.firebase.auth.FirebaseAuth;


public class utamaadmin extends AppCompatActivity {
private Button userdata,halamanutama,logout,pbpadmin;
    private long backpressed;
    private Toast backtoast;

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
        setContentView(R.layout.activity_utamaadmin);

        userdata = (Button) findViewById(R.id.userdatabutton);
        userdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(utamaadmin.this, userdata.class);
                startActivity(i);
            }
        });

        halamanutama = (Button) findViewById(R.id.halamanutamabutton);
        halamanutama.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(utamaadmin.this, menuutama.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(utamaadmin.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        pbpadmin = (Button) findViewById(R.id.pbpadmin);
        pbpadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(utamaadmin.this,pbpdata.class);
                startActivity(i);
            }
        });
}}
