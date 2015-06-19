package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Estab extends Activity {
    private ImageView imgDest;
    private ImageView imgCom;
    private TextView txtNome;
    private TextView txtEnd;
    private TextView txtSite;
    private TextView txtDesc;
    private TextView txtTel;
    private TextView txtPrecoBar;
    private TextView txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estab);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int destilado = params.getInt("destilado");
        int comida = params.getInt("comida");

        txtNome = (TextView) findViewById(R.id.txtNomeEst);
        txtEnd = (TextView) findViewById(R.id.txtEndEst);
        txtSite = (TextView) findViewById(R.id.txtSiteEst);
        txtDesc = (TextView) findViewById(R.id.txtDescEst);
        txtTel = (TextView) findViewById(R.id.txtTelEst);
        txtPrecoBar = (TextView) findViewById(R.id.txtPrecoEstab);
        txtHora = (TextView) findViewById(R.id.txtHoraEst);
        imgDest = (ImageView) findViewById(R.id.imgDestiladoEst);
        imgCom = (ImageView) findViewById(R.id.imgComidaEst);

        txtNome.setText(params.getString("nome"));
        txtEnd.setText(params.getString("endereco"));
        txtSite.setText(params.getString("site"));
        txtDesc.setText(params.getString("descricao"));
        txtHora.setText(params.getString("hora"));
        txtTel.setText(String.valueOf("Telefone: " + params.getString("telefone")));
        txtPrecoBar.setText(String.valueOf("R$: " +params.getString("preco")));

        if(destilado == 1){
            imgDest.setVisibility(View.VISIBLE);
        }
        if(comida == 1){
            imgCom.setVisibility(View.VISIBLE);
        }

        ImageButton btnMapa = (ImageButton) findViewById(R.id.imgMaps);

        btnMapa.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(getApplicationContext(), MapsActivity.class);
                it.putExtra("end", txtEnd.getText());
                it.putExtra("nome", txtNome.getText());
                it.putExtra("tel", txtTel.getText());
                startActivity(it);
            };
        });
    }

}
