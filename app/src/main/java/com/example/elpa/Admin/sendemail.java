package com.example.elpa.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elpa.R;

public class sendemail extends AppCompatActivity {
private TextView email,input;
private EditText answer;
private Button send,kembali;
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
        setContentView(R.layout.activity_sendemail);

        String email1 = getIntent().getStringExtra("emailss");
        final String inputs = getIntent().getStringExtra("input");

        email = (TextView) findViewById(R.id.emailemail);
        email.setText(email1);
        input = (TextView) findViewById(R.id.inputemail);
        input.setText(inputs);

        answer = (EditText) findViewById(R.id.jawabankalian);


        send = (Button) findViewById(R.id.sendemail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answers = answer.getText().toString();
                if (answers.equals("")){
                    Toast.makeText(getApplicationContext(),"JAWABAN KOSONG ",Toast.LENGTH_SHORT).show();
                    answer.requestFocus();
                }else{
                    sendEmail();
                }
            }
        });
        kembali = (Button) findViewById(R.id.kembaliemail);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    protected void sendEmail() {
//        Log.i("Send email", "");
        String TO = getIntent().getStringExtra("emailss");
        String recipient[] = TO.split(",");
        String CC = getIntent().getStringExtra("input");
        String subject = "Menjawab pertanyaan anda dari pusat bantuan pelanggan";
        String msg = answer.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

        emailIntent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(emailIntent, "Choose an email client"));
            finish();
//            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
