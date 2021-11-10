package com.team404.apprende.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.team404.apprende.R;

public class LoginActivity extends AppCompatActivity {

    private ImageView btnBackMain;
    private EditText etPersonEmailLogin, etPersonPasswordLogin;
    private ConstraintLayout clBtnLogin;

    private final static int RC_SIGN_IN= 123;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
    }

    private void initComponent(){
        btnBackMain = findViewById(R.id.btnBackMain);
        etPersonEmailLogin = findViewById(R.id.etPersonEmailLogin);
        etPersonPasswordLogin = findViewById(R.id.etPersonPasswordLogin);
        clBtnLogin= findViewById(R.id.clBtnLogin);

        progressDialog = new ProgressDialog(LoginActivity.this);
        dialog = new Dialog(LoginActivity.this);

        firebaseAuth = FirebaseAuth.getInstance();

        clBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailLogin = etPersonEmailLogin.getText().toString();
                String passwordLogin = etPersonPasswordLogin.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()){
                    //CorreoLogin.setError("Correo no válido");
                    //CorreoLogin.setFocusable(true);
                    Toast.makeText(LoginActivity.this, "Correo no válido", Toast.LENGTH_SHORT).show();
                }else if (passwordLogin.length()<6){
                    //ContrasenaLogin.setError("Contraseña debe ser mayor o igual a 6");
                    //ContrasenaLogin.setFocusable(true);
                    Toast.makeText(LoginActivity.this, "Contraseña debe ser mayor o igual a 6", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(emailLogin,passwordLogin);
                }

            }
        });

        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //MÉTODO PARA LOGEAR USUARIO
    private void loginUser(String emailLogin, String passwordLogin) {
        progressDialog.setTitle("Ingresando");
        progressDialog.setMessage("Espere por favor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(emailLogin,passwordLogin)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Si se inicia sesion correctamente
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,StartUpActivity.class));
                            Toast.makeText(LoginActivity.this, "Hola!. Bienvenido "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            progressDialog.dismiss();
                            //REEMPLAZAMOS EL DIALOG POR EL TOAST
                            dialogNoInicio();
                            //Toast.makeText(LoginActivity.this, "Algo ha salido mal.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //que nos muestre el mensaje
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    //CREAMOS EL DIALOG PERSONALIZADO
    private void dialogNoInicio(){
        Button okNoInicio;
        TextView messageTXT;

        dialog.setContentView(R.layout.no_session);

        messageTXT = dialog.findViewById(R.id.messageTXT);
        okNoInicio = dialog.findViewById(R.id.okNoInicio);

        /* FUENTE DE LETRA */
        //SANS-MEDIO
        String ubicacion = "fuentes/sans_medio.ttf";
        Typeface typeface= Typeface.createFromAsset(LoginActivity.this.getAssets(),ubicacion);
        messageTXT.setTypeface(typeface);
        okNoInicio.setTypeface(typeface);

        okNoInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.show();

    }

}