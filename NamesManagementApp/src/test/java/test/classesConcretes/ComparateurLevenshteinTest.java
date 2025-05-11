package test.classesConcretes;

import interfaces.ComparateurDeChaines;
import org.apache.commons.text.similarity.LevenshteinDistance; // For verification

import classesConcretes.ComparateurLevenshtein;

public class ComparateurLevenshteinTest {

    public static void main(String[] args) {
        ComparateurDeChaines comparateur = new ComparateurLevenshtein();
        LevenshteinDistance verifier = new LevenshteinDistance();

        // Cas 1: Chaînes identiques
        String s1_1 = "kitten";
        String s1_2 = "kitten";
        double resultat1 = comparateur.comparer(s1_1, s1_2);
        // Distance = 0. MaxLength = 6. Score = 1 - (0/6) = 1.0
        if (resultat1 == 1.0f) {
            System.out.println("ComparateurLevenshteinTest - Cas 1 (identiques): Succès. Score: " + resultat1);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 1 (identiques): Échec. Attendu: 1.0, Obtenu: " + resultat1);
        }

        // Cas 2: Chaînes complètement différentes (même longueur)
        String s2_1 = "apple";
        String s2_2 = "grape";
        double resultat2 = comparateur.comparer(s2_1, s2_2);
        int dist2 = verifier.apply(s2_1, s2_2); // apple vs grape -> g(a)r(p)a(p)p(l)e -> 4
        double expected2 = 1.0f - ((double) dist2 / Math.max(s2_1.length(), s2_2.length())); // 1 - (4/5) = 0.2
        if (Math.abs(resultat2 - expected2) < 0.0001f) {
            System.out.println("ComparateurLevenshteinTest - Cas 2 (différentes, même longueur): Succès. Score: " + resultat2);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 2 (différentes, même longueur): Échec. Attendu: " + expected2 + ", Obtenu: " + resultat2);
        }

        // Cas 3: Chaînes avec une différence (insertion/suppression)
        String s3_1 = "sitting";
        String s3_2 = "kitten"; // s -> k, i -> i, t -> t, t -> t, i -> e, n -> n, g -> '' (3 modifications)
        double resultat3 = comparateur.comparer(s3_1, s3_2);
        int dist3 = verifier.apply(s3_1, s3_2); // 3
        double expected3 = 1.0f - ((double) dist3 / Math.max(s3_1.length(), s3_2.length())); // 1 - (3/7) approx 0.5714
        if (Math.abs(resultat3 - expected3) < 0.0001f) {
            System.out.println("ComparateurLevenshteinTest - Cas 3 (similaires): Succès. Score: " + resultat3);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 3 (similaires): Échec. Attendu: " + expected3 + ", Obtenu: " + resultat3);
        }

        // Cas 4: Sensibilité à la casse
        String s4_1 = "Apple";
        String s4_2 = "apple";
        double resultat4 = comparateur.comparer(s4_1, s4_2);
        int dist4 = verifier.apply(s4_1, s4_2); // 1
        double expected4 = 1.0f - ((double) dist4 / Math.max(s4_1.length(), s4_2.length())); // 1 - (1/5) = 0.8
        if (Math.abs(resultat4 - expected4) < 0.0001f) {
            System.out.println("ComparateurLevenshteinTest - Cas 4 (casse différente): Succès. Score: " + resultat4);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 4 (casse différente): Échec. Attendu: " + expected4 + ", Obtenu: " + resultat4);
        }

        // Cas 5: Une chaîne vide
        String s5_1 = "test";
        String s5_2 = "";
        double resultat5 = comparateur.comparer(s5_1, s5_2);
        int dist5 = verifier.apply(s5_1, s5_2); // 4
        double expected5 = 1.0f - ((double) dist5 / Math.max(s5_1.length(), s5_2.length())); // 1 - (4/4) = 0.0
        if (resultat5 == 0.0f) {
            System.out.println("ComparateurLevenshteinTest - Cas 5 (une vide): Succès. Score: " + resultat5);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 5 (une vide): Échec. Attendu: 0.0, Obtenu: " + resultat5);
        }

        // Cas 6: Deux chaînes vides
        String s6_1 = "";
        String s6_2 = "";
        double resultat6 = comparateur.comparer(s6_1, s6_2);
        // Distance = 0. MaxLength = 0. Le code actuel donne 1.0f pour deux vides avant calcul.
        if (resultat6 == 1.0f) {
            System.out.println("ComparateurLevenshteinTest - Cas 6 (deux vides): Succès. Score: " + resultat6);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 6 (deux vides): Échec. Attendu: 1.0, Obtenu: " + resultat6);
        }

        // Cas 7: Une chaîne null, une non-null
        String s7_1 = null;
        String s7_2 = "test";
        double resultat7 = comparateur.comparer(s7_1, s7_2);
        if (resultat7 == 0.0f) {
            System.out.println("ComparateurLevenshteinTest - Cas 7 (un null): Succès. Score: " + resultat7);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 7 (un null): Échec. Attendu: 0.0, Obtenu: " + resultat7);
        }

        // Cas 8: Deux chaînes null
        String s8_1 = null;
        String s8_2 = null;
        double resultat8 = comparateur.comparer(s8_1, s8_2);
        if (resultat8 == 1.0f) {
            System.out.println("ComparateurLevenshteinTest - Cas 8 (deux nulls): Succès. Score: " + resultat8);
        } else {
            System.err.println("ComparateurLevenshteinTest - Cas 8 (deux nulls): Échec. Attendu: 1.0, Obtenu: " + resultat8);
        }
    }
}

