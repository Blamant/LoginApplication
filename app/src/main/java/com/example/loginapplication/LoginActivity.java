package com.example.loginapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    Button ebtnGoToRegister;
    private Button eLogin;

    String inputEmail = "";
    String inputPassword = "";

class Credentials {
    String email = "Admin";
    String password = "12345678";
}

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

                String inputName = eEmail.getText().toString();
                String inputPassword = ePassword.getText().toString();

                //Check if credentials are correct

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Voer alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
                }else if (!validate(inputName, inputPassword)){



                    counter--;

                    eAttemptsInfo.setText("Pogingen over: " + String.valueOf(counter));

                    Toast.makeText(LoginActivity.this, "Incorrecte gegevens ingevoerd", Toast.LENGTH_LONG).show();

                    if (counter == 0){
                        eLogin.setEnabled(false);
                        Toast.makeText(LoginActivity.this, "U heeft alle pogingen gebruikt", Toast.LENGTH_LONG).show();

                    }


                }else{
                    Toast.makeText(LoginActivity.this, "Login succesvol", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, activity_zoeken.class);
                    startActivity(intent);
                }

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

    private boolean validate(String email, String password){

        Credentials credentials = new Credentials();

        if(email.equals(credentials.email) && password.equals(credentials.password))
        {
            return true;
        }
        return false;
    }
}