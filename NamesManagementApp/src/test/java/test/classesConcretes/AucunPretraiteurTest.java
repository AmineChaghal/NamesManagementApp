package test.classesConcretes;

import interfaces.Pretraiteur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.AucunPretraiteur;

public class AucunPretraiteurTest {

    public static void main(String[] args) {
        Pretraiteur pretraiteur = new AucunPretraiteur();

        // Cas 1: Liste de chaînes simple
        List<String> input1 = Arrays.asList("Alice", "Dupont");
        List<String> expected1 = Arrays.asList("Alice", "Dupont");
        List<String> result1 = pretraiteur.traiter(input1);
        if (result1.equals(expected1)) {
            System.out.println("AucunPretraiteurTest - Cas 1: Succès");
        } else {
            System.err.println("AucunPretraiteurTest - Cas 1: Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Liste de chaînes avec des espaces en début/fin (devrait rester inchangé)
        List<String> input2 = Arrays.asList(" Bob ", " Martin ");
        List<String> expected2 = Arrays.asList(" Bob ", " Martin ");
        List<String> result2 = pretraiteur.traiter(input2);
        if (result2.equals(expected2)) {
            System.out.println("AucunPretraiteurTest - Cas 2: Succès");
        } else {
            System.err.println("AucunPretraiteurTest - Cas 2: Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Liste vide
        List<String> input3 = new ArrayList<>();
        List<String> expected3 = new ArrayList<>();
        List<String> result3 = pretraiteur.traiter(input3);
        if (result3.equals(expected3)) {
            System.out.println("AucunPretraiteurTest - Cas 3: Succès");
        } else {
            System.err.println("AucunPretraiteurTest - Cas 3: Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }

        // Cas 4: Liste avec un seul élément
        List<String> input4 = Arrays.asList("TestSeul");
        List<String> expected4 = Arrays.asList("TestSeul");
        List<String> result4 = pretraiteur.traiter(input4);
        if (result4.equals(expected4)) {
            System.out.println("AucunPretraiteurTest - Cas 4: Succès");
        } else {
            System.err.println("AucunPretraiteurTest - Cas 4: Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }
    }
}

