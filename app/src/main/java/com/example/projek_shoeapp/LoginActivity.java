package com.example.projek_shoeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView got_reg;

    EditText editTextEmail,editTextPassword;
    Button button_login;
    sqlite_db myDatabasePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDatabasePassword = new sqlite_db(this);

        editTextEmail=findViewById(R.id.txEmail);
        editTextPassword=findViewById(R.id.txPassword);
        button_login=findViewById(R.id.btnLogin);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt_login();
            }
        });

        got_reg=findViewById(R.id.signup);
        got_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void attempt_login() {
        String email=editTextEmail.getText().toString();
        String password=editTextPassword.getText().toString();

        if (!isEmailValid(email)){
            Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
        }
        if (!isPasswordValid(password)){
            Toast.makeText(this, "Password Invalid", Toast.LENGTH_SHORT).show();
        }
        Cursor res=myDatabasePassword.login_user(email,password);
        if (res.getCount()==1){
            final Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Invalid Account", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length()>5;
    }


}