package com.example.elpa.System;

import android.app.Application;
import android.content.Intent;

import com.example.elpa.Admin.utamaadmin;
import com.example.elpa.User.MainActivity;
import com.example.elpa.User.menuutama;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homes extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser != null){
            String admin = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            if (admin.equals("admin@admin.com")){
                Intent intent = new Intent(Homes.this, utamaadmin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else{
                Intent intent = new Intent(Homes.this, menuutama.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
//            Intent intent = new Intent(Homes.this, menuutama.class);
//            startActivity(intent);
        }else{
            Intent intent = new Intent(Homes.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}