package com.example.marlon.findyourfun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marlon on 20/06/2015.
 */
public class ConfigOpenHelper extends SQLiteOpenHelper {
    static String KEY_ID = "_id";
    static String KEY_ALC = "alcance";
    static String KEY_CERVA = "cerva";
    static String KEY_DEST = "dest";
    static String KEY_COMIDA = "comida";

    String SQL_CREATE_TABLE = "create table configuracao" +
            "(" + KEY_ID + " integer primary key autoincrement, "
            + KEY_ALC + " integer, "
            + KEY_CERVA + " integer, "
            + KEY_DEST + " integer, "
            + KEY_COMIDA + " integer);";

    ConfigOpenHelper (Context context){
        super(context, "db_FindYourFun", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS configuracao");
        onCreate(db);
    }
}
