package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


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
    private ImageView imgEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estab);


        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("i01", R.drawable.i01);
        map.put("i02", R.drawable.i02);
        map.put("i03", R.drawable.i03);
        map.put("i04", R.drawable.i04);
        map.put("i05", R.drawable.i05);
        map.put("i06", R.drawable.i06);
        map.put("i07", R.drawable.i07);
        map.put("i08", R.drawable.i08);
        map.put("i09", R.drawable.i09);
        map.put("i10", R.drawable.i10);


        Intent it = getIntent();
        Bundle params = it.getExtras();
        int destilado = params.getInt("destilado");
        int comida = params.getInt("comida");
        String posMap = params.getString("imagem");

        txtNome = (TextView) findViewById(R.id.txtNomeEst);
        txtEnd = (TextView) findViewById(R.id.txtEndEst);
        txtSite = (TextView) findViewById(R.id.txtSiteEst);
        txtDesc = (TextView) findViewById(R.id.txtDescEst);
        txtTel = (TextView) findViewById(R.id.txtTelEst);
        txtPrecoBar = (TextView) findViewById(R.id.txtPrecoEstab);
        txtHora = (TextView) findViewById(R.id.txtHoraEst);
        imgDest = (ImageView) findViewById(R.id.imgDestiladoEst);
        imgCom = (ImageView) findViewById(R.id.imgComidaEst);
        imgEst = (ImageView) findViewById(R.id.imgEst);

        txtNome.setText(params.getString("nome"));
        txtEnd.setText(params.getString("endereco"));
        txtSite.setText(params.getString("site"));
        txtDesc.setText(params.getString("descricao"));
        txtHora.setText(params.getString("hora"));
        txtTel.setText(String.valueOf("Telefone: " + params.getString("telefone")));
        txtPrecoBar.setText(String.valueOf("R$: " +params.getString("preco")));
        imgEst.setImageResource(map.get(posMap));

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
