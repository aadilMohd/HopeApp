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
        email = findViewById(R.id.emialinp);
        pass = findViewById(R.id.passin);
        singupl=findViewById(R.id.signinlink);
        signinb=findViewById(R.id.signinbut);


        signinb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emails=email.getText().toString();
                passs=pass.getText().toString();

                String n = null;


                System.out.println(passs);

                Intent i = new Intent(Login.this,MainActivity.class);

                i.putExtra("email",emails);

                startActivity(i);


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