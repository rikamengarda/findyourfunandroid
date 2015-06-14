package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marlon on 09/06/2015.
 */
public class lista extends Activity {

    private BancoDeDados db;
    private List<Est> estabelecimento = new ArrayList<Est>();
    private EstabelecimentoAdapter estAdapter;
    public ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list = (ListView) findViewById(R.id.list);
        db = new BancoDeDados(this);

        lerDados();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent it = new Intent(getApplicationContext(), estabelecimento.class);
                Bundle params = new Bundle();
                params.putString("nome", estabelecimento.get(position).nome);
                params.putString("endereco", estabelecimento.get(position).endereco);
                params.putString("site", estabelecimento.get(position).site);
                params.putString("descricao", estabelecimento.get(position).descricao);
                params.putString("telefone", estabelecimento.get(position).telefone);
                params.putString("preco", estabelecimento.get(position).preco);
                params.putInt("cerveja", estabelecimento.get(position).cerveja);
                params.putInt("destilado", estabelecimento.get(position).destilado);
                params.putInt("comida", estabelecimento.get(position).comida);
                it.putExtras(params);
               startActivity(it);
            }

        });
    }

    public void lerDados() {
        db.abrir();
        estabelecimento.clear();
        Cursor cursor = db.retornaTodosEst();

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
                estabelecimento.add(a);
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
}
