package com.example.marlon.findyourfun;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 08/06/2015.
 */
public class Lista extends Activity {
    private BD_ESTABELECIMENTO bdEstabelecimento;
    private List<Est> estab = new ArrayList<Est>();
    public ListView list;
    private EST_ADAPTER estAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lista);

        list = (ListView) findViewById(R.id.list);
        bdEstabelecimento = new BD_ESTABELECIMENTO(this);

        lerDados();
    }

    public void lerDados() {
        bdEstabelecimento.abrir();
        estab.clear();
        Cursor cursor = bdEstabelecimento.retornaTodosEst();

        if (cursor.moveToFirst())
            do {
                Est a = new Est();
                a.id = cursor.getInt(cursor.getColumnIndex(bdEstabelecimento.KEY_ID));
                a.nome = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_EST));
                a.media = cursor.getDouble(cursor.getColumnIndex(bdEstabelecimento.KEY_MEDIA));
                a.endereco = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_END));
                a.descricao = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_DESC));
                a.telefone = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_TEL));
                a.horario = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_HORARIO));
                a.site = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_SITE));
                a.facebook = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_FB));
                a.instagram = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_INST));
                a.twitter = cursor.getString(cursor.getColumnIndex(bdEstabelecimento.KEY_TWITTER));
                a.cerveja = cursor.getInt(cursor.getColumnIndex(bdEstabelecimento.KEY_CERVEJA));
                a.destilado = cursor.getInt(cursor.getColumnIndex(bdEstabelecimento.KEY_DESTILADO));
                a.comida = cursor.getInt(cursor.getColumnIndex(bdEstabelecimento.KEY_COMIDA));
                a.preco = cursor.getDouble(cursor.getColumnIndex(bdEstabelecimento.KEY_PRECO));
                estab.add(a);
            } while (cursor.moveToNext());

        if(estab.size() > 0){
            if(estAdapter == null){
                estAdapter = new EST_ADAPTER(this, estab) {
                    @Override
                    public void edita(Est est) {
                        Intent intent = new Intent(getApplicationContext(),cadastro.class);
                        intent.putExtra("estabelecimento", est);

                        startActivity(intent);
                    }

                    @Override
                    public void deleta(Est est) {
                        bdEstabelecimento.abrir();
                        bdEstabelecimento.apagaEst(est.id);
                        bdEstabelecimento.fechar();
                        lerDados();
                        estAdapter.notifyDataSetChanged();
                    }
                };

                list.setAdapter(estAdapter);
            } else
                estAdapter.novosDados(estab);
            //artigosAdapter.notifyDataSetChanged();
        }
        bdEstabelecimento.fechar();
    }
}
