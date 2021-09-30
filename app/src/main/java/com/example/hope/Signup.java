package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    private View signup;
    private TextView signinl;
    private TextView Fname,Phone,email,pass,pass1;
    private String Fnames,Phones,emails,passs,pass1s;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = findViewById(R.id.signupbut);
        signinl=findViewById(R.id.signinlink);
        Fname = findViewById(R.id.namee);
        Phone = findViewById(R.id.phonee);
        email = findViewById(R.id.emaile);
        pass= findViewById(R.id.pass1e);
        pass1 = findViewById(R.id.confirmpass1);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fnames = Fname.getText().toString().trim();
                Phones = Phone.getText().toString().trim();
                emails = email.getText().toString().trim();
                passs = pass.getText().toString().trim();
                pass1s = pass1.getText().toString().trim();
                mAuth = FirebaseAuth.getInstance();

                if(TextUtils.isEmpty(emails)){

                    email.setError("Enter email");
                    return;


                }

                if(TextUtils.isEmpty(passs)){

                    pass.setError("Enter Pasword");
                    return;


                }

                if (!(passs.equals(pass1s))) {

                    pass1.setError("Passwords do not match");

                    return;


                }

                mAuth.createUserWithEmailAndPassword(emails,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            startActivity(new Intent(Signup.this,MainActivity.class));

                        }
                        else {
                            Toast.makeText(Signup.this, "Sign Up failed, Try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                });









            }
        });

        signinl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
    }
}