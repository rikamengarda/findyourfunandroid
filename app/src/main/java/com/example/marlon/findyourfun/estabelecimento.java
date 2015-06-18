package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class estabelecimento extends Activity {
    private TextView txtNome;
    private TextView txtEnd;
    private TextView txtSite;
    private TextView txtDesc;
    private TextView txtTel;
    private TextView txtPrecoBar;
    private ImageView imgCer;
    private ImageView imgDest;
    private ImageView imgCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int destilado = params.getInt("destilado");
        int comida = params.getInt("comida");

        txtNome = (TextView) findViewById(R.id.txtNomeBar);
        txtEnd = (TextView) findViewById(R.id.txtEndereco);
        txtSite = (TextView) findViewById(R.id.txtSiteBar);
        txtDesc = (TextView) findViewById(R.id.txtDescBar);
        txtTel = (TextView) findViewById(R.id.txtTelefone);
        txtPrecoBar = (TextView) findViewById(R.id.txtPrecoBar);
        imgCer = (ImageView) findViewById(R.id.imgCerveja);
        imgDest = (ImageView) findViewById(R.id.imgDestilado);
        imgCom = (ImageView) findViewById(R.id.imgComida);

        txtNome.setText(params.getString("nome"));
        txtEnd.setText(params.getString("endereco"));
        txtSite.setText(params.getString("site"));
        txtDesc.setText(params.getString("descricao"));
        txtTel.setText(String.valueOf("Telefone: " + params.getString("telefone")));
        txtPrecoBar.setText(String.valueOf("R$: " +params.getString("preco")));

        if(destilado == 1){
            imgDest.setVisibility(View.VISIBLE);
        }
        if(comida == 1){
            imgCom.setVisibility(View.VISIBLE);
        }

        Button btnMapa = (Button) findViewById(R.id.btnMapa);

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
