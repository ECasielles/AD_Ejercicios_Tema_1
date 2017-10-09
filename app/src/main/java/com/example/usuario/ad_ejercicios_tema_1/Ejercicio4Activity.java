package com.example.usuario.ad_ejercicios_tema_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Ejercicio4Activity extends AppCompatActivity implements View.OnClickListener{

    TextView medtEntrada, medtSalida, medtnClave;
    Button mbtnCifrar, mbtnDescifrar;
    Cesar csr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio4);
        medtEntrada = (EditText) findViewById(R.id.edtEntrada);
        medtSalida = (EditText) findViewById(R.id.edtSalida);
        medtnClave = (EditText) findViewById(R.id.edtnClave);

        mbtnCifrar = (Button) findViewById(R.id.btnCifrar);
        mbtnDescifrar = (Button) findViewById(R.id.btnDescifrar);

        mbtnCifrar.setOnClickListener(this);
        mbtnDescifrar.setOnClickListener(this);

        csr = new Cesar(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnCifrar:
                if (medtEntrada.getText().length() > 0 && Pattern.matches("-?+\\d+", medtnClave.getText()))
                    medtSalida.setText(
                            csr.cifrar(
                                    medtEntrada.getText().toString(),
                                    Long.parseLong(medtnClave.getText().toString())
                            )
                    );
                break;
            case R.id.btnDescifrar:
                if (medtEntrada.getText().length() > 0 && Pattern.matches("-?+\\d+", medtnClave.getText()))
                    medtSalida.setText(
                            csr.cifrar(
                                    medtEntrada.getText().toString(),
                                    -1 * Long.parseLong(medtnClave.getText().toString())
                            )
                    );
                break;
        }
    }
}
