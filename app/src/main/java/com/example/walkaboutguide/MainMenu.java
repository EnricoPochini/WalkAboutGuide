package com.example.walkaboutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainMenu extends AppCompatActivity {

    //connessione al server
    private final int porta = 150;
    private String ip = "172.24.64.1";


    private int numTuristi;

    //Orario arrivo (gestito come due int)

    private int numVegetariani = 0;
    private int numVegani = 0;
    private int numGlutenFree = 0;

    //region UI
    TextView dysplayNominativoGuida;
    EditText dysplayNumTuristi;
    EditText dysplayOrarioArrivo;
    //TimePickerDialog timePickerDialog;

    TextView displayNumVegetariani;
    TextView displayNumVegani;
    TextView displayNumGlutenFree;
    EditText displayInfoAggiuntive;

    Button inviaDati;

    FloatingActionButton addVegetariano;
    FloatingActionButton addVegano;
    FloatingActionButton addGlutenFree;

    FloatingActionButton removeVegetariano;
    FloatingActionButton removeVegano;
    FloatingActionButton removeGlutenFree;
    //endregion

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //region UI_Assegnazione
        dysplayNominativoGuida = findViewById(R.id.nominativoGuida);

        dysplayNumTuristi = findViewById(R.id.numeroTuristi);
        dysplayOrarioArrivo = findViewById(R.id.orarioArrivo);

        displayNumVegetariani = findViewById(R.id.numVegetariani);
        displayNumVegani = findViewById(R.id.numVegani);
        displayNumGlutenFree = findViewById(R.id.numGlutenFree);
        displayInfoAggiuntive = findViewById(R.id.infoAggiuntive);

        inviaDati = findViewById(R.id.InviaDati);

        addVegetariano = findViewById(R.id.addVegetariano);
        addVegano = findViewById(R.id.addVegano);
        addGlutenFree = findViewById(R.id.addGlutenFree);

        removeVegetariano = findViewById(R.id.removeVegetariano);
        removeVegano = findViewById(R.id.removeVegano);
        removeGlutenFree = findViewById(R.id.removeGlutenFree);

        //endregion

        //set nominativo guida
        dysplayNominativoGuida.setText(dysplayNominativoGuida.getText()+" "+MainActivity.getNomeGuida());


        //region ADD


        //bottone invia i dati
        inviaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendInfo();
                reset();

            }
        });

        addVegetariano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numVegetariani++;
                displayNumVegetariani.setText(String.valueOf(numVegetariani));
            }
        });

        addVegano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numVegani++;
                displayNumVegani.setText(String.valueOf(numVegani));
            }
        });

        addGlutenFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numGlutenFree++;
                displayNumGlutenFree.setText(String.valueOf(numGlutenFree));
            }
        });

        //endregion

        //region REMOVE
        removeVegetariano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numVegetariani = decrement(numVegetariani);
                displayNumVegetariani.setText(String.valueOf(numVegetariani));
            }
        });

        removeVegano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numVegani = decrement(numVegani);
                displayNumVegani.setText(String.valueOf(numVegani));
            }
        });

        removeGlutenFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numGlutenFree = decrement(numGlutenFree);
                displayNumGlutenFree.setText(String.valueOf(numGlutenFree));
            }
        });

        //endregion


    }


    public int decrement(int value){
        if(value==0){
            return 0;
        }
        return value-1;
    }



    //invia al server le info
    private void sendInfo(){

        if(!dysplayOrarioArrivo.getText().toString().isEmpty() && !dysplayOrarioArrivo.getText().toString().isEmpty()){

            Toast.makeText(MainMenu.this, "Invio dati alla cucina", Toast.LENGTH_SHORT).show();

            try(Socket server = new Socket(ip,porta);
                PrintWriter out = new PrintWriter(server.getOutputStream(),true);
            ){

                out.println(buildData());


            }catch(IOException ignored){


            }

            return;

        }//if

        Toast.makeText(MainMenu.this, "Completa i campi prima di inviare i dati", Toast.LENGTH_SHORT).show();

    }

    //costruisce la stringa da inviare
    private String buildData(){

        //nome guida
        return MainActivity.getNomeGuida() + "#" +
                //orario di arrivo
                "in arrivo per le: " + dysplayOrarioArrivo.getText().toString() + "#" +
                //numero turisti
                "Turisti: " + dysplayNumTuristi.getText().toString() +
                //info intolleranze
                " Vegetariani: " + numVegetariani + "  Vegani: " + numVegani + "  Gluten free: " + numGlutenFree + "#" +
                //info aggiuntive
                displayInfoAggiuntive.getText().toString() + "#";
    }

    //resetta i campi dei dati
    private void reset(){

        dysplayNumTuristi.setText("");
        dysplayOrarioArrivo.setText("");
        numTuristi =0;
        numVegani=0;
        displayNumVegani.setText("0");
        numGlutenFree=0;
        displayNumGlutenFree.setText("0");
        numVegetariani=0;
        displayNumVegetariani.setText("0");
        displayInfoAggiuntive.setText("");

    }



}