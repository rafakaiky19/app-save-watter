package com.example.projeto2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class TeladeCadastro extends AppCompatActivity {



    private EditText edit_nome,Edit_Email, Edit_senha;
    private Button button_cadastro;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};
    String usuarioID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telade_cadastro);
        button_cadastro = findViewById(R.id.button_cadastro);



          IniciarComponetes();

          button_cadastro.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String nome = edit_nome.getText().toString();
                  String email = Edit_Email.getText().toString();
                  String senha = Edit_senha.getText().toString();

                  if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                      Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                      snackbar.setBackgroundTint(Color.WHITE);
                      snackbar.setTextColor(Color.BLACK);
                      snackbar.show();
                  }else{
                      CadastroUsuario(view);

                      Teladeselecao();
                  }
              }
          });



    }

    private void CadastroUsuario(View v){
        String email = Edit_Email.getText().toString();
        String senha = Edit_senha.getText().toString();
        Task<AuthResult> authResultTask = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    SalvarDadosUsuarios();





                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    String erro;
                    try{
                     throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no mínimo seis caracteres";
                    }catch (FirebaseAuthUserCollisionException e) {
                        erro = "Essa conta ja foi cadastrada";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido";
                    }catch (Exception e){
                        erro = "Erro ao cadastrar usuário";
                    }

                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });




    }

    private void SalvarDadosUsuarios(){
        String nome= edit_nome.getText().toString();

        FirebaseFirestore Db = FirebaseFirestore.getInstance();

        Map<String,Object> Usuarios = new HashMap<>();
        Usuarios.put("nome", nome);

        usuarioID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference= Db.collection("Usuarios").document(usuarioID);
        documentReference.set(Usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os dados" );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                  Log.d("db_erro", "Erro ao salvar dados" + e.toString());
            }
        });


    }
    private void IniciarComponetes(){
        edit_nome= findViewById(R.id.edit_nome);
        Edit_Email=findViewById(R.id.Edit_Email);
        Edit_senha =  findViewById(R.id.Edit_senha);
        button_cadastro= findViewById(R.id.button_cadastro);

    }

    private void Teladeselecao(){
        Intent intent= new Intent(TeladeCadastro.this, TeladeCarregamento.class);
        startActivity(intent);
        finish();
    }
}