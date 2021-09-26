package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elpa.Admin.utamaadmin;
import com.example.elpa.R;
import com.example.elpa.Admin.userdata;
import com.example.elpa.System.Homes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView daftar;
    private TextView lupa;
    private Button masuk;
    private EditText emails;
    private EditText passs;
    private FirebaseAuth mAuth;
    private long backpressed;
    private Toast backtoast;

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
        setContentView(R.layout.activity_main);

            if (!isNetworkAvailable()){
                Toast.makeText(getApplicationContext(),"Anda tidak terhubung ke internet",Toast.LENGTH_SHORT).show();
            }
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            String admin = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            if (admin.equals("admin@admin.com")){
                Intent intent = new Intent(MainActivity.this, utamaadmin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else{
                Intent intent = new Intent(MainActivity.this, menuutama.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
//            Intent intent = new Intent(MainActivity.this, menuutama.class);
//            startActivity(intent);
        }else{



        mAuth = FirebaseAuth.getInstance();

        final ProgressDialog pd = new ProgressDialog(MainActivity.this);

        emails = (EditText) findViewById(R.id.username);
        passs = (EditText) findViewById(R.id.password);



        masuk = (Button) findViewById(R.id.masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
             String email = emails.getText().toString();
             String pass = passs.getText().toString();


                if(email.equals("")){
                    Toast.makeText(getApplicationContext(),"E-Mail Kosong",Toast.LENGTH_SHORT).show();
                    emails.requestFocus();
                }else if(pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Password Kosong",Toast.LENGTH_SHORT).show();
                    passs.requestFocus();
                }else {
                    pd.setMessage("Loading...");
                    pd.show();

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete( Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    pd.dismiss();
                                    openutama();
                                }else {
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(),"E-Mail atau Kata Sandi salah",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
            }
        });
        daftar = (TextView) findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               openREGISTER();
           }
        });


        lupa = (TextView) findViewById(R.id.lupa);
        lupa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLKS();
            }
        });
    }}
    public void openREGISTER(){
        Intent intent = new Intent(this, NEWDAFTAR.class);
        startActivity(intent);
    }
    public void openLKS(){
        Intent intent = new Intent(this, lks1.class);
        startActivity(intent);
    }
    public void openutama(){
        String email = emails.getText().toString();
        if (email.equals("admin@admin.com")){
            Intent intent = new Intent(MainActivity.this, utamaadmin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity.this, menuutama.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
