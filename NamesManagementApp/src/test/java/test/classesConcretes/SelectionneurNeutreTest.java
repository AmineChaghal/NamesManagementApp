package test.classesConcretes;

import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.SelectionneurDeResultats;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.SelectionneurNeutre;

public class SelectionneurNeutreTest {

    public static void main(String[] args) {
        SelectionneurDeResultats selectionneur = new SelectionneurNeutre();

        // Noms et couples pour les tests
        Nom nom1 = new Nom("N1", Arrays.asList("N1"), "1");
        Nom nom2 = new Nom("N2", Arrays.asList("N2"), "2");
        Nom nom3 = new Nom("N3", Arrays.asList("N3"), "3");

        ResultatDeComparaison res1 = new ResultatDeComparaison(nom1, nom2, 0.8);
        ResultatDeComparaison res2 = new ResultatDeComparaison(nom1, nom3, 0.5);
        ResultatDeComparaison res3 = new ResultatDeComparaison(nom2, nom3, 0.9);

        // Cas 1: Liste de résultats non vide
        List<ResultatDeComparaison> input1 = Arrays.asList(res1, res2, res3);
        List<ResultatDeComparaison> result1 = selectionneur.selectionner(input1);
        // Attendu: la même liste, car le sélectionneur est neutre
        if (result1.equals(input1) && result1.size() == input1.size()) {
            System.out.println("SelectionneurNeutreTest - Cas 1 (liste non vide): Succès. " + result1.size() + " résultats.");
        } else {
            System.err.println("SelectionneurNeutreTest - Cas 1 (liste non vide): Échec. Attendu: " + input1 + ", Obtenu: " + result1);
        }

        // Cas 2: Liste de résultats vide
        List<ResultatDeComparaison> input2 = new ArrayList<>();
        List<ResultatDeComparaison> result2 = selectionneur.selectionner(input2);
        if (result2.isEmpty()) {
            System.out.println("SelectionneurNeutreTest - Cas 2 (liste vide): Succès.");
        } else {
            System.err.println("SelectionneurNeutreTest - Cas 2 (liste vide): Échec. Attendu liste vide, Obtenu: " + result2);
        }

        // Cas 3: Vérifier que la référence de la liste retournée est la même que celle en entrée
        // (C'est le comportement actuel de SelectionneurNeutre)
        List<ResultatDeComparaison> input3 = new ArrayList<>(Arrays.asList(res1));
        List<ResultatDeComparaison> result3 = selectionneur.selectionner(input3);
        if (result3 == input3) { // Comparaison de référence
            System.out.println("SelectionneurNeutreTest - Cas 3 (même référence): Succès.");
        } else {
            System.err.println("SelectionneurNeutreTest - Cas 3 (même référence): Échec. Les références ne sont pas les mêmes.");
        }
    }
}

