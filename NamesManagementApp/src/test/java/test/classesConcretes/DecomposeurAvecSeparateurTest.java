package test.classesConcretes;

import interfaces.DecomposeurDeNoms;
import java.util.Arrays;
import java.util.List;

import classesConcretes.DecomposeurAvecSeparateur;

public class DecomposeurAvecSeparateurTest {

    public static void main(String[] args) {
        // Cas 1: Séparateur par défaut (espace)
        DecomposeurDeNoms decomposeur1 = new DecomposeurAvecSeparateur();
        String nom1 = "Jean Marc Dupont";
        List<String> expected1 = Arrays.asList("Jean", "Marc", "Dupont");
        List<String> result1 = decomposeur1.construire(nom1);
        if (result1.equals(expected1)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 1 (espace): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 1 (espace): Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Séparateur par défaut (virgule)
        String nom2 = "Dupont,Jean,Marc";
        List<String> expected2 = Arrays.asList("Dupont", "Jean", "Marc");
        List<String> result2 = decomposeur1.construire(nom2);
        if (result2.equals(expected2)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 2 (virgule): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 2 (virgule): Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Séparateur par défaut (underscore)
        String nom3 = "Jean_Marc_Dupont";
        List<String> expected3 = Arrays.asList("Jean", "Marc", "Dupont");
        List<String> result3 = decomposeur1.construire(nom3);
        if (result3.equals(expected3)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 3 (underscore): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 3 (underscore): Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }
        
        // Cas 4: Séparateur par défaut (double étoile)
        String nom4 = "Jean**Marc**Dupont";
        List<String> expected4 = Arrays.asList("Jean", "Marc", "Dupont");
        List<String> result4 = decomposeur1.construire(nom4);
        if (result4.equals(expected4)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 4 (double étoile): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 4 (double étoile): Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }

        // Cas 5: Séparateur personnalisé (point-virgule)
        DecomposeurDeNoms decomposeur2 = new DecomposeurAvecSeparateur(";+");
        String nom5 = "Jean;Marc;Dupont";
        List<String> expected5 = Arrays.asList("Jean", "Marc", "Dupont");
        List<String> result5 = decomposeur2.construire(nom5);
        if (result5.equals(expected5)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 5 (point-virgule): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 5 (point-virgule): Échec. Attendu: " + expected5 + ", Obtenu: " + result5);
        }

        // Cas 6: Nom avec espaces en début/fin (trim doit fonctionner)
        String nom6 = "  Jean Marc Dupont  ";
        List<String> expected6 = Arrays.asList("Jean", "Marc", "Dupont");
        List<String> result6 = decomposeur1.construire(nom6);
        if (result6.equals(expected6)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 6 (trim): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 6 (trim): Échec. Attendu: " + expected6 + ", Obtenu: " + result6);
        }

        // Cas 7: Séparateurs multiples et consécutifs (doit gérer les éléments vides si le regex n'est pas parfait, mais le split de base les gère)
        String nom7 = "Jean  Marc,,Dupont__Test"; // Regex par défaut \s+|,+|_+ gère bien ça
        List<String> expected7 = Arrays.asList("Jean", "Marc", "Dupont", "Test");
        List<String> result7 = decomposeur1.construire(nom7);
        if (result7.equals(expected7)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 7 (séparateurs multiples): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 7 (séparateurs multiples): Échec. Attendu: " + expected7 + ", Obtenu: " + result7);
        }

        // Cas 8: Chaîne vide
        String nom8 = "";
        List<String> expected8 = Arrays.asList(""); // split sur chaîne vide donne un array avec une chaîne vide
        List<String> result8 = decomposeur1.construire(nom8);
        if (result8.equals(expected8)) {
            System.out.println("DecomposeurAvecSeparateurTest - Cas 8 (chaîne vide): Succès");
        } else {
            System.err.println("DecomposeurAvecSeparateurTest - Cas 8 (chaîne vide): Échec. Attendu: " + expected8 + ", Obtenu: " + result8);
        }

        // Cas 9: Chaîne avec uniquement des séparateurs
        String nom9 = "  , __ ** ";
        // trim() -> ", __ **" -> split -> ["", "", "", ""] si les séparateurs sont aux extrémités après trim
        // ou juste [""] si le trim enlève tout et qu'on split une chaine vide.
        // Le comportement de split sur une chaine vide est de retourner [""].
        // Si la chaine après trim est par ex ",,", split(",+") donne ["", ""].
        // Avec "  , __ ** ".trim() -> ", __ **".split("\\s+|,+|_+|\\*\\*+")
        // -> ["", "", "", ""] (un vide avant la première virgule, un vide entre virgule et underscore, etc)
        List<String> result9 = decomposeur1.construire(nom9);
        // Attendu: dépend de l'implémentation exacte de split et du regex. Souvent, cela produit des chaînes vides.
        // Pour le regex `\s+|,+|_+|\*\*+` et la chaîne 
}}