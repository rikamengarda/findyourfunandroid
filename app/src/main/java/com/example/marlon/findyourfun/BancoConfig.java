package com.example.marlon.findyourfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Marlon on 20/06/2015.
 */
public class BancoConfig {
    static String KEY_ID = "_id";
    static String KEY_ALC = "alcance";
    static String KEY_CERVA = "cerva";
    static String KEY_DEST = "dest";
    static String KEY_COMIDA = "comida";

    String NOME_TABELA = "configuracao";
    final Context context;
    ConfigOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoConfig(Context ctx){
    this.context = ctx;
    openHelper = new ConfigOpenHelper(context);
}

    public BancoConfig abrir(){
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        openHelper.close();
    }

    public long insereConfig(int alc, int cerveja, int destilado, int comida){
        ContentValues campos = new ContentValues();
        campos.put(KEY_ALC, alc);
        campos.put(KEY_CERVA, cerveja);
        campos.put(KEY_DEST, destilado);
        campos.put(KEY_COMIDA, comida);
        return db.insert(NOME_TABELA, null, campos);
    }

    public Cursor retornaTodosConfig(){
        return db.query(NOME_TABELA, new String[]{KEY_ID, KEY_ALC, KEY_CERVA, KEY_DEST, KEY_COMIDA}, null, null, null, null, null);
    }

    public boolean atualizaConfig(long id, int alc, int cerveja, int destilado, int comida){
        ContentValues args = new ContentValues();
        args.put(KEY_ALC, alc);
        args.put(KEY_CERVA, cerveja);
        args.put(KEY_DEST, destilado);
        args.put(KEY_COMIDA, comida);

        // Retorna se houve registro alterado ou nao
        return db.update(NOME_TABELA, args, KEY_ID + "=" + id, null) > 0;
    }
}
