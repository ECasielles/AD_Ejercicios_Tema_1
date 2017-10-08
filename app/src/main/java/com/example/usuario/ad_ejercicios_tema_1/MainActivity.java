package com.example.usuario.ad_ejercicios_tema_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btnConvertir:
                intent = new Intent(MainActivity.this, Ejercicio1Activity.class);
                startActivity(intent);
                break;
            case R.id.btnEj2:
                intent = new Intent(MainActivity.this, Ejercicio2Activity.class);
                startActivity(intent);
                break;
            case R.id.btnEj3:
                intent = new Intent(MainActivity.this, Ejercicio3Activity.class);
                startActivity(intent);
                break;
            case R.id.btnEj4:
                intent = new Intent(MainActivity.this, Ejercicio4Activity.class);
                startActivity(intent);
                break;
        }
    }
}
