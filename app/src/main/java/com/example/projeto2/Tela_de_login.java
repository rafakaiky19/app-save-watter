package com.example.projeto2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class Tela_de_login extends AppCompatActivity {

    private TextView text_tela_cadastro;

    private EditText Text_senha,Text_email;

    private Button button_entrar;

    private ProgressBar progressBar;

    String[] mensagens = {"Preencha todos os campos", "Login realizado com sucesso"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_login);



        IniciarComponetes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_de_login.this, TeladeCadastro.class);
                startActivity(intent);
            }
        });

        button_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= Text_email.getText().toString();
                String senha= Text_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    AutenticarUsuario(view);
                }
            }
        });

    }

    private void AutenticarUsuario(View view){
        String email= Text_email.getText().toString();
        String senha= Text_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        TelaDeSelecao();
                        }
                    },3000);

                }else {
                    String erro ;

                try{
                    throw  task.getException();

                }catch (Exception e){
                    erro = "Erro ao logar usuario";
                }

                Snackbar snackbar = Snackbar.make(view,erro, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual= FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioAtual != null){
            TelaDeSelecao();

        }
    }

    private void TelaDeSelecao(){
        Intent intent= new Intent(Tela_de_login.this, TeladeCarregamento.class);
        startActivity(intent);
        finish();
    }
    private void IniciarComponetes(){

        text_tela_cadastro = findViewById(R.id.Text_tela_cadastro);
        Text_email= findViewById(R.id.Text_email);
        Text_senha= findViewById(R.id.Text_senha);
        button_entrar= findViewById(R.id.button_entrar);
        progressBar= findViewById(R.id.progressbar);
    }
}




