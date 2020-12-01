package com.example.loginapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttemptsInfo;
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
        setContentView(R.layout.activity_main);

        eEmail = findViewById(R.id.edtEmail);
        ePassword = findViewById(R.id.edtPassword);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eLogin = findViewById(R.id.btnLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = eEmail.getText().toString();
                String inputPassword = ePassword.getText().toString();

                //Check if credentials are correct

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Voer alstublieft alle gegevens in", Toast.LENGTH_LONG).show();
                }else{

                    isValid = validate(inputName, inputPassword);

                    if (!isValid){

                        counter--;

                        eAttemptsInfo.setText("Pogingen over: " + String.valueOf(counter));

                        Toast.makeText(MainActivity.this, "Incorrecte gegevens ingevoerd", Toast.LENGTH_LONG).show();

                        if (counter == 0){
                            eLogin.setEnabled(false);
                            Toast.makeText(MainActivity.this, "U heeft alle pogingen gebruikt", Toast.LENGTH_LONG).show();

                        }

                    }else{
                        Toast.makeText(MainActivity.this, "Login succesvol", Toast.LENGTH_LONG).show();
                    }

                    //New activity
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);

                }

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