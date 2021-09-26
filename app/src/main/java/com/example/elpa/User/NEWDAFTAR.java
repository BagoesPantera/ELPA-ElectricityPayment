package com.example.elpa.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elpa.System.NotificationHelper;
import com.example.elpa.R;
import com.example.elpa.System.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NEWDAFTAR extends AppCompatActivity {
    private Button daftar;
    private FirebaseAuth mAuth;
    private EditText username, pass, confirmpass, email,meteran;
    private static final String TAG = "register";
    private DatabaseReference database;
    private NotificationHelper mNotificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdaftar);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance().getReference();

        final ProgressDialog pd = new ProgressDialog(NEWDAFTAR.this);

        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        confirmpass = (EditText) findViewById(R.id.confirmpass);
        email = (EditText) findViewById(R.id.email);
        meteran = (EditText) findViewById(R.id.meteran);

        mNotificationHelper = new NotificationHelper(this);

        daftar = (Button) findViewById(R.id.batenregris);
        daftar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                final String usernames = username.getText().toString();
                final String passs = pass.getText().toString();
                String confirmpasss = confirmpass.getText().toString();
                final String emails = email.getText().toString();
                final String meterans = meteran.getText().toString();
                final String title = "Halo "+ usernames;
                final String message = "Selamat Datang di ELPA ! Silahkan login terlebih dahulu.";

                if(usernames.equals("")){
                    Toast.makeText(getApplicationContext(),"Nama Pengguna Kosong",Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                }else if(passs.equals("")){
                    Toast.makeText(getApplicationContext(),"Kata Sandi Kosong",Toast.LENGTH_SHORT).show();
                    pass.requestFocus();
                }else if(passs.length()< 6 ){
                    Toast.makeText(getApplicationContext(),"Kata Sandi Harus 6 Huruf atau Lebih",Toast.LENGTH_SHORT).show();
                    pass.requestFocus();
                }else if(confirmpasss.equals("")){
                    Toast.makeText(getApplicationContext(),"Konfirmasi Kata Sandi Kosong",Toast.LENGTH_SHORT).show();
                    confirmpass.requestFocus();
                }else if(emails.equals("")){
                    Toast.makeText(getApplicationContext(),"E-Mail Kosong",Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else if(meterans.equals("")){
                    Toast.makeText(getApplicationContext()," Nomor Meteran Kosong",Toast.LENGTH_SHORT).show();
                    meteran.requestFocus();
                }else if(!passs.equals(confirmpasss)){
                    Toast.makeText(getApplicationContext(),"Kata Sandi dan Konfirmasi Kata Sandi berbeda",Toast.LENGTH_SHORT).show();
                    pass.requestFocus();
                }else{

                    pd.setMessage("Loading...");
                    pd.show();

                    mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            Log.d(TAG,""+task.getResult().getSignInMethods().size());
                            if (task.getResult().getSignInMethods().size() == 0){
                                mAuth.createUserWithEmailAndPassword(emails, passs)
                                        .addOnCompleteListener(NEWDAFTAR.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    FirebaseUser user = mAuth.getCurrentUser();

                                                    submitData(new Request(
                                                            usernames,
                                                            passs,
                                                            emails,
                                                            meterans));
                                                    submitDatas(new Request(
                                                            usernames,
                                                            passs,
                                                            emails,
                                                            meterans));

                                                    pd.dismiss();
                                                    sendNotif(title,message);
                                                    openlogin();
                                                } else {
                                                    pd.dismiss();
                                                    Toast.makeText(getApplicationContext(),"Cek Kembali Koneksi Anda",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }else {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"E-Mail Telah Digunakan",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public void submitData(Request request){
        database.child("userData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(request);
    }
    public void submitDatas(Request request){
        database.child("userDatas").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(request);
    }
    public void openlogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void sendNotif(String title,String message){
        NotificationCompat.Builder nb = mNotificationHelper.getRegnotif(title,message);
        mNotificationHelper.getManager().notify(1, nb.build());
    }
}