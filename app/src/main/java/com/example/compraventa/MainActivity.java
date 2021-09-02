package com.example.compraventa;

import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Spinner listaCategorias;
    private SeekBar barraDescuento;
    private TextView mostrarPorcentaje;
    private Switch ofrecerDescuento;
    private CheckBox checkRetiroEnPersona;
    private TextView txtDireccion;
    private TextView lblDireccion;
    private CheckBox checkTerminosYcondiciones;
    private Button btnPublicar;

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

        barraDescuento  = (SeekBar) findViewById(R.id.seekBarDescuento);
        mostrarPorcentaje = (TextView)findViewById(R.id.txtPorcentaje);

        barraDescuento.setProgress(0);
        barraDescuento.setMax(100);
        barraDescuento.setOnSeekBarChangeListener(
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

        //si se activa el switch muestra la barra de descuento, caso contrario la oculta

        ofrecerDescuento = (Switch) findViewById(R.id.switchOfrecerDescuento);

        ofrecerDescuento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mostrarPorcentaje.setVisibility(View.VISIBLE);
                    barraDescuento.setVisibility(View.VISIBLE);
                } else {
                    mostrarPorcentaje.setVisibility(View.GONE);
                    barraDescuento.setVisibility(View.GONE);
                }
            }
        });

        //si se selecciona el check de retiro en persona aparecera el campo para ingresar la direccion

        checkRetiroEnPersona = (CheckBox) findViewById(R.id.checkBoxRetiro);
        txtDireccion = (TextView) findViewById(R.id.txtDireccionRetiro);
        lblDireccion = (TextView) findViewById(R.id.lblDireccionDeRetiro);

        checkRetiroEnPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRetiroEnPersona.isChecked()){
                    lblDireccion.setVisibility(View.VISIBLE);
                    txtDireccion.setVisibility(View.VISIBLE);
                }else{
                    lblDireccion.setVisibility(View.GONE);
                    txtDireccion.setVisibility(View.GONE);
                }
            }
        });

        //si acepta los terminos y condiciones se habilita el boton publicar

        checkTerminosYcondiciones = (CheckBox) findViewById(R.id.checkBoxTerminosYcondiciones);
        btnPublicar = (Button) findViewById(R.id.btnPublicar);

        checkTerminosYcondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkTerminosYcondiciones.isChecked()){
                    btnPublicar.setEnabled(true);
                }else{
                    btnPublicar.setEnabled(false);

                }
            }
        });

        //si presiona publicar


    }

}

