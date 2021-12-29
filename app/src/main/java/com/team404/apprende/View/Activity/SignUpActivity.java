package com.team404.apprende.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.team404.apprende.R;

public class SignUpActivity extends AppCompatActivity {

    private ImageView btnBackMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initComponent();
    }

    private void initComponent(){
        btnBackMain = findViewById(R.id.btnBackMain);

        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}