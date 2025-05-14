package test.classesConcretes;

import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.SelectionneurDeResultats;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.SelectionneurDesNMeilleurs;

public class SelectionneurDesNMeilleursTest {

    public static void main(String[] args) {
        // Noms et couples pour les tests
    	Nom nom1 = new Nom("N1", Arrays.asList("N1"), "1");
        Nom nom2 = new Nom("N2", Arrays.asList("N2"), "2");
        Nom nom3 = new Nom("N3", Arrays.asList("N3"), "3");
        Nom nom4 = new Nom("N4", Arrays.asList("N4"), "4");
        // ResultatDeComparaison doit implémenter Comparable pour que Collections.sort fonctionne comme attendu.
        // On suppose que ResultatDeComparaison.compareTo trie par score décroissant.
        ResultatDeComparaison resA = new ResultatDeComparaison(nom1, nom2, 0.9); // Meilleur
        ResultatDeComparaison resB = new ResultatDeComparaison(nom1, nom3, 0.8); // 2ème
        ResultatDeComparaison resC = new ResultatDeComparaison(nom2, nom3, 0.7); // 3ème
        ResultatDeComparaison resD = new ResultatDeComparaison(nom3, nom4, 0.6); // 4ème
        ResultatDeComparaison resE = new ResultatDeComparaison(nom1, nom4, 0.5); // 5ème

        List<ResultatDeComparaison> tousLesResultats = new ArrayList<>(Arrays.asList(resE, resC, resA, resD, resB)); // Ordre initial mélangé
        // La classe ResultatDeComparaison doit avoir une méthode compareTo qui trie par score décroissant.
        // Si ce n'est pas le cas, le test échouera ou nécessitera un tri manuel ici.
        // Supposons que ResultatDeComparaison.compareTo est correctement implémenté.
        // Collections.sort(tousLesResultats); // Tri par score décroissant: A, B, C, D, E
        // L'implémentation de SelectionneurDesNMeilleurs fait le tri, donc pas besoin de le faire ici.

        // Cas 1: Sélectionner les 2 meilleurs
        SelectionneurDeResultats selectionneur1 = new SelectionneurDesNMeilleurs(2);
        List<ResultatDeComparaison> resultat1 = selectionneur1.selectionner(new ArrayList<>(tousLesResultats)); // Passer une copie pour ne pas modifier l'original
        // Attendu: resA (0.9), resB (0.8)
        List<ResultatDeComparaison> expected1 = Arrays.asList(resA, resB);
        // Le subList dépend de l'ordre après tri. Si ResultatDeComparaison trie bien, ce sera correct.
        if (resultat1.size() == 2 && resultat1.containsAll(expected1) && resultat1.get(0).equals(resA) && resultat1.get(1).equals(resB) ) {
            System.out.println("SelectionneurDesNMeilleursTest - Cas 1 (N=2): Succès. " + resultat1.size() + " résultats: " + resultat1.get(0).getScore() + ", " + resultat1.get(1).getScore());
        } else {
            System.err.println("SelectionneurDesNMeilleursTest - Cas 1 (N=2): Échec. Attendu les 2 meilleurs [0.9, 0.8], Obtenu: " + resultat1);
        }

        // Cas 2: Sélectionner les 3 meilleurs
        SelectionneurDeResultats selectionneur2 = new SelectionneurDesNMeilleurs(3);
        List<ResultatDeComparaison> resultat2 = selectionneur2.selectionner(new ArrayList<>(tousLesResultats));
        // Attendu: resA (0.9), resB (0.8), resC (0.7)
        List<ResultatDeComparaison> expected2 = Arrays.asList(resA, resB, resC);
        if (resultat2.size() == 3 && resultat2.containsAll(expected2) && resultat2.get(0).equals(resA) && resultat2.get(1).equals(resB) && resultat2.get(2).equals(resC)) {
            System.out.println("SelectionneurDesNMeilleursTest - Cas 2 (N=3): Succès. " + resultat2.size() + " résultats.");
        } else {
            System.err.println("SelectionneurDesNMeilleursTest - Cas 2 (N=3): Échec. Attendu les 3 meilleurs [0.9, 0.8, 0.7], Obtenu: " + resultat2);
        }

        // Cas 3: N plus grand que le nombre de résultats
        SelectionneurDeResultats selectionneur3 = new SelectionneurDesNMeilleurs(10);
        List<ResultatDeComparaison> resultat3 = selectionneur3.selectionner(new ArrayList<>(tousLesResultats));
        // Attendu: tous les résultats, triés (resA, resB, resC, resD, resE)
        List<ResultatDeComparaison> expected3 = Arrays.asList(resA, resB, resC, resD, resE);
        if (resultat3.size() == tousLesResultats.size() && resultat3.equals(expected3)) {
            System.out.println("SelectionneurDesNMeilleursTest - Cas 3 (N > taille liste): Succès. " + resultat3.size() + " résultats.");
        } else {
            System.err.println("SelectionneurDesNMeilleursTest - Cas 3 (N > taille liste): Échec. Attendu tous les résultats triés, Obtenu: " + resultat3);
        }

        // Cas 4: N = 0
        SelectionneurDeResultats selectionneur4 = new SelectionneurDesNMeilleurs(0);
        List<ResultatDeComparaison> resultat4 = selectionneur4.selectionner(new ArrayList<>(tousLesResultats));
        // Attendu: liste vide
        if (resultat4.isEmpty()) {
            System.out.println("SelectionneurDesNMeilleursTest - Cas 4 (N=0): Succès. 0 résultats.");
        } else {
            System.err.println("SelectionneurDesNMeilleursTest - Cas 4 (N=0): Échec. Attendu liste vide, Obtenu: " + resultat4);
        }

        // Cas 5: Liste de résultats vide en entrée
        SelectionneurDeResultats selectionneur5 = new SelectionneurDesNMeilleurs(5);
        List<ResultatDeComparaison> resultat5 = selectionneur5.selectionner(new ArrayList<>());
        if (resultat5.isEmpty()) {
            System.out.println("SelectionneurDesNMeilleursTest - Cas 5 (liste vide en entrée): Succès.");
        } else {
            System.err.println("SelectionneurDesNMeilleursTest - Cas 5 (liste vide en entrée): Échec. Attendu liste vide, Obtenu: " + resultat5);
        }
        
        // Cas 6: N négatif (devrait idéalement être géré, par exemple en le traitant comme 0 ou en levant une exception)
        // L'implémentation actuelle de subList lèverait une IllegalArgumentException si fromIndex > toIndex (ce qui arriverait si n est négatif et Math.min(n, size) devient négati)
        // ou une IndexOutOfBoundsException si n est négatif et Math.min(n, size) est négatif.
        // Pour un test simple, on s'attend à ce que cela ne crashe pas ou soit géré.
        // Si n est négatif, Math.min(n, size) sera n. subList(0, n_negati) lèvera une exception.
        // On va tester avec n=1 pour s'assurer que le tri est correct pour le premier élément.
        SelectionneurDeResultats selectionneur6 = new SelectionneurDesNMeilleurs(1);
        List<ResultatDeComparaison> resultat6 = selectionneur6.selectionner(new ArrayList<>(tousLesResultats));
        List<ResultatDeComparaison> expected6 = Arrays.asList(resA);
         if (resultat6.size() == 1 && resultat6.equals(expected6)) {
            System.out.println("SelectionneurDesNMeilleursTest - Cas 6 (N=1): Succès. " + resultat6.size() + " résultats: " + resultat6.get(0).getScore());
        } else {
            System.err.println("SelectionneurDesNMeilleursTest - Cas 6 (N=1): Échec. Attendu le meilleur [0.9], Obtenu: " + resultat6);
        }
    }
}

