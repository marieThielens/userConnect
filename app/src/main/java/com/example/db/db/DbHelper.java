package com.example.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.db.models.Personne;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        // je prends les infos du parent (dbBinfo)
        super(context, DbInfo.DB_NOM, null, DbInfo.DB_VERSION);
    }

    @Override
    // Création de la db
    public void onCreate(SQLiteDatabase db) { //SQLiteOpenHelper classe qui prend en charge l'interaction avec le db
       db.execSQL(DbInfo.TableauPersonne.REQUEST_CREATE); // ma requete pour créer une table dans DbInfo
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbInfo.TableauPersonne.REQUEST_DELETE); // on delete
        onCreate(db); // on recrée
    }


}
