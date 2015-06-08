package com.example.marlon.findyourfun;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class cadastro extends Activity {
    private BD_ESTABELECIMENTO db;
    Button Cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        db = new BD_ESTABELECIMENTO(this);
        Cadastrar = (Button) findViewById(R.id.btnCadastrar);
    }


}
