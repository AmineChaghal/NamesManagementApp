package test.classesConcretes;

import interfaces.Pretraiteur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.PretraiteurConversionMinuscules;

public class PretraiteurConversionMinusculesTest {

    public static void main(String[] args) {
        Pretraiteur pretraiteur = new PretraiteurConversionMinuscules();

        // Cas 1: Chaînes avec majuscules et minuscules
        List<String> input1 = Arrays.asList("Alice", "DUPONT", "Jean-Pierre");
        List<String> expected1 = Arrays.asList("alice", "dupont", "jean-pierre");
        List<String> result1 = pretraiteur.traiter(input1);
        if (result1.equals(expected1)) {
            System.out.println("PretraiteurConversionMinusculesTest - Cas 1: Succès");
        } else {
            System.err.println("PretraiteurConversionMinusculesTest - Cas 1: Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Chaînes déjà en minuscules
        List<String> input2 = Arrays.asList("bob", "martin");
        List<String> expected2 = Arrays.asList("bob", "martin");
        List<String> result2 = pretraiteur.traiter(input2);
        if (result2.equals(expected2)) {
            System.out.println("PretraiteurConversionMinusculesTest - Cas 2 (déjà minuscules): Succès");
        } else {
            System.err.println("PretraiteurConversionMinusculesTest - Cas 2 (déjà minuscules): Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Liste vide
        List<String> input3 = new ArrayList<>();
        List<String> expected3 = new ArrayList<>();
        List<String> result3 = pretraiteur.traiter(input3);
        if (result3.equals(expected3)) {
            System.out.println("PretraiteurConversionMinusculesTest - Cas 3 (liste vide): Succès");
        } else {
            System.err.println("PretraiteurConversionMinusculesTest - Cas 3 (liste vide): Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }

        // Cas 4: Liste avec un élément null (devrait devenir chaîne vide selon l'implémentation)
        List<String> input4 = new ArrayList<>();
        input4.add("Valide");
        input4.add(null);
        input4.add("AUTRE");
        List<String> expected4 = Arrays.asList("valide", "", "autre");
        List<String> result4 = pretraiteur.traiter(input4);
        if (result4.equals(expected4)) {
            System.out.println("PretraiteurConversionMinusculesTest - Cas 4 (avec null): Succès");
        } else {
            System.err.println("PretraiteurConversionMinusculesTest - Cas 4 (avec null): Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }
        
        // Cas 5: Liste entièrement null
        List<String> input5 = null;
        List<String> expected5 = new ArrayList<>(); // L'implémentation actuelle retourne une liste vide pour une entrée null
        List<String> result5 = pretraiteur.traiter(input5);
        if (result5.equals(expected5)) {
            System.out.println("PretraiteurConversionMinusculesTest - Cas 5 (liste null): Succès");
        } else {
            System.err.println("PretraiteurConversionMinusculesTest - Cas 5 (liste null): Échec. Attendu: " + expected5 + ", Obtenu: " + result5);
        }

        // Cas 6: Chaînes avec chiffres et symboles
        List<String> input6 = Arrays.asList("Test123!", "@Example");
        List<String> expected6 = Arrays.asList("test123!", "@example");
        List<String> result6 = pretraiteur.traiter(input6);
        if (result6.equals(expected6)) {
            System.out.println("PretraiteurConversionMinusculesTest - Cas 6 (chiffres/symboles): Succès");
        } else {
            System.err.println("PretraiteurConversionMinusculesTest - Cas 6 (chiffres/symboles): Échec. Attendu: " + expected6 + ", Obtenu: " + result6);
        }
    }
}

