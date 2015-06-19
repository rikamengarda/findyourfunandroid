package com.example.marlon.findyourfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Marlon on 11/06/2015.
 */
public class BancoDeDados {
    static String KEY_ID = "_id";
    static String KEY_EST = "est";
    static String KEY_MEDIA = "media";
    static String KEY_END = "end";
    static String KEY_DESC = "desc";
    static String KEY_TEL = "tel";
    static String KEY_HORARIO = "horario";
    static String KEY_SITE = "site";
    static String KEY_FB = "fb";
    static String KEY_INST = "inst";
    static String KEY_TT = "tt";
    static String KEY_CERVA = "cerva";
    static String KEY_DEST = "dest";
    static String KEY_COMIDA = "comida";
    static String KEY_PRECO = "preco";
    static String KEY_IMG = "img";

    String NOME_TABELA = "estabelecimento";

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context ctx){
        this.context = ctx;
        openHelper = new MeuOpenHelper(context);
    }

    public BancoDeDados abrir(){
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        openHelper.close();
    }

    public long insereEst(String est, String endereco, String descricao, String telefone, String horario, String site,
                          String fb, String inst, String tw, int cerveja, int destilado, int comida, String preco, String img){
        ContentValues campos = new ContentValues();
        campos.put(KEY_EST, est);
        campos.put(KEY_END, endereco);
        campos.put(KEY_DESC, descricao);
        campos.put(KEY_TEL, telefone);
        campos.put(KEY_HORARIO, horario);
        campos.put(KEY_SITE, site);
        campos.put(KEY_FB, fb);
        campos.put(KEY_INST, inst);
        campos.put(KEY_TT, tw);
        campos.put(KEY_CERVA, cerveja);
        campos.put(KEY_DEST, destilado);
        campos.put(KEY_COMIDA, comida);
        campos.put(KEY_PRECO, preco);
        campos.put(KEY_IMG, img);
        return db.insert(NOME_TABELA, null, campos);
    }

    public boolean apagaEst(long id){
        return db.delete(NOME_TABELA, KEY_ID + "=" + id, null)>0;
    }

    public Cursor retornaTodosEst(){
        return db.query(NOME_TABELA, new String[]{KEY_ID, KEY_EST, KEY_MEDIA, KEY_END, KEY_DESC, KEY_TEL, KEY_HORARIO, KEY_SITE
                , KEY_FB, KEY_INST, KEY_TT, KEY_CERVA, KEY_DEST, KEY_COMIDA, KEY_PRECO, KEY_IMG}, null, null, null, null, null);
    }

    public boolean atualizaEst(long id, String est, String media, String endereco, String descricao, String telefone, String horario, String site,
                               String fb, String inst, String tw, int cerveja, int destilado, int comida, String preco, String img){
        ContentValues args = new ContentValues();
        args.put(KEY_EST, est);
        args.put(KEY_MEDIA, media);
        args.put(KEY_END, endereco);
        args.put(KEY_DESC, descricao);
        args.put(KEY_TEL, telefone);
        args.put(KEY_HORARIO, horario);
        args.put(KEY_SITE, site);
        args.put(KEY_FB, fb);
        args.put(KEY_INST, inst);
        args.put(KEY_TT, tw);
        args.put(KEY_CERVA, cerveja);
        args.put(KEY_DEST, destilado);
        args.put(KEY_COMIDA, comida);
        args.put(KEY_PRECO, preco);
        args.put(KEY_IMG, img);

        // Retorna se houve registro alterado ou nao
        return db.update(NOME_TABELA, args, KEY_ID + "=" + id, null) > 0;
    }

}
