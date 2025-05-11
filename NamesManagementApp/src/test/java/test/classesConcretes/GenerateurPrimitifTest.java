package test.classesConcretes;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import interfaces.GenerateurDeCandidatsALaComparaison;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classesConcretes.GenerateurPrimitif;

public class GenerateurPrimitifTest {

    public static void main(String[] args) {
        GenerateurDeCandidatsALaComparaison generateur = new GenerateurPrimitif();

        // Noms pour les listes de test
        Nom nomA1 = new Nom("Alice A", Arrays.asList("Alice", "A"), "1");
        Nom nomA2 = new Nom("Bob B", Arrays.asList("Bob", "B"), "2");
        Nom nomB1 = new Nom("Charles C", Arrays.asList("Charles", "C"), "3");
        Nom nomB2 = new Nom("David D", Arrays.asList("David", "D"), "4");
        Nom nomB3 = new Nom("Eve E", Arrays.asList("Eve", "E"), "5");

        // Cas 1: Deux listes non vides
        List<Nom> liste1_1 = Arrays.asList(nomA1, nomA2);
        List<Nom> liste1_2 = Arrays.asList(nomB1, nomB2, nomB3);
        List<CoupleDeNoms> resultat1 = generateur.generer(liste1_1, liste1_2);

        // Attendu: 2 * 3 = 6 couples
        if (resultat1.size() == 6) {
            System.out.println("GenerateurPrimitifTest - Cas 1 (listes non vides): Succès. Nombre de couples: " + resultat1.size());
            // Vérification plus détaillée d'un couple (optionnel, mais bon pour la robustesse)
            boolean foundSpecificPair = false;
            for (CoupleDeNoms couple : resultat1) {
                if (couple.getNom1().equals(nomA1) && couple.getNom2().equals(nomB1)) {
                    foundSpecificPair = true;
                    break;
                }
            }
            if (foundSpecificPair) {
                System.out.println("GenerateurPrimitifTest - Cas 1: Vérification de couple spécifique: Succès.");
            } else {
                System.err.println("GenerateurPrimitifTest - Cas 1: Vérification de couple spécifique: Échec.");
            }
        } else {
            System.err.println("GenerateurPrimitifTest - Cas 1 (listes non vides): Échec. Attendu 6 couples, Obtenu: " + resultat1.size());
        }

        // Cas 2: Une liste vide, une liste non vide
        List<Nom> liste2_1 = new ArrayList<>();
        List<Nom> liste2_2 = Arrays.asList(nomB1, nomB2);
        List<CoupleDeNoms> resultat2 = generateur.generer(liste2_1, liste2_2);
        if (resultat2.isEmpty()) {
            System.out.println("GenerateurPrimitifTest - Cas 2 (une liste vide): Succès. Nombre de couples: " + resultat2.size());
        } else {
            System.err.println("GenerateurPrimitifTest - Cas 2 (une liste vide): Échec. Attendu 0 couples, Obtenu: " + resultat2.size());
        }

        // Cas 3: Deux listes vides
        List<Nom> liste3_1 = new ArrayList<>();
        List<Nom> liste3_2 = new ArrayList<>();
        List<CoupleDeNoms> resultat3 = generateur.generer(liste3_1, liste3_2);
        if (resultat3.isEmpty()) {
            System.out.println("GenerateurPrimitifTest - Cas 3 (deux listes vides): Succès. Nombre de couples: " + resultat3.size());
        } else {
            System.err.println("GenerateurPrimitifTest - Cas 3 (deux listes vides): Échec. Attendu 0 couples, Obtenu: " + resultat3.size());
        }

        // Cas 4: Listes avec un seul élément chacune
        List<Nom> liste4_1 = Arrays.asList(nomA1);
        List<Nom> liste4_2 = Arrays.asList(nomB1);
        List<CoupleDeNoms> resultat4 = generateur.generer(liste4_1, liste4_2);
        if (resultat4.size() == 1) {
            System.out.println("GenerateurPrimitifTest - Cas 4 (un seul élément par liste): Succès. Nombre de couples: " + resultat4.size());
            if (resultat4.get(0).getNom1().equals(nomA1) && resultat4.get(0).getNom2().equals(nomB1)) {
                 System.out.println("GenerateurPrimitifTest - Cas 4: Vérification du couple: Succès.");
            } else {
                 System.err.println("GenerateurPrimitifTest - Cas 4: Vérification du couple: Échec.");
            }
        } else {
            System.err.println("GenerateurPrimitifTest - Cas 4 (un seul élément par liste): Échec. Attendu 1 couple, Obtenu: " + resultat4.size());
        }
    }
}

