package com.example.db.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.db.DbHelper;
import com.example.db.db.DbInfo;

import java.util.ArrayList;
import java.util.List;

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
    private com.example.db.models.Personne convertirCursor(Cursor c) {
        long id = c.getLong(c.getColumnIndex(DbInfo.Personne.COLUMN_ID));
        String nom = c.getString(c.getColumnIndex(DbInfo.Personne.COLUMN_NOM));
        String mdp = c.getString(c.getColumnIndex(DbInfo.Personne.COLUMN_MDP));

        return new com.example.db.models.Personne(id, nom, mdp);
    }

    // Ajouter un personne. ContentValues crée comme un tableau ?
    public ContentValues creerPersonne(com.example.db.models.Personne personne) { // (models Personne)

        // Crée un ensemble vide de valeurs en utilisant la taille initiale par défaut
        ContentValues valeurs = new ContentValues();
        // Ajouter mes valeurs
        valeurs.put(DbInfo.Personne.COLUMN_NOM, personne.getNom());
        valeurs.put(DbInfo.Personne.COLUMN_MDP, personne.getMdp());

        return valeurs;
    }

    // insérer personne
    public long insererPersonne(com.example.db.models.Personne personne) {
        ContentValues valeurs = creerPersonne(personne);
        // Insérer mes lignes
        return db.insert(DbInfo.Personne.TABLE_NOM, null, valeurs);
    }

    // Voir Une seule personne. (Personne est ma classe models)
    public com.example.db.models.Personne avoirDepuisId(long id) {
        Cursor cursor = db.query(DbInfo.Personne.TABLE_NOM,
                null, // toutes les colonnes
                DbInfo.Personne.COLUMN_ID + "=?", // section
                new String[]{ String.valueOf(id) },
                null, null, null); // groupby, having, orderBy

        if(cursor.getCount() == 0) {
            return null;
        }
        // va sur la 1ere ligne
        cursor.moveToFirst();
        // Renvoie la ligne
        return convertirCursor(cursor);
    }

    // avoir toutes les personnes dans une list view.........................
    public List<com.example.db.models.Personne> toutPrendre(){
        Cursor cursor = db.query(DbInfo.Personne.TABLE_NOM, null, null, null, null, null, null);

        List<com.example.db.models.Personne> personnes = new ArrayList<>();
        if(cursor.getCount() == 0) {
            return personnes;
        }
        cursor.moveToFirst(); // Va sur la 1ere ligne
        while(! cursor.isAfterLast()) { // Tant que je suis pas à la fin
            com.example.db.models.Personne p = convertirCursor(cursor);
            personnes.add(p); // ajouter

            cursor.moveToNext(); // Lire la ligne suivante

        }
        return personnes;
    }

    // Mise à jour .....................
    public boolean miseAJour(long id, com.example.db.models.Personne personne) {
        ContentValues cv = creerPersonne(personne);

        int nbRow = db.update(DbInfo.Personne.TABLE_NOM, cv,
                DbInfo.Personne.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) });

        return nbRow == 1;
    }

    // Delete .........................
    public boolean delete(long id) {
        // -> "DELETE FROM product WHERE _id = " + id;

        int nbRow = db.delete( DbInfo.Personne.TABLE_NOM,
                DbInfo.Personne.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) });

        return nbRow == 1;
    }

}

