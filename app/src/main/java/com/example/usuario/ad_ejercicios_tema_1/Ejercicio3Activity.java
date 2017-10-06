package com.example.usuario.ad_ejercicios_tema_1;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ejercicio3Activity extends AppCompatActivity {

    TextView txvTime;
    Button btnComenzar, btnMas, btnMenos;
    int contadorTiempo = 5;
    MyCountDownTimer miContador;
    long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        txvTime = (TextView) findViewById(R.id.txvTime);
        btnComenzar = (Button) findViewById(R.id.btnComenzar);
        btnMas = (Button) findViewById(R.id.btnMas);
        btnMenos = (Button) findViewById(R.id.btnMenos);
        txvTime.setText(contadorTiempo + ":" + "00" );
        time = contadorTiempo * 60 * 1000;

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (miContador != null) {
                    time = miContador.actualTime;
                    miContador.cancel();
                    miContador = null;
                    btnComenzar.setText("Reanudar");
                } else {
                    miContador = new MyCountDownTimer(time, 1000, txvTime, Ejercicio3Activity.this);
                    miContador.start();
                    btnComenzar.setText("Pausa");
                }
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contadorTiempo < 10) contadorTiempo++;
                restartTimer();
            }
        });
        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contadorTiempo > 1) contadorTiempo--;
                restartTimer();
            }
        });
    }

    private void restartTimer() {
        time = contadorTiempo * 60 * 1000;
        if (miContador != null) miContador.cancel();
        txvTime.setText(contadorTiempo + ":" + "00" );
        miContador = null;
        btnComenzar.setText("Comenzar");
    }
    protected void endTimer() {
        time = contadorTiempo * 60 * 1000;
        miContador = null;
        btnComenzar.setText("Comenzar");
    }


}

class MyCountDownTimer extends CountDownTimer {

    TextView txvTime;
    long actualTime;
    Context context;

    MyCountDownTimer(long millisInFuture, long countDownInterval, TextView txvTime, Context context) {
        super(millisInFuture, countDownInterval);
        this.txvTime = txvTime;
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int minutos = (int) millisUntilFinished / 60000;
        int segundos = (int) (millisUntilFinished - minutos * 60000) / 1000;
        txvTime.setText(minutos + ":" + segundos);
        actualTime = millisUntilFinished;

    }
    @Override
    public void onFinish() {
        txvTime.setText("Pausa terminada!");
        ((Ejercicio3Activity) context).endTimer();
    }

}
