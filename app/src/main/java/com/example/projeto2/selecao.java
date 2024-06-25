package com.example.projeto2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class selecao extends AppCompatActivity {

    private ImageView iconiregistradas;
    private ImageView registrar;

    private ImageButton Button_perfil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);


        iconiregistradas = findViewById(R.id.Iconiregistradas);
        iconiregistradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Nascentes_registradas.class);
                startActivity(intent);

            }
        });


        registrar= findViewById(R.id.registar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });


        Button_perfil= findViewById(R.id.Button_perfil);
        Button_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Perfil.class);
                startActivity(intent);

            }
        });

    }
}