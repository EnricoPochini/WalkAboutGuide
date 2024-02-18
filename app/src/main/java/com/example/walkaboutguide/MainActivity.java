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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //componenti grafici
    protected Button login_button;
    protected EditText input_password;

    //connessione
    private int portaLogin = 149;
    private String ipServer = "192.168.1.10";
    private static String nomeGuida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = findViewById(R.id.login_button);
        input_password = findViewById(R.id.password_input);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //per passare il nome della guida nell'altra schermata
        Intent intent = new Intent(this, MainMenu.class);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Attendere...", Toast.LENGTH_SHORT).show();
                nomeGuida = login(input_password.getText().toString());


            }
        });
    }


    public static String getNomeGuida(){return nomeGuida;}

    //region Metodi Ausiliari

    public void openMainMenu() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    //endregion



    //manda la password al server che ritorna il nome della guida oppure errore se c'é un errore
    public String login(String passInserita) {

        String guida = "Errore";

        try {

            Socket socket = new Socket(ipServer, portaLogin);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(passInserita);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            guida = in.readLine();

            if(!guida.equals("Errore") && guida != null){

                openMainMenu();
                Toast.makeText(MainActivity.this, "Login completato", Toast.LENGTH_SHORT).show();

            }//if
            else{

                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

            }//else

        } catch (IOException ignored) {


        }

        return guida;

    }




}

