package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class config extends Activity {

    private int kms;
    private TextView txtKms;
    private CheckBox cervejaCk;
    private CheckBox destilaCk;
    private CheckBox comCK;
    private int cerv;
    private int destila;
    private int com;
    private boolean destilad;
    private boolean comid;

    BancoConfig db = new BancoConfig(this);
    private List<Configuracoes> configs = new ArrayList<Configuracoes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        lerDados();

        if (configs.get(0).destilado == 0) {
            destilad = false;
        } else {
            destilad = true;
        }

        if (configs.get(0).comida == 0) {
            comid = false;
        } else {
            comid = true;
        }

        SeekBar skRange =(SeekBar)findViewById(R.id.skbAlcance);
        skRange.setOnSeekBarChangeListener(skRangeListener);
        txtKms = (TextView)findViewById(R.id.txtValorKM);

        cervejaCk = (CheckBox) findViewById(R.id.checkCerveja);
        destilaCk = (CheckBox) findViewById(R.id.checkDestilados);
        comCK = (CheckBox) findViewById(R.id.checkComida);

        skRange.setProgress(configs.get(0).alc);
        destilaCk.setChecked(destilad);
        comCK.setChecked(comid);
        kms = skRange.getProgress();

        Button btSalvar = (Button) findViewById(R.id.btnSalvarConf);

        btSalvar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (cervejaCk.isChecked()) {
                    cerv = 1;
                } else {
                    cerv = 0;
                }
                if (destilaCk.isChecked()) {
                    destila = 1;
                } else {
                    destila = 0;
                }

                if (comCK.isChecked()) {
                    com = 1;
                } else {
                    com = 0;
                }


                salvar(kms, cerv, destila, com);
            }

            ;
        });
    }

    private SeekBar.OnSeekBarChangeListener skRangeListener = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
            kms = progress;
            updateKms();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar){

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar){

        }
    };

    private void updateKms(){
        txtKms.setText(String.valueOf(kms));
    }

    public void lerDados() {
        db.abrir();
        configs.clear();
        Cursor cursor = db.retornaTodosConfig();

        if (cursor.moveToFirst())
            do {
                Configuracoes a = new Configuracoes();
                a.id = cursor.getInt(cursor.getColumnIndex(db.KEY_ID));
                a.alc = cursor.getInt(cursor.getColumnIndex(db.KEY_ALC));
                a.cerveja = cursor.getInt(cursor.getColumnIndex(db.KEY_CERVA));
                a.destilado = cursor.getInt(cursor.getColumnIndex(db.KEY_DEST));
                a.comida = cursor.getInt(cursor.getColumnIndex(db.KEY_COMIDA));
                configs.add(a);
            } while (cursor.moveToNext());
        db.fechar();
    }

    public void salvar (int alcance, int cerve, int desti, int comi){
        db.abrir();
        db.atualizaConfig(1, alcance, cerve, desti, comi);
        db.fechar();

        Context context = getApplicationContext();
        CharSequence text = "Configurações Salvas!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
