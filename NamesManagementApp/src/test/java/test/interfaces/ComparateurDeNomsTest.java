package test.interfaces;

import classesConcretes.ComparateurDeNomsComplets;
import interfaces.ComparateurDeChaines;
import interfaces.ComparateurDeNoms;
import classesConcretes.ComparateurDeNomsRandom;
import classesConcretes.ComparateurDifferentesCombinaisonsDuNomDecompose;
import classesConcretes.ComparateurEgaliteExacte;
import classesConcretes.ComparateurJaroWinkler;
import classesPorteusesDeDonnees.Nom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComparateurDeNomsTest {

    public static void main(String[] args) {
        // Noms pour les tests
        Nom nom1 = new Nom("Jean Dupont", Arrays.asList("Jean", "Dupont"), "1");
        Nom nom2 = new Nom("Jeanne Dupont", Arrays.asList("Jeanne", "Dupont"), "2");
        Nom nom3 = new Nom("Jean Dupond", Arrays.asList("Jean", "Dupond"), "3");
        Nom nom4 = new Nom("Pierre Martin", Arrays.asList("Pierre", "Martin"), "4");
        Nom nom5 = new Nom("Jean Dupont", Arrays.asList("Jean", "Dupont"), "5"); // Même nom complet que nom1, ID différent

        // Test avec ComparateurDeNomsComplets
        List<ComparateurDeChaines> comparateurs = new ArrayList<>();
        comparateurs.add(new ComparateurEgaliteExacte()); 
        ComparateurDeNoms comparateurComplets = new ComparateurDeNomsComplets(comparateurs);
        System.out.println("Test avec ComparateurDeNomsComplets:");
        testComparateurNoms(comparateurComplets, nom1, nom5, 1.0f, "Cas 1.1 (Complets - identiques)");
        testComparateurNoms(comparateurComplets, nom1, nom2, 0.0f, "Cas 1.2 (Complets - différents)"); // Strictement différent
        testComparateurNoms(comparateurComplets, nom1, nom1, 1.0f, "Cas 1.3 (Complets - même instance)");
        System.out.println("-------------------------------------");

        // Test avec ComparateurDeNomsRandom
        // Le résultat est aléatoire, donc on ne peut pas tester une valeur exacte.
        // On peut juste vérifier que le score est entre 0.0 et 1.0 (inclus).
        ComparateurDeNoms comparateurRandom = new ComparateurDeNomsRandom();
        System.out.println("Test avec ComparateurDeNomsRandom:");
        double scoreRandom1 = comparateurRandom.comparer(nom1, nom2);
        if (scoreRandom1 >= 0.0f && scoreRandom1 <= 1.0f) {
            System.out.println("Cas 2.1 (Random - nom1 vs nom2): Succès. Score: " + scoreRandom1 + " (dans [0,1])");
        } else {
            System.err.println("Cas 2.1 (Random - nom1 vs nom2): Échec. Score: " + scoreRandom1 + " (hors de [0,1])");
        }
        double scoreRandom2 = comparateurRandom.comparer(nom1, nom1); // Devrait être 1.0f car les noms sont identiques
        if (scoreRandom2 == 1.0f) {
            System.out.println("Cas 2.2 (Random - nom1 vs nom1): Succès. Score: " + scoreRandom2);
        } else {
            System.err.println("Cas 2.2 (Random - nom1 vs nom1): Échec. Attendu 1.0, Obtenu: " + scoreRandom2);
        }
        System.out.println("-------------------------------------");

        // Test avec ComparateurDifferentesCombinaisonsDuNomDecompose
        // Cette classe nécessite que les Noms aient leur nomDécomposé initialisé.
        // Nom nomX = new Nom("Jean Pierre", Arrays.asList("Jean", "Pierre"), 1);
        // Nom nomY = new Nom("Pierre Jean", Arrays.asList("Pierre", "Jean"), 2);
        // Nom nomZ = new Nom("Jean Paul Pierre", Arrays.asList("Jean", "Paul", "Pierre"), 3);
        // Nom nomW = new Nom("Jacques", Arrays.asList("Jacques"), 4);

        ComparateurDeNoms comparateurCombinaisons = new ComparateurDifferentesCombinaisonsDuNomDecompose(comparateurs);
        System.out.println("Test avec ComparateurDifferentesCombinaisonsDuNomDecompose:");
        // Cas où les deux noms ont les mêmes parties décomposées dans un ordre différent
        Nom nA = new Nom("Jean Pierre", Arrays.asList("Jean", "Pierre"), "10");
        Nom nB = new Nom("Pierre Jean", Arrays.asList("Pierre", "Jean"), "11");
        testComparateurNoms(comparateurCombinaisons, nA, nB, 1.0f, "Cas 3.1 (Combinaisons - permutation)");

        // Cas où un nom est un sous-ensemble de l'autre
        Nom nC = new Nom("Jean Pierre Paul", Arrays.asList("Jean", "Pierre", "Paul"), "12");
        testComparateurNoms(comparateurCombinaisons, nA, nC, (2.0f/3.0f), "Cas 3.2 (Combinaisons - sous-ensemble)"); // 2 parties communes sur 3 max

        // Cas où il y a des parties communes mais pas une permutation complète ni un sous-ensemble pur
        Nom nD = new Nom("Jean Jacques", Arrays.asList("Jean", "Jacques"), "13");
        testComparateurNoms(comparateurCombinaisons, nA, nD, (1.0f/2.0f), "Cas 3.3 (Combinaisons - intersection partielle)"); // 1 partie commune sur 2 max

        // Cas sans aucune partie commune
        Nom nE = new Nom("Luc Marc", Arrays.asList("Luc", "Marc"), "14");
        testComparateurNoms(comparateurCombinaisons, nA, nE, 0.0f, "Cas 3.4 (Combinaisons - aucune partie commune)");
        
        // Cas avec listes de décomposition vides
        Nom nF_vide = new Nom("TestF", new ArrayList<>(), "15");
        Nom nG_vide = new Nom("TestG", new ArrayList<>(), "16");
        testComparateurNoms(comparateurCombinaisons, nF_vide, nG_vide, 0.0f, "Cas 3.5 (Combinaisons - listes vides)"); // Ou 1.0f si on considère 0/0 comme 1
        
        // Cas où une liste est vide et l'autre non
        testComparateurNoms(comparateurCombinaisons, nA, nF_vide, 0.0f, "Cas 3.6 (Combinaisons - une liste vide)");
        System.out.println("-------------------------------------");
    }

    private static void testComparateurNoms(ComparateurDeNoms comparateur, Nom n1, Nom n2, double expectedScore, String testName) {
        double score = comparateur.comparer(n1, n2);
        if (Math.abs(score - expectedScore) < 0.001f) {
            System.out.println(testName + ": Succès. Score: " + score);
        } else {
            System.err.println(testName + ": Échec. Attendu: " + expectedScore + ", Obtenu: " + score);
        }
    }
}

