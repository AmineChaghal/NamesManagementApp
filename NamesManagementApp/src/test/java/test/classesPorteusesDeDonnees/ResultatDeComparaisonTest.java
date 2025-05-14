package test.classesPorteusesDeDonnees;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;

import java.util.ArrayList;

public class ResultatDeComparaisonTest {

    public static void main(String[] args) {
        // Noms pour les tests
        Nom nomA = new Nom("Nom A", Arrays.asList("Nom", "A"), "1");
        Nom nomB = new Nom("Nom B", Arrays.asList("Nom", "B"), "2");
        Nom nomC = new Nom("Nom C", Arrays.asList("Nom", "C"), "3");
        Nom nomD = new Nom("Nom D", Arrays.asList("Nom", "D"), "4");

        // Cas 1: Création et getters
        CoupleDeNoms couple1 = new CoupleDeNoms(nomA, nomB);
        double score1 = 0.75f;
        ResultatDeComparaison res1 = new ResultatDeComparaison(nomA, nomB, score1); // Utilisation directe des Noms

        boolean cas1_ok = true;
        if (!res1.getNom1().equals(nomA)) {
            System.err.println("ResultatDeComparaisonTest - Cas 1: Échec. getNom1() incorrect.");
            cas1_ok = false;
        }
        if (!res1.getNom2().equals(nomB)) {
            System.err.println("ResultatDeComparaisonTest - Cas 1: Échec. getNom2() incorrect.");
            cas1_ok = false;
        }
        if (res1.getScore() != score1) {
            System.err.println("ResultatDeComparaisonTest - Cas 1: Échec. getScore() incorrect. Attendu: " + score1 + ", Obtenu: " + res1.getScore());
            cas1_ok = false;
        }
        if (cas1_ok) {
            System.out.println("ResultatDeComparaisonTest - Cas 1 (création et getters): Succès");
        }

        // Cas 2: Test de la méthode compareTo pour le tri
        ResultatDeComparaison r_high = new ResultatDeComparaison(nomA, nomB, 0.9);
        ResultatDeComparaison r_medium = new ResultatDeComparaison(nomA, nomC, 0.7);
        ResultatDeComparaison r_low = new ResultatDeComparaison(nomB, nomC, 0.5);
        ResultatDeComparaison r_medium_alt = new ResultatDeComparaison(nomC, nomD, 0.7); // Même score que r_medium

        List<ResultatDeComparaison> listeResultats = new ArrayList<>();
        listeResultats.add(r_low);      // 0.5
        listeResultats.add(r_high);     // 0.9
        listeResultats.add(r_medium_alt); // 0.7
        listeResultats.add(r_medium);   // 0.7

        Collections.sort(listeResultats); // Doit trier par score décroissant

        // Ordre attendu après tri: r_high, r_medium/r_medium_alt, r_medium/r_medium_alt, r_low
        // (l'ordre entre r_medium et r_medium_alt n'est pas garanti par double.compare si scores égaux)

        boolean cas2_ok = true;
        if (listeResultats.get(0) != r_high) {
            System.err.println("ResultatDeComparaisonTest - Cas 2: Échec. Premier élément incorrect après tri. Attendu score 0.9, Obtenu: " + listeResultats.get(0).getScore());
            cas2_ok = false;
        }
        if (listeResultats.get(3) != r_low) {
            System.err.println("ResultatDeComparaisonTest - Cas 2: Échec. Dernier élément incorrect après tri. Attendu score 0.5, Obtenu: " + listeResultats.get(3).getScore());
            cas2_ok = false;
        }
        // Vérifier que les éléments du milieu ont le bon score (0.7)
        if (listeResultats.get(1).getScore() != 0.7f || listeResultats.get(2).getScore() != 0.7) {
            System.err.println("ResultatDeComparaisonTest - Cas 2: Échec. Scores des éléments du milieu incorrects après tri.");
            cas2_ok = false;
        }
        // Vérifier que les deux éléments à 0.7 sont présents
        if (! ( (listeResultats.get(1) == r_medium && listeResultats.get(2) == r_medium_alt) || 
                (listeResultats.get(1) == r_medium_alt && listeResultats.get(2) == r_medium) ) ) {
            System.err.println("ResultatDeComparaisonTest - Cas 2: Échec. Les deux éléments avec score 0.7 ne sont pas correctement positionnés.");
            cas2_ok = false;
        }


        if (cas2_ok) {
            System.out.println("ResultatDeComparaisonTest - Cas 2 (compareTo et tri): Succès");
        } else {
            System.out.println("ResultatDeComparaisonTest - Cas 2 (compareTo et tri): Échec. Ordre obtenu après tri: ");
            for(ResultatDeComparaison r : listeResultats) {
                System.out.println("  Score: " + r.getScore());
            }
        }
        
        // Cas 3: Comparaison de deux objets ResultatDeComparaison
        int compareRes1 = r_high.compareTo(r_low); // 0.9 vs 0.5 -> r_high est "plus petit" car son score est plus grand (tri décroissant)
                                                  // double.compare(other.score, this.score) -> double.compare(0.5, 0.9) -> -1
        if (compareRes1 < 0) {
            System.out.println("ResultatDeComparaisonTest - Cas 3.1 (0.9 vs 0.5): Succès. compareTo retourne " + compareRes1);
        } else {
            System.err.println("ResultatDeComparaisonTest - Cas 3.1 (0.9 vs 0.5): Échec. Attendu < 0, Obtenu: " + compareRes1);
        }

        int compareRes2 = r_low.compareTo(r_high); // 0.5 vs 0.9 -> r_low est "plus grand"
                                                  // double.compare(other.score, this.score) -> double.compare(0.9, 0.5) -> 1
        if (compareRes2 > 0) {
            System.out.println("ResultatDeComparaisonTest - Cas 3.2 (0.5 vs 0.9): Succès. compareTo retourne " + compareRes2);
        } else {
            System.err.println("ResultatDeComparaisonTest - Cas 3.2 (0.5 vs 0.9): Échec. Attendu > 0, Obtenu: " + compareRes2);
        }

        int compareRes3 = r_medium.compareTo(r_medium_alt); // 0.7 vs 0.7 -> scores égaux
                                                            // double.compare(0.7, 0.7) -> 0
        if (compareRes3 == 0) {
            System.out.println("ResultatDeComparaisonTest - Cas 3.3 (0.7 vs 0.7): Succès. compareTo retourne " + compareRes3);
        } else {
            System.err.println("ResultatDeComparaisonTest - Cas 3.3 (0.7 vs 0.7): Échec. Attendu 0, Obtenu: " + compareRes3);
        }
    }
}

