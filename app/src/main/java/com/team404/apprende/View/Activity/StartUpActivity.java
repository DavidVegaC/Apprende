package com.team404.apprende.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team404.apprende.R;

public class StartUpActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout llJuegos;
    private ImageView btSignOff;
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
        llJuegos = findViewById(R.id.llJuegos);
        llJuegos.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llJuegos:
                    startActivity(new Intent(StartUpActivity.this,JuegosActivity.class));
                break;
            default:
                break;
        }
    }
}