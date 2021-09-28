package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private TextView singupl;
    private View signinb;
    private String emails;
    private String passs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        email = findViewById(R.id.emaile);
        pass = findViewById(R.id.passin);
        singupl=findViewById(R.id.signinlink);
        signinb=findViewById(R.id.signinbut);


        signinb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                emails=email.getText().toString();

                System.out.println(email);

                startActivity(new Intent(Login.this,MainActivity.class));


            }


        });

        singupl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(Login.this,Signup.class));

            }
        });

    }
}