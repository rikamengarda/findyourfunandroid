package com.example.marlon.findyourfun;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

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
    BancoConfig db = new BancoConfig(this);
    private List<Configuracoes> configs = new ArrayList<Configuracoes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        SeekBar skRange =(SeekBar)findViewById(R.id.skbAlcance);
        skRange.setOnSeekBarChangeListener(skRangeListener);
        txtKms = (TextView)findViewById(R.id.txtValorKM);

        cervejaCk = (CheckBox) findViewById(R.id.checkCerveja);
        destilaCk = (CheckBox) findViewById(R.id.checkDestilados);
        comCK = (CheckBox) findViewById(R.id.checkComida);

        lerDados();

        skRange.setProgress(Integer.parseInt(configs.get(0).alc));
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
                salvar(configs);
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
                a.alc = cursor.getString(cursor.getColumnIndex(db.KEY_ALC));
                a.cerveja = cursor.getInt(cursor.getColumnIndex(db.KEY_CERVA));
                a.destilado = cursor.getInt(cursor.getColumnIndex(db.KEY_DEST));
                a.comida = cursor.getInt(cursor.getColumnIndex(db.KEY_COMIDA));
                configs.add(a);
            } while (cursor.moveToNext());
        db.fechar();
    }

    public void salvar (List<Configuracoes> list){
        db.abrir();
        db.atualizaConfig(list.get(0).id, list.get(0).alc, list.get(0).cerveja, list.get(0).destilado, list.get(0).comida);
        db.fechar();
    }

}
