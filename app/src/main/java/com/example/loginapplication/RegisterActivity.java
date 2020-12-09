package com.example.loginapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {
    EditText eFullName, eEmail, ePassword;
    Button ebtnRegister;
    Button ebtnGoToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eFullName = findViewById(R.id.edtFullName);
        eEmail = findViewById(R.id.edtEmail);
        ePassword = findViewById(R.id.edtPassword);
        ebtnRegister = findViewById(R.id.btnRegister);
        ebtnGoToLogin = findViewById(R.id.btnGoToLogin);



        ebtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eEmail.getText().toString().trim();
                String password = ePassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    eEmail.setError("Email is verplicht");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ePassword.setError("Wachtwoord is verplicht");
                    return;
                }

                if(password.length() < 6){
                    ePassword.setError("Wachtwoord moet langer dan 6 karakters zijn");
                    return;
                }


            }
        });

        ebtnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}