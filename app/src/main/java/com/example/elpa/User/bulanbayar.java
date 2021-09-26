package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.elpa.R;

public class bulanbayar extends AppCompatActivity {
private Button back;
private Button januari,februari,maret,april,mei,juni,juli,agustus,september,oktober,november,desember;
String st;
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
        setContentView(R.layout.activity_bulanbayar);



        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        januari = (Button) findViewById(R.id.januari);
        januari.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Januari";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        februari = (Button) findViewById(R.id.februari);
        februari.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Februari";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        maret = (Button) findViewById(R.id.maret);
        maret.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Maret";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        april = (Button) findViewById(R.id.april);
        april.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "April";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        mei = (Button) findViewById(R.id.mei);
        mei.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Mei";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        juni = (Button) findViewById(R.id.juni);
        juni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Juni";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        juli = (Button) findViewById(R.id.juli);
        juli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Juli";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        agustus = (Button) findViewById(R.id.agustus);
        agustus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Agustus";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        september = (Button) findViewById(R.id.september);
        september.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "September";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        oktober = (Button) findViewById(R.id.oktober);
        oktober.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Oktober";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        november = (Button) findViewById(R.id.november);
        november.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "November";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });

        desember = (Button) findViewById(R.id.desember);
        desember.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(bulanbayar.this, pemilihanbank.class);
                st = "Desember";
                i.putExtra("Value", st);
                startActivity(i);
                finish();
            }
        });
    }
}
