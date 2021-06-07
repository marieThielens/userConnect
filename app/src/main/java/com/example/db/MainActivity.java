package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.db.db.dao.PersonneDao;
import com.example.db.models.Personne;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nom, mdp;
    Button btnAjouter, btnVoirData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.inputNom);
        mdp = findViewById(R.id.input_mdp);
        btnAjouter = findViewById(R.id.btn_ajouter_data);
        btnVoirData = findViewById(R.id.btn_voir_data);

        // Initialisation du DAO pour travailler avec Personne .............
        PersonneDao personneDao = new PersonneDao(this);

       // ma methode du dao pour que la db soit accessible en écriture
        personneDao.ecrireDb();
        //long id = personneDao.insererPersonne(p1);

        // Lecture de toutes les personne de la db
        personneDao.toutPrendre();
        List<Personne> personnes = personneDao.toutPrendre();
        personneDao.close();

        btnAjouter.setOnClickListener(v -> {
            ajouterUser();
        });

    }
    public void ajouterUser(){
        String monNom = nom.getText().toString();
        String monMdp = mdp.getText().toString();

        // Créer la nouvelle personne
        Personne personne = new Personne(monNom, monMdp);

        // sauver dans la db
        PersonneDao personneDao = new PersonneDao(getApplicationContext());
        personneDao.ecrireDb();
        personneDao.insererPersonne(personne);
        personneDao.close();

    }

}