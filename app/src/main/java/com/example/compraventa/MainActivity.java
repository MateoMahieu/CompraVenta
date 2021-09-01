package com.example.compraventa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCategorias = (Spinner)findViewById(R.id.spinnerCategoria);
        String[] categorias = {"INDUMENTARIA", "ELECTRONICA", "ENTRETENIMIENTO", "JARDIN", "VEHICULOS", "JUGUETES"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        listaCategorias.setAdapter(adapter);
    }
}