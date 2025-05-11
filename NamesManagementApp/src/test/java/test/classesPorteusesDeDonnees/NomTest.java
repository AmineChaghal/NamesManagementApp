package test.classesPorteusesDeDonnees;

import java.util.Arrays;
import java.util.List;

import classesPorteusesDeDonnees.Nom;

import java.util.ArrayList;

public class NomTest {

    public static void main(String[] args) {
        // Cas 1: Création d'un objet Nom et vérification des getters
        String nomComplet1 = "Jean Dupont";
        List<String> nomDecompose1 = Arrays.asList("Jean", "Dupont");
        String id1 = "1";
        Nom nom1 = new Nom(nomComplet1, nomDecompose1, id1);

        boolean cas1_ok = true;
        if (!nom1.getNomComplet().equals(nomComplet1)) {
            System.err.println("NomTest - Cas 1: Échec. getNomComplet() a retourné " + nom1.getNomComplet());
            cas1_ok = false;
        }
        if (!nom1.getNomDecompose().equals(nomDecompose1)) {
            System.err.println("NomTest - Cas 1: Échec. getNomDecompose() a retourné " + nom1.getNomDecompose());
            cas1_ok = false;
        }
        if (nom1.getId() != id1) {
            System.err.println("NomTest - Cas 1: Échec. getId() a retourné " + nom1.getId());
            cas1_ok = false;
        }

        if (cas1_ok) {
            System.out.println("NomTest - Cas 1 (création et getters): Succès");
        }

        // Cas 2: Autre instance de Nom
        String nomComplet2 = "Marie Curie-Skłodowska";
        List<String> nomDecompose2 = Arrays.asList("Marie", "Curie-Skłodowska");
        String id2 = "101";
        Nom nom2 = new Nom(nomComplet2, nomDecompose2, id2);

        boolean cas2_ok = true;
        if (!nom2.getNomComplet().equals(nomComplet2)) {
            System.err.println("NomTest - Cas 2: Échec. getNomComplet() incorrect.");
            cas2_ok = false;
        }
        if (!nom2.getNomDecompose().equals(nomDecompose2)) {
            System.err.println("NomTest - Cas 2: Échec. getNomDecompose() incorrect.");
            cas2_ok = false;
        }
        if (nom2.getId() != id2) {
            System.err.println("NomTest - Cas 2: Échec. getId() incorrect.");
            cas2_ok = false;
        }
        if (cas2_ok) {
            System.out.println("NomTest - Cas 2 (autre instance): Succès");
        }

        // Cas 3: Nom avec liste de décomposition vide
        String nomComplet3 = "Mononyme";
        List<String> nomDecompose3 = new ArrayList<>();
        String id3 = "42";
        Nom nom3 = new Nom(nomComplet3, nomDecompose3, id3);
        if (nom3.getNomDecompose().isEmpty()) {
            System.out.println("NomTest - Cas 3 (décomposition vide): Succès");
        } else {
            System.err.println("NomTest - Cas 3 (décomposition vide): Échec. Attendu liste vide, Obtenu: " + nom3.getNomDecompose());
        }

        // Note sur les setters:
        // Les méthodes setNomDecompose(List<String> partiesNom) et setNomComplet(List<String> nomComplet2)
        // sont actuellement des stubs (TODO Auto-generated method stub) et ne sont pas testées ici.
        // Si elles étaient implémentées, des tests supplémentaires seraient nécessaires.
        System.out.println("NomTest: Note - Les setters setNomDecompose et setNomComplet sont des stubs et non testés.");

    }
}

