package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elpa.R;

import java.util.Random;

public class pemilihanbank extends AppCompatActivity {
    private Button back;
    private TextView maybank,maybanks,panin,panins,danamon,danamons,bpd,bpds,harga1,harga2,harga3,harga4;
    TextView bulan;
    String st;
    int bg;
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
        setContentView(R.layout.activity_pemilihanbank);

        String[] array = getApplicationContext().getResources().getStringArray(R.array.harga);
        final String randomharga = array[new Random().nextInt(array.length)];
        final String randomharga2 = array[new Random().nextInt(array.length)];
        final String randomharga3 = array[new Random().nextInt(array.length)];
        final String randomharga4 = array[new Random().nextInt(array.length)];

        //harga 1 maybank
        //harga 2 panin
        //harga 3 bpd
        //harga 4danamaon
        harga1 = (TextView) findViewById(R.id.harga1);
        harga2 = (TextView) findViewById(R.id.harga2);
        harga3 = (TextView) findViewById(R.id.harga3);
        harga4 = (TextView) findViewById(R.id.harga4);

        harga1.setText(randomharga);
        harga2.setText(randomharga2);
        harga3.setText(randomharga3);
        harga4.setText(randomharga4);


        bulan = (TextView) findViewById(R.id.bulan);
        st=getIntent().getExtras().getString("Value");
        bulan.setText(st);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });



        maybank = (TextView) findViewById(R.id.maybank);
        maybank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.maybank;
                String nama="Maybank";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga);
                startActivity(i);
                finish();
            }
        });

        maybanks = (TextView) findViewById(R.id.maybanks);
        maybanks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.maybank;
                String nama="Maybank";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga);
                startActivity(i);
                finish();
            }
        });

        panin = (TextView) findViewById(R.id.panin);
        panin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.panin;
                String nama="Panin Bank";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga2);
                startActivity(i);
                finish();
            }
        });

        panins = (TextView) findViewById(R.id.panins);
        panins.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.panin;
                String nama="Panin Bank";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga2);
                startActivity(i);
                finish();
            }
        });

        danamon = (TextView) findViewById(R.id.danamon);
        danamon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.danamon;
                String nama="Danamon";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga4);
                startActivity(i);
                finish();
            }
        });

        danamons = (TextView) findViewById(R.id.danamons);
        danamons.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.danamon;
                String nama="Danamon";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga4);
                startActivity(i);
                finish();
            }
        });

        bpd = (TextView) findViewById(R.id.bpd);
        bpd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.bpd;
                String nama="BPD";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga3);
                startActivity(i);
                finish();
            }
        });

        bpds = (TextView) findViewById(R.id.bpds);
        bpds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(pemilihanbank.this, konfirmasi.class);
                bg = R.drawable.bpd;
                String nama="BPD";
                i.putExtra("MyImageResource", bg);
                i.putExtra("namabank",nama);
                i.putExtra("harga",randomharga3);
                startActivity(i);
                finish();
            }
        });

    }
}
