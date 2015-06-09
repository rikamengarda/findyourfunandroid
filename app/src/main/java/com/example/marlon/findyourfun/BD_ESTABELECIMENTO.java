package com.example.marlon.findyourfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Ricardo on 04/06/2015.
 */
public class BD_ESTABELECIMENTO {
    static String KEY_ID = "_id";
    static String KEY_EST = "est";
    static String KEY_MEDIA = "MED_NOTA";
    static String KEY_END = "END";
    static String KEY_DESC = "DESC";
    static String KEY_TEL = "TEL";
    static String KEY_HORARIO = "HORARIO";
    static String KEY_SITE = "SITE";
    static String KEY_FB = "FB";
    static String KEY_INST = "INST";
    static String KEY_TWITTER = "TWITTER";
    static String KEY_CERVEJA = "CERVEJA";
    static String KEY_DESTILADO = "DESTILADO";
    static String KEY_COMIDA = "COMIDA";
    static String KEY_PRECO = "PREC";


    String NOME_BANCO = "db_FindYourFun";
    String NOME_TABELA = "estabelecimento";
    int VERSAO_BANCO = 1;
    String SQL_CREATE_TABLE = "create table estabelecimento" +
            "(" + KEY_ID + "integer primary key autoincrement,"
            + KEY_EST + "text not null, "
            + KEY_MEDIA + "double null, "
            + KEY_END + "text not null, "
            + KEY_DESC + "text not null, "
            + KEY_TEL + "text not null, "
            + KEY_HORARIO + "text not null, "
            + KEY_SITE + "text null, "
            + KEY_FB + "text null, "
            + KEY_INST + "text null, "
            + KEY_TWITTER + "text null, "
            + KEY_CERVEJA + "int not null, "
            + KEY_DESTILADO + "int null, "
            + KEY_COMIDA + "int null, "
            + KEY_PRECO + "double not null);";

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BD_ESTABELECIMENTO(Context ctx){
        this.context = ctx;
        openHelper = new MeuOpenHelper(context);
    }

    public class MeuOpenHelper extends SQLiteOpenHelper{
        MeuOpenHelper(Context context){
            super(context, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        //Banco for criado a primeira vez
        public void onCreate(SQLiteDatabase db){
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        //Quando temos uma nova versao do app ou do BD
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS estabelecimento");
            onCreate(db);
        }
    }

    public BD_ESTABELECIMENTO abrir(){
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        openHelper.close();
    }

    public long insereEst(String est, String endereco, String descricao, String telefone, String horario, String site,
                          String fb, String inst, String tw, int cerveja, int destilado, int comida, double preco){
        ContentValues campos = new ContentValues();
        campos.put(KEY_EST, est);
        campos.put(KEY_END, endereco);
        campos.put(KEY_DESC, descricao);
        campos.put(KEY_TEL, telefone);
        campos.put(KEY_HORARIO, horario);
        campos.put(KEY_SITE, site);
        campos.put(KEY_FB, fb);
        campos.put(KEY_INST, inst);
        campos.put(KEY_TWITTER, tw);
        campos.put(KEY_CERVEJA, cerveja);
        campos.put(KEY_DESTILADO, destilado);
        campos.put(KEY_COMIDA, comida);
        campos.put(KEY_PRECO, preco);
        return db.insert(NOME_TABELA, null, campos);
    }

    public boolean apagaEst(long id){
        return db.delete(NOME_TABELA, KEY_ID + "=" + id, null)>0;
    }

    public Cursor retornaTodosEst(){
        return db.query(NOME_TABELA, new String[]{KEY_ID, KEY_EST, KEY_MEDIA, KEY_END, KEY_DESC, KEY_TEL, KEY_HORARIO, KEY_SITE
                , KEY_FB, KEY_INST, KEY_TWITTER, KEY_CERVEJA, KEY_DESTILADO, KEY_COMIDA, KEY_PRECO}, null, null, null, null, null);
    }

    public boolean atualizaEst(long id, String est, String media, String endereco, String descricao, String telefone, String horario, String site,
                               String fb, String inst, String tw, int cerveja, int destilado, int comida, double preco){
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
        args.put(KEY_TWITTER, tw);
        args.put(KEY_CERVEJA, cerveja);
        args.put(KEY_DESTILADO, destilado);
        args.put(KEY_COMIDA, comida);
        args.put(KEY_PRECO, preco);
        // Retorna se houve registro alterado ou nao
        return db.update(NOME_TABELA, args, KEY_ID + "=" + id, null) > 0;
    }

}
