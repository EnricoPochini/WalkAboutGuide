package com.example.walkaboutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    EditText input_password;

    ThreadLogin threadLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = findViewById(R.id.login_button);
        input_password = findViewById(R.id.password_input);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    threadLogin = new ThreadLogin(input_password.getText().toString());
                    threadLogin.start();
                    if(threadLogin.getNominativo().equals("Errore")){
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Login succesful", Toast.LENGTH_SHORT).show();
                        openMainMenu();
                    }


            }
        });
    }

    //region Metodi Ausiliari

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    //endregion

}

