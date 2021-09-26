package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.elpa.R;

public class lks2 extends AppCompatActivity {
    private Button konfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lks2);
        konfirm = (Button) findViewById(R.id.button4);
        konfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLKS3();
            }
        });
    }


public void openLKS3(){
        Intent intent = new Intent(this, lks3.class);
        startActivity(intent);
    }
}
