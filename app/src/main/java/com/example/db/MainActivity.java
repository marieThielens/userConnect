package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        btnAjouter.setOnClickListener(v -> {
            ajouterUser();
        });

    }
    public void ajouterUser(){
        String monNom = nom.getText().toString();
        String monMdp = mdp.getText().toString();
    }

}