package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class cadastro extends Activity {
    private BD_ESTABELECIMENTO db;
    private Est estabelecimento;
    private EditText nomeEdt;
    private EditText endEdt;
    private EditText descEdt;
    private EditText telEdt;
    private EditText horEdt;
    private EditText siteEdt;
    private EditText faceEdt;
    private EditText instEdt;
    private EditText twtEdt;
    private int cerveja;
    private int dest;
    private int comida;
    private EditText precEdt;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        db = new BD_ESTABELECIMENTO(this);
        btCadastrar = (Button) findViewById(R.id.btnCadastrar);

        nomeEdt = (EditText) findViewById(R.id.editNomeCad);
        endEdt = (EditText) findViewById(R.id.editEndCad);
        descEdt = (EditText) findViewById(R.id.ediDescCad);
        telEdt = (EditText) findViewById(R.id.ediTelCad);
        horEdt = (EditText) findViewById(R.id.ediHorlCad);
        siteEdt = (EditText) findViewById(R.id.ediSitelCad);
        faceEdt = (EditText) findViewById(R.id.ediFaceCad);
        instEdt = (EditText) findViewById(R.id.editInsCad);
        twtEdt = (EditText) findViewById(R.id.ediTweetCad);
        precEdt = (EditText) findViewById(R.id.editChoppCad);
        cerveja = Integer.parseInt(String.valueOf(findViewById(R.id.checkCervejaCad)));
        dest = Integer.parseInt(String.valueOf(findViewById(R.id.checkDestiladosCad)));
        comida = Integer.parseInt(String.valueOf(findViewById(R.id.checkComidaCad)));

        btCadastrar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                salvar(v);
            };
        });
    }

    public void salvar (View v){
        db.abrir();
            db.insereEst(nomeEdt.getText().toString(), endEdt.getText().toString(), descEdt.getText().toString(), telEdt.getText().toString(),
                    horEdt.getText().toString(), siteEdt.getText().toString(), faceEdt.getText().toString(), instEdt.getText().toString(), twtEdt.getText().toString(),
                    cerveja, dest, comida,Double.parseDouble(precEdt.getText().toString()));
        db.fechar();
        finish();
    }

}
