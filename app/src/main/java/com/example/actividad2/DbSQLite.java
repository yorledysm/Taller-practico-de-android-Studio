package com.example.actividad2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbSQLite extends SQLiteOpenHelper {


    public static  final String TABLE_NAME="Usuarios";
    public  static  final String DB_NAME="Clientes";
    public static final  int DB_VERSION=1;

    public DbSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE "+TABLE_NAME+"("+
              "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
              "User  TEXT NOT NULL,"+
              "Contrasena TEXT NOT NULL"+
              ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
     onCreate(db);
    }
}
