package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class cadastro extends Activity {
    private EditText nomeEdt;
    private EditText endEdt;
    private EditText descEdt;
    private EditText telEdt;
    private EditText horEdt;
    private EditText siteEdt;
    private EditText faceEdt;
    private EditText instEdt;
    private EditText twtEdt;
    private CheckBox cervCk;
    private CheckBox destCk;
    private CheckBox comidaCK;
    private int cerveja;
    private int dest;
    private int comida;
    private EditText precEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btCadastrar = (Button) findViewById(R.id.btnCadastroCad);
        Button btEditar = (Button) findViewById(R.id.btnEditarCad);
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
        cervCk = (CheckBox) findViewById(R.id.checkCervejaCad);
        destCk = (CheckBox) findViewById(R.id.checkDestiladosCad);
        comidaCK = (CheckBox) findViewById(R.id.checkComidaCad);

        btCadastrar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (cervCk.isChecked()) {
                    cerveja = 1;
                } else {
                    cerveja = 0;
                }
                if (destCk.isChecked()) {
                    dest = 1;
                } else {
                    dest = 0;
                }

                if (comidaCK.isChecked()) {
                    comida = 1;
                } else {
                    comida = 0;
                }
                salvar(v);
            }

            ;
        });

        btEditar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Teste.class);
                startActivity(it);
            };
        });
    }

    public void salvar (View v){
        BancoDeDados db = new BancoDeDados(this);
        db.abrir();
            db.insereEst(nomeEdt.getText().toString(), endEdt.getText().toString(), descEdt.getText().toString(), telEdt.getText().toString(),
                    horEdt.getText().toString(), siteEdt.getText().toString(), faceEdt.getText().toString(), instEdt.getText().toString(), twtEdt.getText().toString(),
                    cerveja, dest, comida, precEdt.getText().toString());
        db.fechar();
        finish();
    }

}
