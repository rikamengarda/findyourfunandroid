package com.example.marlon.findyourfun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marlon on 10/06/2015.
 */
public class MeuOpenHelper extends SQLiteOpenHelper {
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


    String NOME_BANCO = "db_FindYourFun";
    String NOME_TABELA = "estabelecimento";
    int VERSAO_BANCO = 1;

    String SQL_CREATE_TABLE = "create table estabelecimento" +
            "(" + KEY_ID + " integer primary key autoincrement, "
            + KEY_EST + " text, "
            + KEY_MEDIA + " integer, "
            + KEY_END + " text, "
            + KEY_DESC + " text, "
            + KEY_TEL + " text, "
            + KEY_HORARIO + " text, "
            + KEY_SITE + " text, "
            + KEY_FB + " text, "
            + KEY_INST + " text, "
            + KEY_TT + " text, "
            + KEY_CERVA + " integer, "
            + KEY_DEST + " integer, "
            + KEY_COMIDA + " integer, "
            + KEY_PRECO + " text);";

    MeuOpenHelper (Context context){
        super(context, "db_FindYourFun", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS estabelecimento");
        onCreate(db);
    }
}