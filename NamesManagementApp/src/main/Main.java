package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.AucunPretraiteur;
import classesConcretes.ComparateurDeNomsRandom;
import classesConcretes.GenerateurPrimitif;
import classesConcretes.SelectionneurNeutre;
import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.ComparateurDeNoms;
import interfaces.GenerateurDeCandidatsALaComparaison;
import interfaces.Pretraiteur;
import interfaces.SelectionneurDeResultats;
import moteur.MoteurDeMatchingDeNoms;

public class Main {
    public static void main(String[] args) {
        // Création d'une liste de noms simulée
        List<Nom> listeDeNoms = new ArrayList<>();
        listeDeNoms.add(new Nom("Alice Dupont", Arrays.asList("Alice", "Dupont"), 1));
        listeDeNoms.add(new Nom("Alicia Dupuis", Arrays.asList("Alicia", "Dupuis"), 2));
        listeDeNoms.add(new Nom("Bob Martin", Arrays.asList("Bob", "Martin"), 3));
        listeDeNoms.add(new Nom("Charlie Dumas", Arrays.asList("Charlie", "Dumas"), 4));

        // Nom à rechercher (format déjà prêt)
        Nom nomARechercher = new Nom("Alice Dupont", Arrays.asList("Alice", "Dupont"), 999);

        // Préparation des composants trivials
        List<Pretraiteur> pretraiteurs = new ArrayList<>();
        pretraiteurs.add(new AucunPretraiteur());

        ComparateurDeNoms comparateur = new ComparateurDeNomsRandom();
        GenerateurDeCandidatsALaComparaison generateur = new GenerateurPrimitif();
        SelectionneurDeResultats selectionneur = new SelectionneurNeutre();

        // Création du moteur
        MoteurDeMatchingDeNoms moteur = new MoteurDeMatchingDeNoms(
            pretraiteurs,
            comparateur,
            generateur,
            selectionneur
        );

        // Lancement de la recherche
        List<ResultatDeComparaison> resultats = moteur.rechercherUnNomDansUneListe(nomARechercher, listeDeNoms);

        // Affichage des résultats
        for (ResultatDeComparaison resultat : resultats) {
            System.out.println(
                "Nom1: " + resultat.getNom1().getNomComplet() +
                ", Nom2: " + resultat.getNom2().getNomComplet() +
                ", Score: " + resultat.getScore()
            );
        }
    }
}
