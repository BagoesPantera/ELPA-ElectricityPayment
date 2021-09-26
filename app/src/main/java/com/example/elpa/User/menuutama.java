package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elpa.Admin.utamaadmin;
import com.example.elpa.R;
import com.example.elpa.User.bulanbayar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class menuutama extends AppCompatActivity {
private TextView loguot,profile,backtoadmin;
private Button bayar,profiles;
    private FirebaseAuth mAuth;
    private long backpressed;
    private Toast backtoast,non;

    @Override
    public void onBackPressed() {


        if (backpressed + 2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }else{
           backtoast =  Toast.makeText(getApplicationContext(),"Tekan sekali lagi untuk keluar",Toast.LENGTH_LONG);
           backtoast.show();
        }
        backpressed = System.currentTimeMillis();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);



        loguot = (TextView) findViewById(R.id.loguot);
        loguot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (non != null){
                    non.cancel();
                }
                onBackPressed();
            }
        });

        bayar = (Button) findViewById(R.id.bayar);
        bayar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openbayar();
            }
        });

        profiles = (Button) findViewById(R.id.profiles);
        profiles.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openprofile();
            }
        });

        profile = (TextView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openprofile();
            }
        });

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String adminemail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        backtoadmin = (TextView) findViewById(R.id.backtoadmin);
        if (firebaseUser != null){
            if (adminemail.equals("admin@admin.com")){
                backtoadmin.setVisibility(View.VISIBLE);
            }else{backtoadmin.setVisibility(View.GONE);}
        }else{
            backtoadmin.setVisibility(View.GONE);
        }
        backtoadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menuutama.this, utamaadmin.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
           non =  Toast.makeText(getApplicationContext(),"Tombol kembali di non aktifkan",Toast.LENGTH_SHORT);
           non.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void openbayar(){
        Intent intent = new Intent(this, bulanbayar.class);
        startActivity(intent);
    }
    public void openprofile(){
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
}
