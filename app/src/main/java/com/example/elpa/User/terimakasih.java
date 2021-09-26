package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elpa.R;

public class terimakasih extends AppCompatActivity {
    private Button back;
    private TextView pbp,token;
    ImageView logos;

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
        setContentView(R.layout.activity_terimakasih);

        logos = (ImageView) findViewById(R.id.logos);
        logos.setImageResource(getIntent().getIntExtra("MyImageResource", R.drawable.putih));

        String tokenrand = getIntent().getExtras().getString("token");
        token = (TextView) findViewById(R.id.token);
        token.setText(tokenrand);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(terimakasih.this,menuutama.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        pbp = (TextView) findViewById(R.id.pbp);
        pbp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openpbp();
            }
        });
    }
    public void openpbp(){
        Intent intent = new Intent(this, com.example.elpa.User.pbp.class);
        startActivity(intent);
    }


}
