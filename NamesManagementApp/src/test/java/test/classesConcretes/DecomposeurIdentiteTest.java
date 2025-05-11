package test.classesConcretes;

import interfaces.DecomposeurDeNoms;
import java.util.Arrays;
import java.util.List;

import classesConcretes.DecomposeurIdentite;

import java.util.ArrayList;

public class DecomposeurIdentiteTest {

    public static void main(String[] args) {
        DecomposeurDeNoms decomposeur = new DecomposeurIdentite();

        // Cas 1: Nom simple
        String nom1 = "Jean Dupont";
        List<String> expected1 = Arrays.asList("Jean Dupont");
        List<String> result1 = decomposeur.construire(nom1);
        if (result1.equals(expected1)) {
            System.out.println("DecomposeurIdentiteTest - Cas 1 (nom simple): Succès");
        } else {
            System.err.println("DecomposeurIdentiteTest - Cas 1 (nom simple): Échec. Attendu: " + expected1 + ", Obtenu: " + result1);
        }

        // Cas 2: Nom avec plusieurs parties (doit rester inchangé)
        String nom2 = "Jean Marc Eric Dupont";
        List<String> expected2 = Arrays.asList("Jean Marc Eric Dupont");
        List<String> result2 = decomposeur.construire(nom2);
        if (result2.equals(expected2)) {
            System.out.println("DecomposeurIdentiteTest - Cas 2 (nom multiple): Succès");
        } else {
            System.err.println("DecomposeurIdentiteTest - Cas 2 (nom multiple): Échec. Attendu: " + expected2 + ", Obtenu: " + result2);
        }

        // Cas 3: Chaîne vide
        String nom3 = "";
        List<String> expected3 = Arrays.asList("");
        List<String> result3 = decomposeur.construire(nom3);
        if (result3.equals(expected3)) {
            System.out.println("DecomposeurIdentiteTest - Cas 3 (chaîne vide): Succès");
        } else {
            System.err.println("DecomposeurIdentiteTest - Cas 3 (chaîne vide): Échec. Attendu: " + expected3 + ", Obtenu: " + result3);
        }

        // Cas 4: Nom avec des caractères spéciaux
        String nom4 = "Jean-Marc O'Connell";
        List<String> expected4 = Arrays.asList("Jean-Marc O'Connell");
        List<String> result4 = decomposeur.construire(nom4);
        if (result4.equals(expected4)) {
            System.out.println("DecomposeurIdentiteTest - Cas 4 (caractères spéciaux): Succès");
        } else {
            System.err.println("DecomposeurIdentiteTest - Cas 4 (caractères spéciaux): Échec. Attendu: " + expected4 + ", Obtenu: " + result4);
        }
    }
}

