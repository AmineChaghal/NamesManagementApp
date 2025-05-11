package test.classesConcretes;

import interfaces.Pretraiteur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.PretraiteurSuppressionPonctuation;

public class PretraiteurSuppressionPonctuationTest {

    public static void main(String[] args) {
        Pretraiteur pretraiteur = new PretraiteurSuppressionPonctuation();

        // Cas 1: Chaînes avec ponctuation diverse
        List<String> input1 = Arrays.asList("Jean-Marc!", "(Dupont)", "O'Connell, Jr.");
        // Le regex [^\p{L}\p{N}\s-] conserve lettres, chiffres, espaces, tirets.
        List<String> expected1 = Arrays.asList("Jean-Marc", "Dupont", "OConnell Jr");
        List<String> result1 = pretraiteur.traiter(input1);
        if (result1.equals(expected1)) {
            System.out.println("PretraiteurSuppressionPonctuationTest - Cas 1 (ponctuation diverse): Succès");
        } else {
            System.err.println("PretraiteurSuppressionPonctuationTest - Cas 1 (ponctuation diverse): Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Chaînes sans ponctuation à supprimer (ou avec ponctuation conservée comme le tiret)
        List<String> input2 = Arrays.asList("Jean Marc", "Dupont-Smith", "123 Main St");
        List<String> expected2 = Arrays.asList("Jean Marc", "Dupont-Smith", "123 Main St");
        List<String> result2 = pretraiteur.traiter(input2);
        if (result2.equals(expected2)) {
            System.out.println("PretraiteurSuppressionPonctuationTest - Cas 2 (sans ponctuation à supprimer): Succès");
        } else {
            System.err.println("PretraiteurSuppressionPonctuationTest - Cas 2 (sans ponctuation à supprimer): Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Liste vide
        List<String> input3 = new ArrayList<>();
        List<String> expected3 = new ArrayList<>();
        List<String> result3 = pretraiteur.traiter(input3);
        if (result3.equals(expected3)) {
            System.out.println("PretraiteurSuppressionPonctuationTest - Cas 3 (liste vide): Succès");
        } else {
            System.err.println("PretraiteurSuppressionPonctuationTest - Cas 3 (liste vide): Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }

        // Cas 4: Liste avec un élément null (devrait devenir chaîne vide)
        List<String> input4 = new ArrayList<>();
        input4.add("Avec!Point");
        input4.add(null);
        input4.add("SansPoint");
        List<String> expected4 = Arrays.asList("AvecPoint", "", "SansPoint");
        List<String> result4 = pretraiteur.traiter(input4);
        if (result4.equals(expected4)) {
            System.out.println("PretraiteurSuppressionPonctuationTest - Cas 4 (avec null): Succès");
        } else {
            System.err.println("PretraiteurSuppressionPonctuationTest - Cas 4 (avec null): Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }

        // Cas 5: Liste entièrement null
        List<String> input5 = null;
        List<String> expected5 = new ArrayList<>(); // L'implémentation actuelle retourne une liste vide
        List<String> result5 = pretraiteur.traiter(input5);
        if (result5.equals(expected5)) {
            System.out.println("PretraiteurSuppressionPonctuationTest - Cas 5 (liste null): Succès");
        } else {
            System.err.println("PretraiteurSuppressionPonctuationTest - Cas 5 (liste null): Échec. Attendu: " + expected5 + ", Obtenu: " + result5);
        }

        // Cas 6: Chaînes avec uniquement de la ponctuation
        List<String> input6 = Arrays.asList("!!!", ".,;:?!", "()[]{}");
        List<String> expected6 = Arrays.asList("", "", "");
        List<String> result6 = pretraiteur.traiter(input6);
        if (result6.equals(expected6)) {
            System.out.println("PretraiteurSuppressionPonctuationTest - Cas 6 (uniquement ponctuation): Succès");
        } else {
            System.err.println("PretraiteurSuppressionPonctuationTest - Cas 6 (uniquement ponctuation): Échec. Attendu: " + expected6 + ", Obtenu: " + result6);
        }
    }
}

