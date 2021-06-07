package com.example.db.models;

public class Personne {
    private int id;
    private String nom;
    private String mdp;

    // Premier constructeur sans l'id
    public Personne(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }
    // Avec l'id
    public Personne(int id, String nom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
    }

    // Getters and setters

    public int getId() { return id; }
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
}
