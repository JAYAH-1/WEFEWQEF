package com.example.mabahin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class signUp extends AppCompatActivity {
    EditText fullname, contact, email, password;
    Button SingUp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        fullname = findViewById(R.id.fullname);
        contact = findViewById(R.id.contactNO);
        email = findViewById(R.id.EMAIL);
        password = findViewById(R.id.pass);
        SingUp = findViewById(R.id.Signup);

        String mail, pass, fullName, contact;
        mail = email.getText().toString();
        pass = password.getText().toString();
        fullName = password.getText().toString();
        contact = password.getText().toString();


        new Thread(new Runnable() {
            @Override
            public void run() {

                SingUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        duplicateChecker();
                            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d("test", "createUserWithEmail:success");
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                toFirestore();
                                                Intent i = new Intent(signUp.this, logIn.class);
                                                Toast.makeText(signUp.this, "Pleas log in now !", Toast.LENGTH_SHORT).show();
                                                startActivity(i);

                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w("tset", "createUserWithEmail:failure", task.getException());
                                                Toast.makeText(signUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });


                    }
                });
            }
        }).start();
    }


     public void duplicateChecker(){
        mAuth.fetchSignInMethodsForEmail(email.getText().toString())
                 .addOnCompleteListener(task -> {
                     if (task.isSuccessful()) {
                         // The email address is already in use
                         Toast.makeText(this, "THE EMAIL IS ALREADY IN USED", Toast.LENGTH_SHORT).show();

                     } else {

                     }
                 });
     }
    public void toFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String Email = email.getText().toString();
        String pasword = password.getText().toString();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String id = user.getUid();


        HashMap<String, Object> usersDATA = new HashMap<>();
        usersDATA.put("email", Email);
        usersDATA.put("pasword", pasword);

        db.collection("Users").document(id)
                .set(usersDATA)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}