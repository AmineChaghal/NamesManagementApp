package test.classesConcretes;

import classesPorteusesDeDonnees.Nom;
import interfaces.ComparateurDeChaines;
import interfaces.ComparateurDeNoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.ComparateurDifferentesCombinaisonsDuNomDecompose;
import classesConcretes.ComparateurEgaliteExacte;

public class ComparateurDifferentesCombinaisonsDuNomDecomposeTest {

    public static void main(String[] args) {
        // Utilisation d'un comparateur de chaînes simple pour les tests (EgaliteExacte)
        List<ComparateurDeChaines> comparateursChaines = new ArrayList<>();
        comparateursChaines.add(new ComparateurEgaliteExacte());

        ComparateurDeNoms comparateur = new ComparateurDifferentesCombinaisonsDuNomDecompose(comparateursChaines);

        // Cas 1: Noms identiques, décomposition identique
        Nom nom1_1 = new Nom("Jean Marc Dupont", Arrays.asList("Jean", "Marc", "Dupont"), "1");
        Nom nom1_2 = new Nom("Jean Marc Dupont", Arrays.asList("Jean", "Marc", "Dupont"), "2");
        double resultat1 = comparateur.comparer(nom1_1, nom1_2);
        if (resultat1 == 1.0) {
            System.out.println("ComparateurDifferentesCombinaisonsTest - Cas 1 (identiques): Succès. Score: " + resultat1);
        } else {
            System.err.println("ComparateurDifferentesCombinaisonsTest - Cas 1 (identiques): Échec. Attendu: 1.0, Obtenu: " + resultat1);
        }

        // Cas 2: Noms avec mêmes parties mais dans un ordre différent
        Nom nom2_1 = new Nom("Dupont Jean Marc", Arrays.asList("Dupont", "Jean", "Marc"), "3");
        // nom1_1 est ("Jean", "Marc", "Dupont")
        double resultat2 = comparateur.comparer(nom1_1, nom2_1); // Devrait trouver une permutation qui matche à 100%
        if (resultat2 == 1.0) {
            System.out.println("ComparateurDifferentesCombinaisonsTest - Cas 2 (permutation): Succès. Score: " + resultat2);
        } else {
            System.err.println("ComparateurDifferentesCombinaisonsTest - Cas 2 (permutation): Échec. Attendu: 1.0, Obtenu: " + resultat2);
        }

        // Cas 3: Noms avec une partie commune, tailles différentes
        Nom nom3_1 = new Nom("Jean Dupont", Arrays.asList("Jean", "Dupont"), "4");
        // nom1_1 est ("Jean", "Marc", "Dupont")
        // La plus petite liste est ("Jean", "Dupont").
        // Sous-listes de nom1_1 de taille 2: ("Jean", "Marc"), ("Marc", "Dupont")
        // Permutations de nom3_1: ("Jean", "Dupont"), ("Dupont", "Jean")
        // Comparaison de ("Jean", "Dupont") avec ("Jean", "Marc") -> Jean=1, Dupont vs Marc=0. Moyenne = 0.5
        // Comparaison de ("Jean", "Dupont") avec ("Marc", "Dupont") -> Jean vs Marc=0, Dupont=1. Moyenne = 0.5
        // Comparaison de ("Dupont", "Jean") avec ("Jean", "Marc") -> Dupont vs Jean=0, Jean vs Marc=0. Moyenne = 0
        // Comparaison de ("Dupont", "Jean") avec ("Marc", "Dupont") -> Dupont vs Marc=0, Jean vs Dupont=0. Moyenne = 0
        // Le meilleur score devrait être 0.5
        double resultat3 = comparateur.comparer(nom1_1, nom3_1);
        if (resultat3 == 0.5) { // (1/2 * (1+0)) ou (1/2 * (0+1))
            System.out.println("ComparateurDifferentesCombinaisonsTest - Cas 3 (partie commune, tailles dif): Succès. Score: " + resultat3);
        } else {
            System.err.println("ComparateurDifferentesCombinaisonsTest - Cas 3 (partie commune, tailles dif): Échec. Attendu: 0.5, Obtenu: " + resultat3);
        }

        // Cas 4: Noms complètement différents
        Nom nom4_1 = new Nom("Pierre Martin", Arrays.asList("Pierre", "Martin"), "5");
        Nom nom4_2 = new Nom("Sophie Bernard", Arrays.asList("Sophie", "Bernard"), "6");
        double resultat4 = comparateur.comparer(nom4_1, nom4_2);
        if (resultat4 == 0.0) {
            System.out.println("ComparateurDifferentesCombinaisonsTest - Cas 4 (différents): Succès. Score: " + resultat4);
        } else {
            System.err.println("ComparateurDifferentesCombinaisonsTest - Cas 4 (différents): Échec. Attendu: 0.0, Obtenu: " + resultat4);
        }

        // Cas 5: Une liste de décomposition vide
        Nom nom5_1 = new Nom("Test Vide", new ArrayList<>(), "7");
        double resultat5 = comparateur.comparer(nom1_1, nom5_1);
        if (resultat5 == 0.0) {
            System.out.println("ComparateurDifferentesCombinaisonsTest - Cas 5 (liste vide): Succès. Score: " + resultat5);
        } else {
            System.err.println("ComparateurDifferentesCombinaisonsTest - Cas 5 (liste vide): Échec. Attendu: 0.0, Obtenu: " + resultat5);
        }

        // Cas 6: Noms avec des parties partiellement correspondantes
        // nomA: ("alpha", "beta", "gamma")
        // nomB: ("alpha", "delta", "gamma")
        // Comparaison ("alpha", "beta", "gamma") vs ("alpha", "delta", "gamma")
        // alpha vs alpha = 1
        // beta vs delta = 0
        // gamma vs gamma = 1
        // Moyenne = (1+0+1)/3 = 2/3 = 0.666...f
        Nom nom6_1 = new Nom("Alpha Beta Gamma", Arrays.asList("alpha", "beta", "gamma"), "8");
        Nom nom6_2 = new Nom("Alpha Delta Gamma", Arrays.asList("alpha", "delta", "gamma"), "9");
        double resultat6 = comparateur.comparer(nom6_1, nom6_2);
        // Utiliser une tolérance pour les comparaisons de flottants
        if (Math.abs(resultat6 - (2.0f/3.0)) < 0.0001) {
            System.out.println("ComparateurDifferentesCombinaisonsTest - Cas 6 (correspondance partielle): Succès. Score: " + resultat6);
        } else {
            System.err.println("ComparateurDifferentesCombinaisonsTest - Cas 6 (correspondance partielle): Échec. Attendu approx: " + (2.0f/3.0) + ", Obtenu: " + resultat6);
        }
    }
}

