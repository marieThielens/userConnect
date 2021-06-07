package com.example.db.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.db.DbHelper;
import com.example.db.db.DbInfo;
import com.example.db.models.Personne;

public class PersonneDao {

    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    // Constructeur
    public PersonneDao(Context context) {
        this.context = context;
    }

    // Rendre la db ecrivable....................
    public PersonneDao ecrireDb() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Rendre la db lisable
    public PersonneDao lireDb() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    // Fermer la db
    public void close() {
        db.close();
        dbHelper.close();
    }

    // Méthode CRUD , convertir le curseur
    private Personne convertirCursor(Cursor c) {
        long id = c.getLong(c.getColumnIndex(DbInfo.TableauPersonne.COLUMN_ID));
        String name = c.getString(c.getColumnIndex(DbInfo.TableauPersonne.COLUMN_NOM));
        String mdp = c.getString(c.getColumnIndex(D))
    }

    // Ajouter un personne. ContentValues crée comme un tableau ?
    public ContentValues creerPersonne(Personne personne) { // (models Personne)

        // Crée un ensemble vide de valeurs en utilisant la taille initiale par défaut
        ContentValues valeurs = new ContentValues();
        // Ajouter mes valeurs
        valeurs.put(DbInfo.TableauPersonne.COLUMN_NOM, personne.getNom());
        valeurs.put(DbInfo.TableauPersonne.COLUMN_MDP, personne.getMdp());

        return valeurs;
    }

    // insérer personne
    public long insererPersonne(Personne personne) {
        ContentValues valeurs = creerPersonne(personne);
        // Insérer mes lignes
        return db.insert(DbInfo.TableauPersonne.TABLE_NOM, null, valeurs);
    }

    // Voir Une seule personne. (Personne est ma classe models)
    public Personne avoirDepuisId(long id) {
        Cursor cursor = db.query(DbInfo.TableauPersonne.TABLE_NOM,
                null, // toutes les colonnes
                DbInfo.TableauPersonne.COLUMN_ID + "=?", // section
                new String[]{ String.valueOf(id) },
                null, null, null); // groupby, having, orderBy

        if(cursor.getCount() == 0) {
            return null;
        }
        // va sur la 1ere ligne
        cursor.moveToFirst();
        // Renvoie la ligne
        return
    }

}
