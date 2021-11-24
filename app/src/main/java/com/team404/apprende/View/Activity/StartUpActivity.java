package com.team404.apprende.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.team404.apprende.R;

public class StartUpActivity extends AppCompatActivity {

    private Button btSignOff;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference BASE_DE_DATOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        initComponent();
    }

    private void initComponent(){
        btSignOff = findViewById(R.id.btSignOff);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();//INICIALIZAMOS INSTANCIA
        BASE_DE_DATOS = firebaseDatabase.getReference("USERS_APP");

        btSignOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    //MÉTODO PARA VALIDAR SI EL USUARIO HA INICIADO SESIÓN
    private void validateLogIn(){
        if(firebaseUser!=null){
            //loadDataUser();
        }else{
            startActivity(new Intent(StartUpActivity.this,LoginActivity.class));
            finish();
        }
    }

    //MÉTODO PARA CERRAR SESIÓN
    private void signOut(){
        firebaseAuth.signOut();
        Toast.makeText(this, "Ha cerrado sesión.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(StartUpActivity.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        validateLogIn();
        super.onStart();
    }

}