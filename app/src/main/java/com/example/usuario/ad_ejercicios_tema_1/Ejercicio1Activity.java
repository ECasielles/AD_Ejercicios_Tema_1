package com.example.usuario.ad_ejercicios_tema_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Ejercicio1Activity extends AppCompatActivity {

    Button btnConvertir;
    Switch swtCambio;
    EditText edtDolar, edtEuro;
    Boolean E2D = true;

    float conversionDolarAEuro = 0.8524787f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        edtDolar = (EditText) findViewById(R.id.edtDolar);
        edtEuro = (EditText) findViewById(R.id.edtEuro);

        btnConvertir = (Button) findViewById(R.id.btnConvertir);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (E2D) {
                    Editable texto = edtDolar.getText();
                    if (texto != null){
                        try {
                            edtEuro.setText(String.valueOf(Float.valueOf(texto.toString()) * conversionDolarAEuro));
                        } catch (Exception e) {
                            Toast.makeText(Ejercicio1Activity.this, "Error de entrada de datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Editable texto = edtEuro.getText();
                    if (texto != null){
                        try {
                            edtDolar.setText(String.valueOf(Float.valueOf(texto.toString()) / conversionDolarAEuro));
                        } catch (Exception e) {
                            Toast.makeText(Ejercicio1Activity.this, "Error de entrada de datos", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        swtCambio = (Switch) findViewById(R.id.swtCambio);
        swtCambio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(E2D) {
                    compoundButton.setText("Euros a Dólares");
                } else {
                    compoundButton.setText("Dólares a Euros");
                }
                E2D = !E2D;
            }
        });


    }
}
