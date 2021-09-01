package com.example.compraventa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner listaCategorias;
    private SeekBar barra;
    private TextView mostrarPorcentaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //coloca las categorias en el spinner

        listaCategorias = (Spinner)findViewById(R.id.spinnerCategoria);

        String[] categorias = getResources().getStringArray(R.array.listaCategorias);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        listaCategorias.setAdapter(adapter);

        //esto es para que muestre el % de la barra

        barra  = (SeekBar) findViewById(R.id.seekBarDescuento);
        mostrarPorcentaje = (TextView)findViewById(R.id.txtPorcentaje);

        barra.setProgress(0);
        barra.setMax(100);
        barra.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        mostrarPorcentaje.setText(String.valueOf(progress) + " %");
                    }
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }
}