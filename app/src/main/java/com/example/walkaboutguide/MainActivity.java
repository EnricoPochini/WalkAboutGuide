package com.example.walkaboutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    EditText input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = findViewById(R.id.login_button);
        input_password = findViewById(R.id.password_input);

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (loginCheck(input_password.getText().toString())){
                    Toast.makeText(MainActivity.this, "Login succesful", Toast.LENGTH_SHORT).show();
                    openMainMenu();
                }
            }
        });
    }

    //region Metodi Ausiliari
    public boolean loginCheck(String passwordInput){
        //TODO fare roba password bene
        if(passwordInput.equals("1234")){
            return true;
        }
        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    //endregion

}

