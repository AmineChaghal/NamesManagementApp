package test.classesConcretes;

import classesConcretes.ComparateurEgaliteExacte;
import interfaces.ComparateurDeChaines;

public class ComparateurEgaliteExacteTest {

    public static void main(String[] args) {
        ComparateurDeChaines comparateur = new ComparateurEgaliteExacte();

        // Cas 1: Chaînes identiques
        String s1_1 = "test";
        String s1_2 = "test";
        double resultat1 = comparateur.comparer(s1_1, s1_2);
        if (resultat1 == 1.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 1 (identiques): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 1 (identiques): Échec. Attendu: 1.0, Obtenu: " + resultat1);
        }

        // Cas 2: Chaînes différentes
        String s2_1 = "test1";
        String s2_2 = "test2";
        double resultat2 = comparateur.comparer(s2_1, s2_2);
        if (resultat2 == 0.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 2 (différentes): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 2 (différentes): Échec. Attendu: 0.0, Obtenu: " + resultat2);
        }

        // Cas 3: Une chaîne vide et une non vide
        String s3_1 = "";
        String s3_2 = "test";
        double resultat3 = comparateur.comparer(s3_1, s3_2);
        if (resultat3 == 0.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 3 (une vide): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 3 (une vide): Échec. Attendu: 0.0, Obtenu: " + resultat3);
        }

        // Cas 4: Deux chaînes vides
        String s4_1 = "";
        String s4_2 = "";
        double resultat4 = comparateur.comparer(s4_1, s4_2);
        if (resultat4 == 1.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 4 (deux vides): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 4 (deux vides): Échec. Attendu: 1.0, Obtenu: " + resultat4);
        }

        // Cas 5: Sensibilité à la casse
        String s5_1 = "Test";
        String s5_2 = "test";
        double resultat5 = comparateur.comparer(s5_1, s5_2);
        if (resultat5 == 0.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 5 (casse différente): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 5 (casse différente): Échec. Attendu: 0.0, Obtenu: " + resultat5);
        }

        // Cas 6: Une chaîne null, une non-null
        String s6_1 = null;
        String s6_2 = "test";
        double resultat6 = comparateur.comparer(s6_1, s6_2);
        if (resultat6 == 0.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 6 (un null): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 6 (un null): Échec. Attendu: 0.0, Obtenu: " + resultat6);
        }

        // Cas 7: Deux chaînes null
        String s7_1 = null;
        String s7_2 = null;
        double resultat7 = comparateur.comparer(s7_1, s7_2);
        if (resultat7 == 1.0) {
            System.out.println("ComparateurEgaliteExacteTest - Cas 7 (deux nulls): Succès");
        } else {
            System.err.println("ComparateurEgaliteExacteTest - Cas 7 (deux nulls): Échec. Attendu: 1.0, Obtenu: " + resultat7);
        }
    }
}

