package com.example.projeto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TeladeCarregamento extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telade_carregamento);

        handler= new Handler();
        handler.postDelayed((Runnable) () -> {
            Intent intent = new Intent(TeladeCarregamento.this,selecao.class);
            startActivity(intent);
            finish();
        },4000);

    }
}