package com.example.mabahin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class logIn extends AppCompatActivity {

 EditText  email , password;
 Button login;
 FirebaseAuth mauth;
 TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mauth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.paswword);
        login =(Button) findViewById(R.id.btn);
        btn = findViewById(R.id.toSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(logIn.this, signUp.class);

                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FirebaseAuth auth =FirebaseAuth.getInstance();
               FirebaseUser user = auth.getCurrentUser();

               String name = user.getDisplayName();
               String email = user.getDisplayName();

                Intent i = new Intent(logIn.this, signUp.class);
                i.putExtra("name", name);
                i.putExtra("email", email);
                login();
            }
        });
    }



    private void login() {
        String Email = email.getText().toString();
        String pasword = password.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mauth.signInWithEmailAndPassword(Email, pasword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d("test", "createUserWithEmail:success");
                                    Intent i = new Intent(logIn.this, MainActivity.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("tets", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(logIn.this, "Please create Account First ",
                                            Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        }).start();
    }


}