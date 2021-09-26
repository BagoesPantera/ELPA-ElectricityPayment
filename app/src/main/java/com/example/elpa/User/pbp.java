package com.example.elpa.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elpa.R;
import com.example.elpa.System.pbpinput;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class pbp extends AppCompatActivity {
    private Button back;
    private Button kirim;
    EditText input;
    private DatabaseReference database;
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
        setContentView(R.layout.activity_pbp);

        final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });


        input = (EditText) findViewById(R.id.input);
        database = FirebaseDatabase.getInstance().getReference();

        kirim = (Button) findViewById(R.id.kirim);
        kirim.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputs = input.getText().toString();
                if(inputs.equals("")){
                    Toast.makeText(getApplicationContext(),"Input Kosong",Toast.LENGTH_SHORT).show();
                    input.requestFocus();
                }else{
                    submitData(new pbpinput(inputs,email));
                    openpbp2();
                }
            }
        });
    }
    public void submitData(pbpinput request){database.child("PBP").push().setValue(request);}
    public void openpbp2(){
        Intent intent = new Intent(this, pbp2.class);
        startActivity(intent);
    }
}
