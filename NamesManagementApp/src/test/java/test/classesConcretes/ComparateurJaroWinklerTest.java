package test.classesConcretes;

import interfaces.ComparateurDeChaines;
import org.apache.commons.text.similarity.JaroWinklerSimilarity; // For verification if needed, though the class uses its own instance

import classesConcretes.ComparateurJaroWinkler;

public class ComparateurJaroWinklerTest {

    public static void main(String[] args) {
        ComparateurDeChaines comparateur = new ComparateurJaroWinkler();
        JaroWinklerSimilarity verifier = new JaroWinklerSimilarity(); // For expected values

        // Cas 1: Chaînes identiques
        String s1_1 = "martha";
        String s1_2 = "martha";
        double resultat1 = comparateur.comparer(s1_1, s1_2);
        double expected1 = verifier.apply(s1_1, s1_2).doubleValue();
        if (Math.abs(resultat1 - expected1) < 0.0001f && resultat1 == 1.0) {
            System.out.println("ComparateurJaroWinklerTest - Cas 1 (identiques): Succès. Score: " + resultat1);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 1 (identiques): Échec. Attendu: " + expected1 + ", Obtenu: " + resultat1);
        }

        // Cas 2: Chaînes complètement différentes
        String s2_1 = "apple";
        String s2_2 = "banana";
        double resultat2 = comparateur.comparer(s2_1, s2_2);
        double expected2 = verifier.apply(s2_1, s2_2).doubleValue();
        if (Math.abs(resultat2 - expected2) < 0.0001) {
            System.out.println("ComparateurJaroWinklerTest - Cas 2 (différentes): Succès. Score: " + resultat2);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 2 (différentes): Échec. Attendu: " + expected2 + ", Obtenu: " + resultat2);
        }

        // Cas 3: Chaînes similaires
        String s3_1 = "dwayne";
        String s3_2 = "duane";
        double resultat3 = comparateur.comparer(s3_1, s3_2);
        double expected3 = verifier.apply(s3_1, s3_2).doubleValue(); // Environ 0.84
        if (Math.abs(resultat3 - expected3) < 0.0001) {
            System.out.println("ComparateurJaroWinklerTest - Cas 3 (similaires): Succès. Score: " + resultat3);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 3 (similaires): Échec. Attendu: " + expected3 + ", Obtenu: " + resultat3);
        }

        // Cas 4: Sensibilité à la casse (Jaro-Winkler est sensible à la casse)
        String s4_1 = "Martha";
        String s4_2 = "martha";
        double resultat4 = comparateur.comparer(s4_1, s4_2);
        double expected4 = verifier.apply(s4_1, s4_2).doubleValue(); // Devrait être différent de 1.0
        if (Math.abs(resultat4 - expected4) < 0.0001f && resultat4 < 1.0) {
            System.out.println("ComparateurJaroWinklerTest - Cas 4 (casse différente): Succès. Score: " + resultat4);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 4 (casse différente): Échec. Attendu: " + expected4 + " (et < 1.0), Obtenu: " + resultat4);
        }

        // Cas 5: Une chaîne vide
        String s5_1 = "test";
        String s5_2 = "";
        double resultat5 = comparateur.comparer(s5_1, s5_2);
        double expected5 = verifier.apply(s5_1, s5_2).doubleValue(); // Devrait être 0.0
        if (Math.abs(resultat5 - expected5) < 0.0001f && resultat5 == 0.0) {
            System.out.println("ComparateurJaroWinklerTest - Cas 5 (une vide): Succès. Score: " + resultat5);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 5 (une vide): Échec. Attendu: " + expected5 + ", Obtenu: " + resultat5);
        }

        // Cas 6: Deux chaînes vides
        String s6_1 = "";
        String s6_2 = "";
        double resultat6 = comparateur.comparer(s6_1, s6_2);
        double expected6 = verifier.apply(s6_1, s6_2).doubleValue(); // Devrait être 1.0
        if (Math.abs(resultat6 - expected6) < 0.0001f && resultat6 == 1.0) {
            System.out.println("ComparateurJaroWinklerTest - Cas 6 (deux vides): Succès. Score: " + resultat6);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 6 (deux vides): Échec. Attendu: " + expected6 + ", Obtenu: " + resultat6);
        }

        // Cas 7: Une chaîne null, une non-null
        String s7_1 = null;
        String s7_2 = "test";
        double resultat7 = comparateur.comparer(s7_1, s7_2);
        if (resultat7 == 0.0) {
            System.out.println("ComparateurJaroWinklerTest - Cas 7 (un null): Succès. Score: " + resultat7);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 7 (un null): Échec. Attendu: 0.0, Obtenu: " + resultat7);
        }

        // Cas 8: Deux chaînes null
        String s8_1 = null;
        String s8_2 = null;
        double resultat8 = comparateur.comparer(s8_1, s8_2);
        if (resultat8 == 1.0) {
            System.out.println("ComparateurJaroWinklerTest - Cas 8 (deux nulls): Succès. Score: " + resultat8);
        } else {
            System.err.println("ComparateurJaroWinklerTest - Cas 8 (deux nulls): Échec. Attendu: 1.0, Obtenu: " + resultat8);
        }
    }
}

