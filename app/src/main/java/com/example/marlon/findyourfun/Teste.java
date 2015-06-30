package com.example.marlon.findyourfun;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Teste extends Activity {

    private BancoDeDados db;
    private List<Est> estabelecimento = new ArrayList<Est>();
    private TesteAdapter estAdapter;
    public ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list = (ListView) findViewById(R.id.list);
        db = new BancoDeDados(this);

        lerDados();
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
                estAdapter = new TesteAdapter(this, estabelecimento){
                   @Override
                    public void deleta(Est estab) {
                        db.abrir();
                        db.apagaEst(estab.id);
                        db.fechar();
                        lerDados();
                        estAdapter.notifyDataSetChanged();
                    }
                };
                list.setAdapter(estAdapter);
            } else{
                estAdapter.novosDados(estabelecimento);
            }
        }
        db.fechar();
    }

}
