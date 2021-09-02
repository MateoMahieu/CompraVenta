package com.example.compraventa;

import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private Spinner listaCategorias;
    private SeekBar barraDescuento;
    private TextView mostrarPorcentaje;
    private Switch ofrecerDescuento;
    private CheckBox checkRetiroEnPersona;
    private EditText txtDireccion;
    private TextView lblDireccion;
    private CheckBox checkTerminosYcondiciones;
    private Button btnPublicar;
    private EditText txtTitulo;
    private EditText txtPrecio;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //coloca las categorias en el spinner

        listaCategorias = (Spinner) findViewById(R.id.spinnerCategoria);

        String[] categorias = getResources().getStringArray(R.array.listaCategorias);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        listaCategorias.setAdapter(adapter);

        //esto es para que muestre el % de la barra

        barraDescuento = (SeekBar) findViewById(R.id.seekBarDescuento);
        mostrarPorcentaje = (TextView) findViewById(R.id.txtPorcentaje);

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
        txtDireccion = (EditText) findViewById(R.id.txtDireccionRetiro);
        lblDireccion = (TextView) findViewById(R.id.lblDireccionDeRetiro);

        checkRetiroEnPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRetiroEnPersona.isChecked()) {
                    lblDireccion.setVisibility(View.VISIBLE);
                    txtDireccion.setVisibility(View.VISIBLE);
                } else {
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
                if (checkTerminosYcondiciones.isChecked()) {
                    btnPublicar.setEnabled(true);
                } else {
                    btnPublicar.setEnabled(false);

                }
            }
        });

        //si presiona publicar valida que los datos sean ingresados

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validaciones campos obligaotiros vacios

                if(txtTitulo.getText().toString().isEmpty()){
                txtTitulo.setError("Campo obligatorio");
                }
                if(checkRetiroEnPersona.isChecked() && (txtDireccion.getText().toString().isEmpty())){
                    txtDireccion.setError("Campo obligatorio");
                }
                if(listaCategorias.getSelectedItemId() == 0){
                    Toast.makeText(getApplicationContext(), "Error: La categoria es un campo obligatorio", Toast.LENGTH_LONG).show();
                }
                if(txtPrecio.getText().toString().isEmpty()){
                    txtPrecio.setError("Campo obligatorio");
                }else{
                    if(Integer.parseInt(txtPrecio.getText().toString()) == 0){
                        txtPrecio.setError("El precio del producto debe ser mayor a 0");
                    }
                }
                if(!txtEmail.getText().toString().isEmpty()){
                    if(!validarEmail(txtEmail.getText().toString())){
                        txtEmail.setError("Email no valido");
                    }
                }
            }
        });

    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}


