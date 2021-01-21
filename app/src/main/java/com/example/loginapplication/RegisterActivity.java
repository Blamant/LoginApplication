package com.example.loginapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

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
                final String email = eEmail.getText().toString().trim();
                final String password = ePassword.getText().toString().trim();

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

                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[3];
                        field[0] = "fullname";
                        field[1] = "email";
                        field[2] = "password";
                        //Creating array for data
                        String[] data = new String[3];
                        data[0] = eFullName.getText().toString().trim();
                        data[1] = email;
                        data[2] = password;
                        PutData putData = new PutData("http://192.168.2.139/LoginRegister/signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                //End ProgressBar (Set visibility to GONE)
                            }
                        }
                        //End Write and Read data with URL
                    }
                });


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