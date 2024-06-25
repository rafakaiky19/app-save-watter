package com.example.projeto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity2 extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler= new Handler();
        handler.postDelayed((Runnable) () -> {
            Intent intent = new Intent(MainActivity2.this, Tela_de_login.class);
            startActivity(intent);
            finish();
        },4000);
    }
}