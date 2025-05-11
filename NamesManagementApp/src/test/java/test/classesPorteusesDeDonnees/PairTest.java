package test.classesPorteusesDeDonnees;

import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.Pair;

public class PairTest {

    public static void main(String[] args) {
        // Cas 1: Paire de String et Integer
        String first1 = "TestString";
        Integer second1 = 123;
        Pair<String, Integer> pair1 = new Pair<>(first1, second1);

        boolean cas1_ok = true;
        if (!pair1.getFirst().equals(first1)) {
            System.err.println("PairTest - Cas 1: Échec. getFirst() a retourné " + pair1.getFirst());
            cas1_ok = false;
        }
        if (!pair1.getSecond().equals(second1)) {
            System.err.println("PairTest - Cas 1: Échec. getSecond() a retourné " + pair1.getSecond());
            cas1_ok = false;
        }
        if (cas1_ok) {
            System.out.println("PairTest - Cas 1 (String, Integer): Succès");
        }

        // Cas 2: Paire de Double et Boolean
        Double first2 = 3.14159;
        Boolean second2 = true;
        Pair<Double, Boolean> pair2 = new Pair<>(first2, second2);

        boolean cas2_ok = true;
        if (!pair2.getFirst().equals(first2)) {
            System.err.println("PairTest - Cas 2: Échec. getFirst() a retourné " + pair2.getFirst());
            cas2_ok = false;
        }
        if (!pair2.getSecond().equals(second2)) {
            System.err.println("PairTest - Cas 2: Échec. getSecond() a retourné " + pair2.getSecond());
            cas2_ok = false;
        }
        if (cas2_ok) {
            System.out.println("PairTest - Cas 2 (Double, Boolean): Succès");
        }

        // Cas 3: Paire avec des valeurs null (si permis par les types)
        String first3 = null;
        Integer second3 = null;
        Pair<String, Integer> pair3 = new Pair<>(first3, second3);

        boolean cas3_ok = true;
        if (pair3.getFirst() != null) { // On vérifie la nullité directement
            System.err.println("PairTest - Cas 3: Échec. getFirst() devait être null, a retourné " + pair3.getFirst());
            cas3_ok = false;
        }
        if (pair3.getSecond() != null) { // On vérifie la nullité directement
            System.err.println("PairTest - Cas 3: Échec. getSecond() devait être null, a retourné " + pair3.getSecond());
            cas3_ok = false;
        }
        if (cas3_ok) {
            System.out.println("PairTest - Cas 3 (valeurs null): Succès");
        }
        
        // Cas 4: Paire d'objets Nom (pour montrer la généricité avec des types personnalisés)
        Nom nomObj1 = new Nom("Nom Obj1", java.util.Arrays.asList("Nom", "Obj1"), "10");
        Nom nomObj2 = new Nom("Nom Obj2", java.util.Arrays.asList("Nom", "Obj2"), "11");
        Pair<Nom, Nom> pair4 = new Pair<>(nomObj1, nomObj2);
        boolean cas4_ok = true;
        if (!pair4.getFirst().equals(nomObj1)) {
            System.err.println("PairTest - Cas 4: Échec. getFirst() ne retourne pas le bon objet Nom.");
            cas4_ok = false;
        }
        if (!pair4.getSecond().equals(nomObj2)) {
            System.err.println("PairTest - Cas 4: Échec. getSecond() ne retourne pas le bon objet Nom.");
            cas4_ok = false;
        }
        if (cas4_ok) {
            System.out.println("PairTest - Cas 4 (objets Nom): Succès");
        }

    }
}

