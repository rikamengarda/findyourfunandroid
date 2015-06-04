package com.example.marlon.findyourfun;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class cadastro extends Activity {
    private BancoDeDados db;
    Button Cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        db = new BancoDeDados(this);
        Cadastrar = (Button) findViewById(R.id.btnCadastrar);
    }


}
