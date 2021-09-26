package com.example.elpa.System;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.elpa.R;
import com.example.elpa.User.MainActivity;
import com.example.elpa.User.menuutama;
import com.example.elpa.Admin.userdata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
   String cek = "tes";
   int bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        cek = getIntent().getExtras().getString("keconfirm");
//        bg = getIntent().getIntExtra("MyImageResource", R.drawable.putih);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String admin = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        if (firebaseUser != null){
            if (admin.equals("admin@admin.com")){
                Intent intent = new Intent(Home.this, userdata.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else{
                Intent intent = new Intent(Home.this, menuutama.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }else{
            Intent intent = new Intent(Home.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}