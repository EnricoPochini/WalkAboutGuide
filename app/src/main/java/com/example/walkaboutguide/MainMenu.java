package com.example.walkaboutguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenu extends AppCompatActivity {

    int numTuristi;

    //Orario arrivo (gestito come due int)
    int oraArrivo;
    int minutiArrivo;

    int numVegetariani = 0;
    int numVegani = 0;
    int numGlutenFree = 0;

    //region UI
    TextView dysplayNominativoGuida;
    EditText dysplayNumTuristi;
    EditText dysplayOrarioArrivo;
    TimePickerDialog timePickerDialog;

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

        //region UI_Assegnazione
        dysplayNominativoGuida = findViewById(R.id.nominativoGuida);

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

        //TODO ottieni nominativo guida

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

        //endregion

        //Set orario
        dysplayOrarioArrivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(MainMenu.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dysplayOrarioArrivo.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                        oraArrivo = hourOfDay;
                        minutiArrivo = minute;
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });

    }
    //region AUSILIARI

    /*public boolean unpdateOrarioArrivo(){

        char[] orario =  dysplayOrarioArrivo.getText().toString().toCharArray();


    }*/


    public int decrement(int value){
        if(value==0){
            return 0;
        }
        return value-1;
    }

    //endregion
}