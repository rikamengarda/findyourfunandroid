package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Login extends Activity {
    private BD_ESTABELECIMENTO db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new BD_ESTABELECIMENTO(this);
        Button btLogin = (Button)findViewById(R.id.btnLogin);
        Button btFacebook = (Button)findViewById(R.id.btn_Login_facebook);
        Button btCadastro = (Button)findViewById(R.id.btnCadastrar);

        btFacebook.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            };
        });

        btLogin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            };
        });

        btCadastro.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(getApplicationContext(), cadastro.class);
                startActivity(it);
            };
        });
    }

    }
