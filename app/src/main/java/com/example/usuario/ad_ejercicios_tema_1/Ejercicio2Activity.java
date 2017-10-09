package com.example.usuario.ad_ejercicios_tema_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ejercicio2Activity extends AppCompatActivity {

    Button mbtnLink;
    String url;
    EditText medtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        medtLink = (EditText) findViewById(R.id.edtLink);
        mbtnLink = (Button) findViewById(R.id.btnLink);

        mbtnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = ((EditText) findViewById(R.id.edtLink)).getText().toString();

                if (Patterns.WEB_URL.matcher(url).matches()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    Intent intent = new Intent(Ejercicio2Activity.this, UrlActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
