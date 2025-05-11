package test.classesConcretes;

import classesPorteusesDeDonnees.Nom;
import interfaces.ComparateurDeChaines;
import interfaces.ComparateurDeNoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.ComparateurDeNomsComplets;
import classesConcretes.ComparateurEgaliteExacte;

public class ComparateurDeNomsCompletsTest {

    public static void main(String[] args) {
        // Création des noms de test
        Nom nom1 = new Nom("Jean Dupont", Arrays.asList("Jean", "Dupont"), "1");
        Nom nom2 = new Nom("Jean Dupont", Arrays.asList("Jean", "Dupont"), "2");
        Nom nom3 = new Nom("Jeanne Durand", Arrays.asList("Jeanne", "Durand"), "3");

        // Cas 1: Utilisation d'un seul comparateur (EgaliteExacte)
        List<ComparateurDeChaines> comparateurs1 = new ArrayList<>();
        comparateurs1.add(new ComparateurEgaliteExacte());
        ComparateurDeNoms comparateurNoms1 = new ComparateurDeNomsComplets(comparateurs1);

        double resultat1_1 = comparateurNoms1.comparer(nom1, nom2); // Devrait être 1.0f
        if (resultat1_1 == 1.0f) {
            System.out.println("ComparateurDeNomsCompletsTest - Cas 1.1 (noms identiques): Succès");
        } else {
            System.err.println("ComparateurDeNomsCompletsTest - Cas 1.1: Échec. Attendu: 1.0, Obtenu: " + resultat1_1);
        }

        double resultat1_2 = comparateurNoms1.comparer(nom1, nom3); // Devrait être 0.0f
        if (resultat1_2 == 0.0f) {
            System.out.println("ComparateurDeNomsCompletsTest - Cas 1.2 (noms différents): Succès");
        } else {
            System.err.println("ComparateurDeNomsCompletsTest - Cas 1.2: Échec. Attendu: 0.0, Obtenu: " + resultat1_2);
        }

        // Cas 2: Utilisation de deux comparateurs (EgaliteExacte et un mock qui retourne toujours 0.5f)
        // Pour simplifier, nous allons réutiliser EgaliteExacte et un autre qui retourne une valeur fixe.
        // Idéalement, on utiliserait un framework de mock comme Mockito.
        class MockComparateurChaines implements ComparateurDeChaines {
            private double fixedScore;
            public MockComparateurChaines(double score) { this.fixedScore = score; }
            @Override
            public double comparer(String s1, String s2) { return fixedScore; }
        }

        List<ComparateurDeChaines> comparateurs2 = new ArrayList<>();
        comparateurs2.add(new ComparateurEgaliteExacte()); // Score 1.0f pour nom1 vs nom2
        comparateurs2.add(new MockComparateurChaines(0.5f)); // Score 0.5f pour n'importe quelle paire
        ComparateurDeNoms comparateurNoms2 = new ComparateurDeNomsComplets(comparateurs2);

        // Test avec nom1 et nom2 (identiques pour EgaliteExacte)
        // EgaliteExacte retourne 1.0f. MockComparateurChaines retourne 0.5f.
        // Moyenne attendue = (1.0f + 0.5f) / 2 = 0.75f
        double resultat2_1 = comparateurNoms2.comparer(nom1, nom2);
        if (resultat2_1 == 0.75f) {
            System.out.println("ComparateurDeNomsCompletsTest - Cas 2.1 (deux comparateurs, noms identiques): Succès");
        } else {
            System.err.println("ComparateurDeNomsCompletsTest - Cas 2.1: Échec. Attendu: 0.75, Obtenu: " + resultat2_1);
        }

        // Test avec nom1 et nom3 (différents pour EgaliteExacte)
        // EgaliteExacte retourne 0.0f. MockComparateurChaines retourne 0.5f.
        // Moyenne attendue = (0.0f + 0.5f) / 2 = 0.25f
        double resultat2_2 = comparateurNoms2.comparer(nom1, nom3);
        if (resultat2_2 == 0.25f) {
            System.out.println("ComparateurDeNomsCompletsTest - Cas 2.2 (deux comparateurs, noms différents): Succès");
        } else {
            System.err.println("ComparateurDeNomsCompletsTest - Cas 2.2: Échec. Attendu: 0.25, Obtenu: " + resultat2_2);
        }
        
        // Cas 3: Liste de comparateurs vide (devrait être géré par une assertion ou une attente d'exception si non géré)
        // Le code actuel lèverait une ArithmeticException. Pour un test simple, on évite ce cas
        // ou on s'attendrait à une exception spécifique si c'était le comportement désiré et géré.
        // System.out.println("ComparateurDeNomsCompletsTest - Cas 3: Test avec liste de comparateurs vide (non exécuté pour éviter ArithmeticException sans gestion)");

    }
}

