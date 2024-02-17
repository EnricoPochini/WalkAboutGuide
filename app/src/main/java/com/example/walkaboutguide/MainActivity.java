package com.example.walkaboutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //componenti grafici
    protected Button login_button;
    protected EditText input_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = findViewById(R.id.login_button);
        input_password = findViewById(R.id.password_input);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadLogin threadLogin = new ThreadLogin(input_password.getText().toString());
                threadLogin.start();


                if(input_password.getText().toString().equals("Errore")){

                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                }//if
                else{

                    Toast.makeText(MainActivity.this, "Login completato", Toast.LENGTH_SHORT).show();

                    openMainMenu();

                }//else


            }
        });
    }

    //region Metodi Ausiliari

    public void openMainMenu() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    //endregion



    class ThreadLogin extends Thread {

        //connessione
        private int portaLogin = 149;
        private String ipServer = "10.0.2.2";

        //login
        private String inputPassword;

        public ThreadLogin(String inputPassword) {
            this.inputPassword=inputPassword;
        }//costruttore


        @Override
        public void run() {

            login();

        }


        //manda la password al server che irotrna il nome della guida oppure errore se c'é un errore
        public String login() {

            String guida = "Errore";

            try {

                Socket socket = new Socket(ipServer, portaLogin);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(inputPassword);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                guida = in.readLine();

                input_password.setText(guida);

            } catch (IOException ignored) {


            }

            return guida;

        }

    }



}

