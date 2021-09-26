package com.example.elpa.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elpa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class lks1 extends AppCompatActivity {
    private Button kembali;
    private Button submit;
    private EditText emails;
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
        setContentView(R.layout.activity_lks1);

        kembali = (Button) findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        emails = (EditText) findViewById(R.id.email);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        submit = (Button) findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String email = emails.getText().toString();
                if(email.equals("")){
                    Toast.makeText(getApplicationContext(),"E-Mail Kosong",Toast.LENGTH_SHORT).show();
                    emails.requestFocus();
                }else{
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        if (firebaseUser != null ){
                                            FirebaseAuth.getInstance().signOut();
                                        }
                                        Toast.makeText(getApplicationContext(),"Kami telah mengirim link untuk mengganti kata sandi ke E-Mail anda.",Toast.LENGTH_SHORT).show();
                                        openlogin();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Cek Kembali E-Mail anda",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
public void openlogin(){
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
}
}
