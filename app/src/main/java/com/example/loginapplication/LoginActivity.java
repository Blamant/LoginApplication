package com.example.loginapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    Button ebtnGoToRegister;
    private Button eLogin;

    String inputEmail = "";
    String inputPassword = "";

    boolean isValid = false;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        eEmail = findViewById(R.id.edtEmail);
        ePassword = findViewById(R.id.edtPassword);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eLogin = findViewById(R.id.btnLogin);
        ebtnGoToRegister = findViewById(R.id.btnGoToRegister);

        eLogin.setOnClickListener(new View.OnClickListener() {
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

                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[2];
                        field[0] = "email";
                        field[1] = "password";
                        //Creating array for data
                        String[] data = new String[2];
                        data[0] = email;
                        data[1] = password;
                        PutData putData = new PutData(getString(R.string.server_ip)+"/LoginRegister/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
                                //End ProgressBar (Set visibility to GONE)
                                if(result.equals("Login Success")){
                                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                });


            }
        });
        ebtnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}