package com.team404.apprende.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team404.apprende.R;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private ImageView btnBackMain;
    private EditText etPersonEmailRegister, etPersonPasswordRegister;
    private ConstraintLayout clBtnRegister;

    FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initComponent();
    }

    private void initComponent(){
        btnBackMain = findViewById(R.id.btnBackMain);
        etPersonEmailRegister = findViewById(R.id.etPersonEmailRegister);
        etPersonPasswordRegister = findViewById(R.id.etPersonPasswordRegister);
        clBtnRegister= findViewById(R.id.clBtnRegister);

        progressDialog = new ProgressDialog(SignUpActivity.this);
        dialog = new Dialog(SignUpActivity.this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etPersonEmailRegister.getText().toString();
                String password = etPersonPasswordRegister.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //CorreoLogin.setError("Correo no válido");
                    //CorreoLogin.setFocusable(true);
                    Toast.makeText(SignUpActivity.this, "Correo no válido", Toast.LENGTH_SHORT).show();
                }else if (password.length()<6){
                    //ContrasenaLogin.setError("Contraseña debe ser mayor o igual a 6");
                    //ContrasenaLogin.setFocusable(true);
                    Toast.makeText(SignUpActivity.this, "Contraseña debe ser mayor o igual a 6", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(email,password);
                }
            }
        });
    }

    //MÉTODO DE REGISTRO DE USUARIO
    private void registerUser(String correo, String contrasena) {
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(correo,contrasena)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*SI EL REGISTRO ES EXITOSO */
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //AQUÍ VAN LOS DATOS A REGISTRAR

                            /* OBTENEMOS LOS TEXTOS DE LOS EDITTEXT */

                            //PARA OBTENER EL UID
                            assert user != null ;
                            String uid = user.getUid();

                            /* CREAMOS HASHMAP PARA ENVIAR DATOS A FIREBASE */
                            HashMap<Object,String> DataUser = new HashMap<>();
                            DataUser.put("uid",uid);
                            DataUser.put("correo",correo);
                            DataUser.put("contrasena",contrasena);

                            //LA IMAGEN DE MOMENTO ESTARÁ VACÍA
                            DataUser.put("imagen","");

                            //INICIALIZAMOS LA INSTANCIA A LA BASE DE DATOS FIREBASE
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            //CREAMOS LA BASE DE DATOS
                            DatabaseReference reference = database.getReference("USERS_APPRENDE");
                            //EL NOMBRE DE LA BASE DE DATOS "NO RELACIONAL ES USERS_APP"
                            reference.child(uid).setValue(DataUser);
                            Toast.makeText(SignUpActivity.this, "Se registró exitosamente", Toast.LENGTH_SHORT).show();
                            //UNA VEZ QUE SE HA REGISTRADO, NOS MANDARÁ AL APARTADO DE INICIO
                            startActivity(new Intent(SignUpActivity.this,StartUpActivity.class));
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Algo ha salido mal.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }




}