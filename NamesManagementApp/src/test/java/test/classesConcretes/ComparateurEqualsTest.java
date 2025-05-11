package test.classesConcretes;

import classesConcretes.ComparateurEquals;
import interfaces.ComparateurDeChaines;

public class ComparateurEqualsTest {

    public static void main(String[] args) {
        ComparateurDeChaines comparateur = new ComparateurEquals();

        // Cas 1: Chaînes identiques
        String s1_1 = "test";
        String s1_2 = "test";
        double resultat1 = comparateur.comparer(s1_1, s1_2);
        if (resultat1 == 1.0f) {
            System.out.println("ComparateurEqualsTest - Cas 1 (identiques): Succès");
        } else {
            System.err.println("ComparateurEqualsTest - Cas 1 (identiques): Échec. Attendu: 1.0, Obtenu: " + resultat1);
        }

        // Cas 2: Chaînes différentes
        String s2_1 = "test1";
        String s2_2 = "test2";
        double resultat2 = comparateur.comparer(s2_1, s2_2);
        if (resultat2 == 0.0f) {
            System.out.println("ComparateurEqualsTest - Cas 2 (différentes): Succès");
        } else {
            System.err.println("ComparateurEqualsTest - Cas 2 (différentes): Échec. Attendu: 0.0, Obtenu: " + resultat2);
        }

        // Cas 3: Une chaîne vide et une non vide
        String s3_1 = "";
        String s3_2 = "test";
        double resultat3 = comparateur.comparer(s3_1, s3_2);
        if (resultat3 == 0.0f) {
            System.out.println("ComparateurEqualsTest - Cas 3 (une vide): Succès");
        } else {
            System.err.println("ComparateurEqualsTest - Cas 3 (une vide): Échec. Attendu: 0.0, Obtenu: " + resultat3);
        }

        // Cas 4: Deux chaînes vides
        String s4_1 = "";
        String s4_2 = "";
        double resultat4 = comparateur.comparer(s4_1, s4_2);
        if (resultat4 == 1.0f) {
            System.out.println("ComparateurEqualsTest - Cas 4 (deux vides): Succès");
        } else {
            System.err.println("ComparateurEqualsTest - Cas 4 (deux vides): Échec. Attendu: 1.0, Obtenu: " + resultat4);
        }

        // Cas 5: Sensibilité à la casse
        String s5_1 = "Test";
        String s5_2 = "test";
        double resultat5 = comparateur.comparer(s5_1, s5_2);
        if (resultat5 == 0.0f) {
            System.out.println("ComparateurEqualsTest - Cas 5 (casse différente): Succès");
        } else {
            System.err.println("ComparateurEqualsTest - Cas 5 (casse différente): Échec. Attendu: 0.0, Obtenu: " + resultat5);
        }

        // Note: Le comportement avec null n'est pas défini dans l'implémentation de ComparateurEquals
        // et lèverait une NullPointerException. Les tests pour null ne sont donc pas inclus ici
        // à moins que la classe ne soit modifiée pour les gérer.
    }
}

