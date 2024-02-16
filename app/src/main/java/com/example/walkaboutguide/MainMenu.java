package com.example.walkaboutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenu extends AppCompatActivity {

    int numTuristi;

    //TODO capire il tipo di orario arrivo e aggiungere

    int numVegetariani = 0;
    int numVegani = 0;
    int numGlutenFree = 0;

    //region UI
    EditText dysplayNumTuristi;
    EditText dysplayOrarioArrivo;

    TextView displayNumVegetariani;
    TextView displayNumVegani;
    TextView displayNumGlutenFree;

    Button inviaDati;

    FloatingActionButton addVegetariano;
    FloatingActionButton addVegano;
    FloatingActionButton addGlutenFree;

    FloatingActionButton removeVegetariano;
    FloatingActionButton removeVegano;
    FloatingActionButton removeGlutenFree;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //region UI
        dysplayNumTuristi = findViewById(R.id.numeroTuristi);
        dysplayOrarioArrivo = findViewById(R.id.orarioArrivo);

        displayNumVegetariani = findViewById(R.id.numVegetariani);
        displayNumVegani = findViewById(R.id.numVegani);
        displayNumGlutenFree = findViewById(R.id.numGlutenFree);

        inviaDati = findViewById(R.id.InviaDati);

        addVegetariano = findViewById(R.id.addVegetariano);
        addVegano = findViewById(R.id.addVegano);
        addGlutenFree = findViewById(R.id.addGlutenFree);

        removeVegetariano = findViewById(R.id.removeVegetariano);
        removeVegano = findViewById(R.id.removeVegano);
        removeGlutenFree = findViewById(R.id.removeGlutenFree);

        //endregion

        //region ADD

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

        //endregion{



    }

    //region AUSILIARI

    public int decrement(int value){
        if(value==0){
            return 0;
        }
        return value-1;
    }

    public void updateNumTuristi(){
        numTuristi = Integer.parseInt(dysplayNumTuristi.getText().toString());
    }

    //TODO update orario arrivo

    //endregion
}