package com.example.projeto2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Perfil extends AppCompatActivity {

    private TextView Text_nome_usuario, Text_email;
    private Button button_deslogar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String usuarioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        iniciarComponentes();

        button_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(Perfil.this, Tela_de_login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID=FirebaseAuth.getInstance().getCurrentUser().getUid();


        DocumentReference documentReference= db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                   if (documentSnapshot != null){
                       Text_nome_usuario.setText(documentSnapshot.getString("nome"));
                       Text_email.setText(email);
                   }
            }
        });



    }

    private void  iniciarComponentes(){
        Text_nome_usuario= findViewById(R.id.Text_nome_usurio);
        Text_email= findViewById(R.id.Text_email);
        button_deslogar= findViewById(R.id.button_deslogar);
    }
}