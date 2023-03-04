package com.example.mabahin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView name ,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name  = findViewById(R.id.NAME);
        mail  = findViewById(R.id.EMAAILSL);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if(user !=null ){

            String NAME = user.getUid();
            if(!NAME.equals(null)){
                name.setText("NAME FPOM AUTH : "+NAME);
            }

            String email = user.getEmail();
            mail.setText("EMAIL FPOM AUTH : "+email);
        }



    }
}