package com.example.db.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Personne implements Parcelable {
    private long id;
    private String nom;
    private String mdp;

    // Premier constructeur sans l'id
    public Personne(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }
    // Avec l'id
    public Personne(long id, String nom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
    }

    // Getters and setters

    public long getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }


    // Parcelable ........................
    @Override
    public int describeContents() {
        // Défini le nombre d'objets complexe contenu dans mon objet Parcelable
        return 0;
    }

    @Override
    // Découper pour envoyer dans un bundle
    public void writeToParcel(Parcel dest, int flags) {
        // Découpe les données sous forme de parcel
        dest.writeLong(id);
        dest.writeString(nom);
        dest.writeString(mdp);
    }
    // Récréer l'objet via le parcel. Le constructeur pour mon créator. Récréer mon parcel sur base des parcesl contenu dans le bundle
    Personne(Parcel parcel) { // !! ordre doit être le meme que dans writeToParcel
        this.id = parcel.readLong();
        this.nom = parcel.readString();
        this.mdp = parcel.readString();
    }

    // Définition d'un CREATOR = recréer
    // getParcel va appeler le creator statique lié aux type d'objet contenu dans la percel.
    public static final Parcelable.Creator<Personne> CREATOR = new Creator<Personne>() {

        @Override
        public Personne createFromParcel(Parcel source) {
            return new Personne(source);
        }

        @Override
        public Personne[] newArray(int size) {
            return new Personne[size];
        }
    };
}
