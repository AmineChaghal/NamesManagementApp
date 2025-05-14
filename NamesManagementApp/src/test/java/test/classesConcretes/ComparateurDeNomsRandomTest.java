package test.classesConcretes;

import classesPorteusesDeDonnees.Nom;
import interfaces.ComparateurDeNoms;
import java.util.Arrays;

import classesConcretes.ComparateurDeNomsRandom;

public class ComparateurDeNomsRandomTest {

    public static void main(String[] args) {
        ComparateurDeNoms comparateur = new ComparateurDeNomsRandom();

        // Création de noms de test (leur contenu importe peu pour ce comparateur)
        Nom nom1 = new Nom("Test Nom1", Arrays.asList("Test", "Nom1"), "1");
        Nom nom2 = new Nom("Test Nom2", Arrays.asList("Test", "Nom2"), "2");

        // Cas 1: Vérifier que le résultat est dans l'intervalle [0.0, 1.0)
        double resultat1 = comparateur.comparer(nom1, nom2);
        if (resultat1 >= 0.0f && resultat1 < 1.0) {
            System.out.println("ComparateurDeNomsRandomTest - Cas 1 (intervalle): Succès. Résultat: " + resultat1);
        } else {
            System.err.println("ComparateurDeNomsRandomTest - Cas 1 (intervalle): Échec. Résultat obtenu: " + resultat1);
        }

        // Cas 2: Vérifier que deux appels successifs donnent potentiellement des résultats différents
        // (Ce n'est pas une garantie absolue avec du random, mais une indication)
        double resultat2a = comparateur.comparer(nom1, nom2);
        double resultat2b = comparateur.comparer(nom1, nom2);
        // On ne peut pas affirmer qu'ils SERONT différents, juste observer.
        System.out.println("ComparateurDeNomsRandomTest - Cas 2: Appel 1: " + resultat2a + ", Appel 2: " + resultat2b);
        if (resultat2a >= 0.0f && resultat2a < 1.0f && resultat2b >=0.0f && resultat2b < 1.0) {
             System.out.println("ComparateurDeNomsRandomTest - Cas 2 (intervalles multiples): Succès.");
        } else {
             System.err.println("ComparateurDeNomsRandomTest - Cas 2 (intervalles multiples): Échec.");
        }

        // Cas 3: Comparaison d'un nom avec lui-même
        double resultat3 = comparateur.comparer(nom1, nom1);
        if (resultat3 >= 0.0f && resultat3 < 1.0) {
            System.out.println("ComparateurDeNomsRandomTest - Cas 3 (nom avec lui-même): Succès. Résultat: " + resultat3);
        } else {
            System.err.println("ComparateurDeNomsRandomTest - Cas 3 (nom avec lui-même): Échec. Résultat obtenu: " + resultat3);
        }
    }
}

