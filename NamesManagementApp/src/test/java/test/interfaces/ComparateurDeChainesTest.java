package test.interfaces;

import classesConcretes.ComparateurEgaliteExacte;
import interfaces.ComparateurDeChaines;
import classesConcretes.ComparateurJaroWinkler;
import classesConcretes.ComparateurLevenshtein;

public class ComparateurDeChainesTest {

    public static void main(String[] args) {
        // Test avec ComparateurEgaliteExacte
        ComparateurDeChaines comparateurExact = new ComparateurEgaliteExacte();
        System.out.println("Test avec ComparateurEgaliteExacte:");
        testComparateur(comparateurExact, "test", "test", 1.0f, "Cas 1 (exact - identiques)");
        testComparateur(comparateurExact, "test", "Test", 0.0f, "Cas 2 (exact - casse différente)");
        testComparateur(comparateurExact, "test", "autre", 0.0f, "Cas 3 (exact - différentes)");
        testComparateur(comparateurExact, "", "", 1.0f, "Cas 4 (exact - vides)");
        testComparateur(comparateurExact, "test", "", 0.0f, "Cas 5 (exact - une vide)");
        System.out.println("-------------------------------------");

        // Test avec ComparateurJaroWinkler
        ComparateurDeChaines comparateurJaro = new ComparateurJaroWinkler();
        System.out.println("Test avec ComparateurJaroWinkler:");
        testComparateur(comparateurJaro, "martha", "marhta", 0.961f, "Cas 1 (Jaro - martha/marhta)"); // Exemple classique Jaro-Winkler
        testComparateur(comparateurJaro, "dwayne", "duane", 0.84f, "Cas 2 (Jaro - dwayne/duane)");
        testComparateur(comparateurJaro, "jones", "johnson", 0.832f, "Cas 3 (Jaro - jones/johnson)");
        testComparateur(comparateurJaro, "", "", 1.0f, "Cas 4 (Jaro - vides)"); // Devrait être 1.0 pour chaînes vides identiques
        testComparateur(comparateurJaro, "test", "", 0.0f, "Cas 5 (Jaro - une vide)"); // Devrait être 0.0
        testComparateur(comparateurJaro, "apple", "apply", 0.933f, "Cas 6 (Jaro - apple/apply)"); // Légère différence
        System.out.println("-------------------------------------");

        // Test avec ComparateurLevenshtein
        ComparateurDeChaines comparateurLev = new ComparateurLevenshtein();
        System.out.println("Test avec ComparateurLevenshtein:");
        testComparateur(comparateurLev, "kitten", "sitting", 0.5714f, "Cas 1 (Lev - kitten/sitting)"); // dist 3, maxlen 7 -> 1 - 3/7
        testComparateur(comparateurLev, "sunday", "saturday", 0.625f, "Cas 2 (Lev - sunday/saturday)"); // dist 3, maxlen 8 -> 1 - 3/8
        testComparateur(comparateurLev, "test", "test", 1.0f, "Cas 3 (Lev - identiques)");
        testComparateur(comparateurLev, "", "", 1.0f, "Cas 4 (Lev - vides)");
        testComparateur(comparateurLev, "test", "", 0.0f, "Cas 5 (Lev - une vide)");
        System.out.println("-------------------------------------");
    }

    private static void testComparateur(ComparateurDeChaines comparateur, String s1, String s2, double expectedScore, String testName) {
        double score = comparateur.comparer(s1, s2);
        // Utilisation d'une tolérance pour les comparaisons de flottants
        if (Math.abs(score - expectedScore) < 0.001f) {
            System.out.println(testName + ": Succès. Score: " + score);
        } else {
            System.err.println(testName + ": Échec. Attendu: " + expectedScore + ", Obtenu: " + score);
        }
    }
}

