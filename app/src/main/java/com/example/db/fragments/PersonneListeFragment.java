package com.example.db.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.db.R;
import com.example.db.models.Personne;

import java.util.ArrayList;

public class PersonneListeFragment extends Fragment {

    private ListView lvPersonne;

    // constante pour création du fragment
    public static final String ENVOI_DATA = "data_personne";

    // Les données de mon fragment. Personne de models
    private ArrayList<Personne> personnes;

    public PersonneListeFragment() {
        // Constructeur vide obligatoire !
    }

    // Méthode générée par défaut pour créer un Fragment ( les données seront placé dans un bundle ) ____________
    public static PersonneListeFragment newInstance(ArrayList<Personne> envoyerData) { // envoyerData crée ici

        // Création du fragment
        PersonneListeFragment fragment = new PersonneListeFragment();

        // Création d'un bundle avec les données
        Bundle args = new Bundle();
        args.putParcelableArrayList(ENVOI_DATA, envoyerData); // mon array envoyerData
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            personnes = getArguments().getParcelableArrayList(ENVOI_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Spécifier les layout de mon fragment
        View v = inflater.inflate(R.layout.fragment_personne_liste, container, false);
        // relier ma ListeVue qui est dans fragment personne liste
        lvPersonne = v.findViewById(R.id.lv_frag_list_personne);
        // Inflate the layout for this fragment
        return v;
    }

    private void initialiserDonneesVue() {
        // Mon adapter qui va afficher mes listes
        ArrayAdapter<Personne> adapter = new ArrayAdapter<>(
            getContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            personnes
        );
        lvPersonne.setAdapter(adapter); // ma listView personne ajouter dans l'adapteur

        // Quand je clique sur le nom d'une personne un nouveau fragment avec d'autres infos
        lvPersonne.setOnItemClickListener((parent, view, position, id) -> {
            // models = array personnes
            Personne personne = personnes.get(position); // il voit où j'ai cliqué
            if(event != null) {
                event.onClickItem(personne);
            }
        });
    }
    // Contraindre l'interface qu'on va écrire juste après sera fonctionnelle.
    // Elle n'aura qu'une méthode dans l'objectif de pouvoir avoir une lambda
    @FunctionalInterface
    public interface OnClickItemListener {
        void onClickItem(Personne personne); // mon expression lambda
    }
    private OnClickItemListener event;

    public void setOnClickItemListener(OnClickItemListener event) {this.event = event; }
}