package com.example.db.db;

public class DbInfo {

    public static final String DB_NOM = "maDb"; // Le nom de la db
    public static final int DB_VERSION = 1; // la version de la db

    public static class Personne {
        public static final String TABLE_NOM = "personnes";

        public static final String COLUMN_ID = "_id";// Nom des colonnes
        public static final String COLUMN_NOM = "nom" ;
        public static final String COLUMN_MDP = "mdp" ;

        // requetes (DDL )  Pour créer le tabeau et ses colonnes
        public static final String REQUEST_CREATE =
                "CREATE TABLE " + Personne.TABLE_NOM + "("
                        + Personne.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + Personne.COLUMN_NOM + " TEXT NOT NULL, "
                        + Personne.COLUMN_MDP + " TEXT"
                        + ");";

        public static final String INSERT = "INSERT INTO " + TABLE_NOM + " (" + COLUMN_NOM + ") VALUES (?);" ;

        public static final String REQUEST_DELETE = "DROP TABLE IF EXISTS )" + Personne.TABLE_NOM + ";";
    }
}
