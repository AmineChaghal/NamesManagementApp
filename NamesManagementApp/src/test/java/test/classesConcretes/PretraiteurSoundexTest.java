package test.classesConcretes;

import interfaces.Pretraiteur;
import org.apache.commons.codec.language.Soundex;

import classesConcretes.PretraiteurSoundex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PretraiteurSoundexTest {

    public static void main(String[] args) {
        Pretraiteur pretraiteur = new PretraiteurSoundex();
        Soundex soundexEncoder = new Soundex(); // Pour vérifier les valeurs attendues

        // Cas 1: Noms classiques
        List<String> input1 = Arrays.asList("Robert", "Rupert", "Rubin");
        // Soundex: Robert -> R163, Rupert -> R163, Rubin -> R150
        List<String> expected1 = Arrays.asList(soundexEncoder.encode("Robert"), soundexEncoder.encode("Rupert"), soundexEncoder.encode("Rubin"));
        List<String> result1 = pretraiteur.traiter(input1);
        if (result1.equals(expected1)) {
            System.out.println("PretraiteurSoundexTest - Cas 1 (noms classiques): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 1 (noms classiques): Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Noms avec des variations qui devraient donner le même Soundex
        List<String> input2 = Arrays.asList("Smith", "Smythe");
        // Soundex: Smith -> S530, Smythe -> S530
        List<String> expected2 = Arrays.asList(soundexEncoder.encode("Smith"), soundexEncoder.encode("Smythe"));
        List<String> result2 = pretraiteur.traiter(input2);
        if (result2.equals(expected2)) {
            System.out.println("PretraiteurSoundexTest - Cas 2 (variations même code): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 2 (variations même code): Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Liste vide
        List<String> input3 = new ArrayList<>();
        List<String> expected3 = new ArrayList<>();
        List<String> result3 = pretraiteur.traiter(input3);
        if (result3.equals(expected3)) {
            System.out.println("PretraiteurSoundexTest - Cas 3 (liste vide): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 3 (liste vide): Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }

        // Cas 4: Liste avec un élément null (devrait devenir chaîne vide ou code spécifique)
        List<String> input4 = new ArrayList<>();
        input4.add("Test");
        input4.add(null);
        input4.add("Exemple");
        // L'implémentation actuelle retourne "" pour null ou chaîne vide.
        List<String> expected4 = Arrays.asList(soundexEncoder.encode("Test"), "", soundexEncoder.encode("Exemple"));
        List<String> result4 = pretraiteur.traiter(input4);
        if (result4.equals(expected4)) {
            System.out.println("PretraiteurSoundexTest - Cas 4 (avec null): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 4 (avec null): Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }

        // Cas 5: Chaîne vide (devrait retourner "" comme géré dans le code)
        List<String> input5 = Arrays.asList("");
        List<String> expected5 = Arrays.asList("");
        List<String> result5 = pretraiteur.traiter(input5);
        if (result5.equals(expected5)) {
            System.out.println("PretraiteurSoundexTest - Cas 5 (chaîne vide): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 5 (chaîne vide): Échec. Attendu: " + expected5 + ", Obtenu: " + result5);
        }
        
        // Cas 6: Chaîne avec uniquement des caractères non alphabétiques (Soundex retourne souvent le premier caractère et des zéros)
        // L'implémentation Apache Commons Soundex est robuste et gère cela.
        // Par exemple, "123" pourrait retourner "0000" ou un code basé sur le premier caractère si c'est une lettre.
        // "@#$" -> Soundex retourne "0000" ou code basé sur la première lettre si présente.
        // L'implémentation actuelle de PretraiteurSoundex retourne soundex.encode() directement.
        // Soundex de Apache Commons pour "@#$" retourne "A000" si on considère A comme première lettre, ou "0000".
        // Pour être précis, testons avec une chaîne comme "Washington"
        List<String> input6 = Arrays.asList("Washington");
        List<String> expected6 = Arrays.asList(soundexEncoder.encode("Washington")); // W252
        List<String> result6 = pretraiteur.traiter(input6);
        if (result6.equals(expected6)) {
            System.out.println("PretraiteurSoundexTest - Cas 6 (nom long): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 6 (nom long): Échec. Attendu: " + expected6 + ", Obtenu: " + result6);
        }

        // Cas 7: Liste null
        List<String> input7 = null;
        List<String> expected7 = new ArrayList<>();
        List<String> result7 = pretraiteur.traiter(input7);
        if (result7.equals(expected7)) {
            System.out.println("PretraiteurSoundexTest - Cas 7 (liste null): Succès");
        } else {
            System.err.println("PretraiteurSoundexTest - Cas 7 (liste null): Échec. Attendu: " + expected7 + ", Obtenu: " + result7);
        }
    }
}

