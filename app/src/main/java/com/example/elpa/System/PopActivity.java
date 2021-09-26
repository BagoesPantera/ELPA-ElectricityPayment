package com.example.elpa.System;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elpa.R;
import com.example.elpa.User.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PopActivity extends AppCompatActivity {
    private Button gas, g;
    private DatabaseReference database,databases;
    private EditText pass;

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
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final ProgressDialog pd = new ProgressDialog(PopActivity.this);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        database = FirebaseDatabase.getInstance().getReference("userData");
        databases = FirebaseDatabase.getInstance().getReference("userDatas");
        final String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pass = (EditText) findViewById(R.id.passhapusakun);


        gas = (Button) findViewById(R.id.gas);
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String password = pass.getText().toString();


                if (password.equals("")){
                    Toast.makeText(getApplicationContext(),"Password Kosong",Toast.LENGTH_SHORT).show();
                    pass.requestFocus();
                }else{
                    pd.setMessage("Loading...");
                    pd.show();
                    try {
                        AuthCredential credential = EmailAuthProvider
                                .getCredential(email, password);
                        if(user!=null) {
                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent i = new Intent(PopActivity.this,MainActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            databases.child(id).removeValue();
                                            database.child(id).removeValue();
                                            pd.dismiss();
                                            startActivity(i);
                                            Toast.makeText(getApplicationContext(),"AKUN TERHAPUS",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }catch (Exception e){
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Gagal menghapus akun...",Toast.LENGTH_SHORT).show();
                    }

            }}
            });

        g = (Button) findViewById(R.id.tidak);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
