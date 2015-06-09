package com.example.marlon.findyourfun;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lista);

        list = (ListView) findViewById(R.id.list);
        bdEstabelecimento = new BD_ESTABELECIMENTO(this);
        try {
            lerDados();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lerDados() throws SQLException {
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
    }
}
