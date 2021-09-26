package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.elpa.R;

public class lks3 extends AppCompatActivity {
private Button kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lks3);

        kembali = (Button) findViewById(R.id.button5);
        kembali.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                openlogin();
            }
        });
    }
    public void openlogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
