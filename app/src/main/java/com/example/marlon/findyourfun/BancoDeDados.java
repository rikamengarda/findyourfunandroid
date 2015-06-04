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
public class BancoDeDados {
    static String KEY_ID = "_id";
    static String KEY_LOGIN = "login";
    static String KEY_PASS = "password";

    String NOME_BANCO = "db_FindYourFun";
    String NOME_TABELA = "usuario";
    int VERSAO_BANCO = 1;
    String SQL_CREATE_TABLE = "create table usuario" +
            "(" + KEY_ID + "integer primary key autoincrement,"
            + KEY_LOGIN + "text not null, "
            + KEY_PASS + "text not null);";

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context ctx){
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
            db.execSQL("DROP TABLE IF EXISTS usuario");
            onCreate(db);
        }
    }

    public BancoDeDados abrir() throws SQLException{
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        openHelper.close();
    }

    public long insereusuario(String login, String password){
        ContentValues campos = new ContentValues();
        campos.put(KEY_LOGIN, login);
        campos.put(KEY_PASS, password);
        return db.insert(NOME_TABELA, null, campos);
    }

    public boolean apagaUsuario(long id){
        return db.delete(NOME_TABELA, KEY_ID + "=" + id, null)>0;
    }

    public Cursor retornaTodosUsuarios(){
        return db.query(NOME_TABELA, new String[]{KEY_ID, KEY_LOGIN, KEY_PASS}, null, null, null, null, null);
    }

    public boolean atualizaUsuario(long id, String login, String password){
        ContentValues args = new ContentValues();
        args.put(KEY_LOGIN, login);
        args.put(KEY_PASS, password);
        // Retorna se houve registro alterado ou nao
        return db.update(NOME_TABELA, args, KEY_ID + "=" + id, null) > 0;
    }

}
