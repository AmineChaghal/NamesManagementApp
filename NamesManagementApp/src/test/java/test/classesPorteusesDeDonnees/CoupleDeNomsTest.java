package test.classesPorteusesDeDonnees;

import java.util.Arrays;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;

public class CoupleDeNomsTest {

    public static void main(String[] args) {
        // Création de Noms pour le test
        Nom nom1 = new Nom("Alice Wonderland", Arrays.asList("Alice", "Wonderland"), "1");
        Nom nom2 = new Nom("Bob The Builder", Arrays.asList("Bob", "The", "Builder"), "2");
        Nom nom3 = new Nom("Charlie Brown", Arrays.asList("Charlie", "Brown"), "3");

        // Cas 1: Création d'un couple et vérification des getters
        CoupleDeNoms couple1 = new CoupleDeNoms(nom1, nom2);
        boolean cas1_ok = true;
        if (!couple1.getNom1().equals(nom1)) {
            System.err.println("CoupleDeNomsTest - Cas 1: Échec. getNom1() ne retourne pas le bon objet.");
            cas1_ok = false;
        }
        if (!couple1.getNom2().equals(nom2)) {
            System.err.println("CoupleDeNomsTest - Cas 1: Échec. getNom2() ne retourne pas le bon objet.");
            cas1_ok = false;
        }
        if (cas1_ok) {
            System.out.println("CoupleDeNomsTest - Cas 1 (création et getters): Succès");
        }

        // Cas 2: Création d'un autre couple avec des noms différents
        CoupleDeNoms couple2 = new CoupleDeNoms(nom2, nom3);
        boolean cas2_ok = true;
        if (!couple2.getNom1().equals(nom2)) {
            System.err.println("CoupleDeNomsTest - Cas 2: Échec. getNom1() ne retourne pas le bon objet.");
            cas2_ok = false;
        }
        if (!couple2.getNom2().equals(nom3)) {
            System.err.println("CoupleDeNomsTest - Cas 2: Échec. getNom2() ne retourne pas le bon objet.");
            cas2_ok = false;
        }
        if (cas2_ok) {
            System.out.println("CoupleDeNomsTest - Cas 2 (autre couple): Succès");
        }

        // Cas 3: Couple avec le même nom pour nom1 et nom2
        CoupleDeNoms couple3 = new CoupleDeNoms(nom1, nom1);
        boolean cas3_ok = true;
        if (!couple3.getNom1().equals(nom1)) {
            System.err.println("CoupleDeNomsTest - Cas 3: Échec. getNom1() ne retourne pas le bon objet.");
            cas3_ok = false;
        }
        if (!couple3.getNom2().equals(nom1)) {
            System.err.println("CoupleDeNomsTest - Cas 3: Échec. getNom2() ne retourne pas le bon objet.");
            cas3_ok = false;
        }
        if (cas3_ok) {
            System.out.println("CoupleDeNomsTest - Cas 3 (noms identiques dans le couple): Succès");
        }
        
        // Cas 4: Test avec des noms null (si le constructeur le permet, sinon s'attendre à une exception)
        // L'implémentation actuelle de Nom et CoupleDeNoms ne semble pas gérer les null explicitement
        // et pourrait lever NullPointerException si les méthodes de Nom sont appelées sur un nom null.
        // Pour un test simple, on évite ce cas ou on le documente comme non testé sans gestion d'erreur spécifique.
        // System.out.println("CoupleDeNomsTest - Cas 4: Test avec noms null (non exécuté pour éviter NPE sans gestion)");

    }
}

