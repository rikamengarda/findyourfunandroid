package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marlon on 09/06/2015.
 */
public class lista extends Activity {

    private BancoDeDados db;
    private BancoConfig dbC;

    private List<Est> estabelecimento = new ArrayList<Est>();
    private List<Configuracoes> configs = new ArrayList<Configuracoes>();

    private EstabelecimentoAdapter estAdapter;
    public ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list = (ListView) findViewById(R.id.list);
        db = new BancoDeDados(this);
        dbC = new BancoConfig(this);

        lerDados();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent it = new Intent(getApplicationContext(), Estab.class);
                Bundle params = new Bundle();
                params.putString("nome", estabelecimento.get(position).nome);
                params.putString("endereco", estabelecimento.get(position).endereco);
                params.putString("site", estabelecimento.get(position).site);
                params.putString("descricao", estabelecimento.get(position).descricao);
                params.putString("telefone", estabelecimento.get(position).telefone);
                params.putString("preco", estabelecimento.get(position).preco);
                params.putString("hora", estabelecimento.get(position).horario);
                params.putInt("destilado", estabelecimento.get(position).destilado);
                params.putInt("comida", estabelecimento.get(position).comida);
                params.putString("imagem", estabelecimento.get(position).imgBar);
                it.putExtras(params);
               startActivity(it);
            }

        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        lerDados();
    }

    public void lerDados() {
        db.abrir();
        dbC.abrir();

        estabelecimento.clear();
        configs.clear();

        Cursor cursor = db.retornaTodosEst();
        Cursor cursorC = dbC.retornaTodosConfig();

        Configuracoes c = new Configuracoes();
        cursorC.moveToFirst();
        c.alc = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_ALC));
        c.comida = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_COMIDA));
        c.destilado = cursorC.getInt(cursorC.getColumnIndex(dbC.KEY_DEST));
        if (cursor.moveToFirst())
            do {
                Est a = new Est();
                a.id = cursor.getInt(cursor.getColumnIndex(db.KEY_ID));
                a.nome = cursor.getString(cursor.getColumnIndex(db.KEY_EST));
                a.media = cursor.getDouble(cursor.getColumnIndex(db.KEY_MEDIA));
                a.endereco = cursor.getString(cursor.getColumnIndex(db.KEY_END));
                a.descricao = cursor.getString(cursor.getColumnIndex(db.KEY_DESC));
                a.telefone = cursor.getString(cursor.getColumnIndex(db.KEY_TEL));
                a.horario = cursor.getString(cursor.getColumnIndex(db.KEY_HORARIO));
                a.site = cursor.getString(cursor.getColumnIndex(db.KEY_SITE));
                a.facebook = cursor.getString(cursor.getColumnIndex(db.KEY_FB));
                a.instagram = cursor.getString(cursor.getColumnIndex(db.KEY_INST));
                a.twitter = cursor.getString(cursor.getColumnIndex(db.KEY_TT));
                a.cerveja = cursor.getInt(cursor.getColumnIndex(db.KEY_CERVA));
                a.destilado = cursor.getInt(cursor.getColumnIndex(db.KEY_DEST));
                a.comida = cursor.getInt(cursor.getColumnIndex(db.KEY_COMIDA));
                a.preco = cursor.getString(cursor.getColumnIndex(db.KEY_PRECO));
                a.imgBar = cursor.getString(cursor.getColumnIndex(db.KEY_IMG));

               if (verifica_distancia(c.alc, a.endereco)){
                   if(verifica_tipo(a.comida, a.destilado, c.comida, c.destilado)){
                       estabelecimento.add(a);
                   }
               }
            } while (cursor.moveToNext());

        if(estabelecimento.size() > 0){
            if(estAdapter == null){
                estAdapter = new EstabelecimentoAdapter(this, estabelecimento);
                list.setAdapter(estAdapter);
            } else{
                estAdapter.novosDados(estabelecimento);
            }
        }
        db.fechar();
    }

    private boolean verifica_distancia(int dist, String end){
        float [] distancia = new float [2];
        float calc;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);


        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        double latitude = myLocation.getLatitude();
        double longitude = myLocation.getLongitude();

        try {
            Geocoder coder = new Geocoder(this);
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(end, 1);
            for(Address add : adresses) {
                double destlongitude = add.getLongitude();
                double destlatitude = add.getLatitude();
                myLocation.distanceBetween(latitude, longitude, destlatitude,destlongitude , distancia);
            }
        } catch (IOException e) {
              e.printStackTrace();
          }

        calc = distancia[0]/1000;

        if(dist >= (int)calc){
            return true;
        }else{
            return false;
        }

    }

    private boolean verifica_tipo(int comida, int dest, int prefComida, int prefDest){
        if(prefComida == 1 && prefDest == 1){
            if(comida == prefComida) {
                if (dest == prefDest) {
                    return true;
                }
            }
        }else{
            if(comida == prefComida) {
                return true;
            }
            if(dest == prefDest){
                return true;
            }
        }
        return false;
    }
}
