package test.classesConcretes;

import interfaces.Pretraiteur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.PretraiteurSuppressionAccents;

public class PretraiteurSuppressionAccentsTest {

    public static void main(String[] args) {
        Pretraiteur pretraiteur = new PretraiteurSuppressionAccents();

        // Cas 1: Chaînes avec accents courants
        List<String> input1 = Arrays.asList("éàçôü", "ÉÀÇÔÜ", "Jérôme");
        List<String> expected1 = Arrays.asList("eacou", "EACOU", "Jerome");
        List<String> result1 = pretraiteur.traiter(input1);
        if (result1.equals(expected1)) {
            System.out.println("PretraiteurSuppressionAccentsTest - Cas 1 (accents courants): Succès");
        } else {
            System.err.println("PretraiteurSuppressionAccentsTest - Cas 1 (accents courants): Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Chaînes sans accents
        List<String> input2 = Arrays.asList("abc", "XYZ", "123");
        List<String> expected2 = Arrays.asList("abc", "XYZ", "123");
        List<String> result2 = pretraiteur.traiter(input2);
        if (result2.equals(expected2)) {
            System.out.println("PretraiteurSuppressionAccentsTest - Cas 2 (sans accents): Succès");
        } else {
            System.err.println("PretraiteurSuppressionAccentsTest - Cas 2 (sans accents): Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Liste vide
        List<String> input3 = new ArrayList<>();
        List<String> expected3 = new ArrayList<>();
        List<String> result3 = pretraiteur.traiter(input3);
        if (result3.equals(expected3)) {
            System.out.println("PretraiteurSuppressionAccentsTest - Cas 3 (liste vide): Succès");
        } else {
            System.err.println("PretraiteurSuppressionAccentsTest - Cas 3 (liste vide): Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }

        // Cas 4: Liste avec un élément null (devrait devenir chaîne vide selon l'implémentation)
        List<String> input4 = new ArrayList<>();
        input4.add("accentué");
        input4.add(null);
        input4.add("NORMAL");
        List<String> expected4 = Arrays.asList("accentue", "", "NORMAL");
        List<String> result4 = pretraiteur.traiter(input4);
        if (result4.equals(expected4)) {
            System.out.println("PretraiteurSuppressionAccentsTest - Cas 4 (avec null): Succès");
        } else {
            System.err.println("PretraiteurSuppressionAccentsTest - Cas 4 (avec null): Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }

        // Cas 5: Liste entièrement null
        List<String> input5 = null;
        List<String> expected5 = new ArrayList<>(); // L'implémentation actuelle retourne une liste vide
        List<String> result5 = pretraiteur.traiter(input5);
        if (result5.equals(expected5)) {
            System.out.println("PretraiteurSuppressionAccentsTest - Cas 5 (liste null): Succès");
        } else {
            System.err.println("PretraiteurSuppressionAccentsTest - Cas 5 (liste null): Échec. Attendu: " + expected5 + ", Obtenu: " + result5);
        }

        // Cas 6: Chaînes avec caractères spéciaux et chiffres (ne doivent pas être affectés)
        List<String> input6 = Arrays.asList("élève-123!", "@garçon");
        List<String> expected6 = Arrays.asList("eleve-123!", "@garcon");
        List<String> result6 = pretraiteur.traiter(input6);
        if (result6.equals(expected6)) {
            System.out.println("PretraiteurSuppressionAccentsTest - Cas 6 (spéciaux/chiffres): Succès");
        } else {
            System.err.println("PretraiteurSuppressionAccentsTest - Cas 6 (spéciaux/chiffres): Échec. Attendu: " + expected6 + ", Obtenu: " + result6);
        }
    }
}

