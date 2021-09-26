package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elpa.System.NotificationHelper;
import com.example.elpa.R;
import com.example.elpa.System.rek;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class konfirmasi extends AppCompatActivity {
    private Button back;
    private Button bayar;
    ImageView logo;
    EditText rek,pin;
    int bg;
    private TextView hargakonfirmasi;
    private DatabaseReference database;

    private NotificationHelper mNotificationHelper;

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
        setContentView(R.layout.activity_konfirmasi);
        boolean a = true;

        String[] array = getApplicationContext().getResources().getStringArray(R.array.token);
        final String randomtoken = array[new Random().nextInt(array.length)];

        logo = (ImageView) findViewById(R.id.logo);
        logo.setImageResource(getIntent().getIntExtra("MyImageResource", R.drawable.putih));
        final String nama = getIntent().getExtras().getString("namabank");
        final String namabank = "Pembayaran melalui "+ nama + " telah berhasil dilakukan.";

        bg = getIntent().getIntExtra("MyImageResource", R.drawable.putih);

        String harga = getIntent().getExtras().getString("harga");
        hargakonfirmasi = (TextView) findViewById(R.id.hargakonfirmasi);
        hargakonfirmasi.setText(harga);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        rek = (EditText) findViewById(R.id.rek);
        pin = (EditText) findViewById(R.id.pin);
        database = FirebaseDatabase.getInstance().getReference();

        mNotificationHelper = new NotificationHelper(this);

        bayar = (Button) findViewById(R.id.bayar);
        bayar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                String reks = rek.getText().toString();
                String pins = pin.getText().toString();

                if(reks.equals("")){
                    Toast.makeText(getApplicationContext(),"Nomor Rekening Kosong",Toast.LENGTH_SHORT).show();
                    rek.requestFocus();
                }else if(pins.equals("")){
                    Toast.makeText(getApplicationContext(),"PIN Kosong",Toast.LENGTH_SHORT).show();
                    pin.requestFocus();
                }else{
                    submitData(new rek(
                            reks.toLowerCase(),
                            pins.toLowerCase()
                    ));
                    Intent intent = new Intent(konfirmasi.this, terimakasih.class);
                    intent.putExtra("MyImageResource", bg);
                    intent.putExtra("token",randomtoken);

                    sendNotif("Terima Kasih untuk Pembelian Anda",namabank,bg,randomtoken);

                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void submitData(rek request){
        database.child("userRek").push().setValue(request);
    }
    public void openthx(){
        Intent intent = new Intent(this, terimakasih.class);
        startActivity(intent);
    }

    public void sendNotif(String title,String message, int bg, String randomtoken){
        NotificationCompat.Builder nb = mNotificationHelper.getConfirmnotif(title,message,bg,randomtoken);
        mNotificationHelper.getManager().notify(1, nb.build());
    }
}