package test.classesConcretes;

import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.SelectionneurDeResultats;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.SelectionneurAvecSeuil;

public class SelectionneurAvecSeuilTest {

    public static void main(String[] args) {
        // Noms et couples pour les tests
        Nom nom1 = new Nom("N1", Arrays.asList("N1"), "1");
        Nom nom2 = new Nom("N2", Arrays.asList("N2"), "2");
        Nom nom3 = new Nom("N3", Arrays.asList("N3"), "3");
        Nom nom4 = new Nom("N4", Arrays.asList("N4"), "4");

        ResultatDeComparaison res1 = new ResultatDeComparaison(nom1, nom2, 0.8);
        ResultatDeComparaison res2 = new ResultatDeComparaison(nom1, nom3, 0.5);
        ResultatDeComparaison res3 = new ResultatDeComparaison(nom2, nom3, 0.9);
        ResultatDeComparaison res4 = new ResultatDeComparaison(nom3, nom4, 0.4);
        ResultatDeComparaison res5 = new ResultatDeComparaison(nom1, nom4, 0.7);

        List<ResultatDeComparaison> tousLesResultats = Arrays.asList(res1, res2, res3, res4, res5);

        // Cas 1: Seuil élevé, peu de résultats
        SelectionneurDeResultats selectionneur1 = new SelectionneurAvecSeuil(0.8);
        List<ResultatDeComparaison> resultat1 = selectionneur1.selectionner(tousLesResultats);
        // Attendu: res1 (0.8), res3 (0.9)
        List<ResultatDeComparaison> expected1 = Arrays.asList(res1, res3);
        if (resultat1.size() == expected1.size() && resultat1.containsAll(expected1)) {
            System.out.println("SelectionneurAvecSeuilTest - Cas 1 (seuil 0.8): Succès. " + resultat1.size() + " résultats.");
        } else {
            System.err.println("SelectionneurAvecSeuilTest - Cas 1 (seuil 0.8): Échec. Attendu: " + expected1 + ", Obtenu: " + resultat1);
        }

        // Cas 2: Seuil bas, plus de résultats
        SelectionneurDeResultats selectionneur2 = new SelectionneurAvecSeuil(0.5);
        List<ResultatDeComparaison> resultat2 = selectionneur2.selectionner(tousLesResultats);
        // Attendu: res1 (0.8), res2 (0.5), res3 (0.9), res5 (0.7)
        List<ResultatDeComparaison> expected2 = Arrays.asList(res1, res2, res3, res5);
        if (resultat2.size() == expected2.size() && resultat2.containsAll(expected2)) {
            System.out.println("SelectionneurAvecSeuilTest - Cas 2 (seuil 0.5): Succès. " + resultat2.size() + " résultats.");
        } else {
            System.err.println("SelectionneurAvecSeuilTest - Cas 2 (seuil 0.5): Échec. Attendu: " + expected2 + ", Obtenu: " + resultat2);
        }

        // Cas 3: Seuil très élevé, aucun résultat
        SelectionneurDeResultats selectionneur3 = new SelectionneurAvecSeuil(1.0);
        List<ResultatDeComparaison> resultat3 = selectionneur3.selectionner(tousLesResultats);
        // Attendu: liste vide
        if (resultat3.isEmpty()) {
            System.out.println("SelectionneurAvecSeuilTest - Cas 3 (seuil 1.0): Succès. 0 résultats.");
        } else {
            System.err.println("SelectionneurAvecSeuilTest - Cas 3 (seuil 1.0): Échec. Attendu liste vide, Obtenu: " + resultat3);
        }

        // Cas 4: Seuil très bas (ou 0), tous les résultats (sauf si score < 0, ce qui n'est pas le cas ici)
        SelectionneurDeResultats selectionneur4 = new SelectionneurAvecSeuil(0.0);
        List<ResultatDeComparaison> resultat4 = selectionneur4.selectionner(tousLesResultats);
        // Attendu: tousLesResultats
        if (resultat4.size() == tousLesResultats.size() && resultat4.containsAll(tousLesResultats)) {
            System.out.println("SelectionneurAvecSeuilTest - Cas 4 (seuil 0.0): Succès. " + resultat4.size() + " résultats.");
        } else {
            System.err.println("SelectionneurAvecSeuilTest - Cas 4 (seuil 0.0): Échec. Attendu: " + tousLesResultats + ", Obtenu: " + resultat4);
        }

        // Cas 5: Liste de résultats vide
        SelectionneurDeResultats selectionneur5 = new SelectionneurAvecSeuil(0.5);
        List<ResultatDeComparaison> resultat5 = selectionneur5.selectionner(new ArrayList<>());
        if (resultat5.isEmpty()) {
            System.out.println("SelectionneurAvecSeuilTest - Cas 5 (liste vide en entrée): Succès.");
        } else {
            System.err.println("SelectionneurAvecSeuilTest - Cas 5 (liste vide en entrée): Échec. Attendu liste vide, Obtenu: " + resultat5);
        }
    }
}

