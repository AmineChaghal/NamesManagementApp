package classesConcretes;

import java.util.*;

import classesPorteusesDeDonnees.Nom;
import interfaces.ComparateurDeChaines;
import interfaces.ComparateurDeNoms;

public class ComparateurDifferentesCombinaisonsDuNomDecompose implements ComparateurDeNoms {

    private List<ComparateurDeChaines> comparateursDeChaines;

    public ComparateurDifferentesCombinaisonsDuNomDecompose(List<ComparateurDeChaines> comparateursDeChaines) {
        this.comparateursDeChaines = comparateursDeChaines;
    }

    @Override
    public double comparer(Nom n1, Nom n2) {
        List<String> l1 = n1.getNomDecompose();
        List<String> l2 = n2.getNomDecompose();

        if (l1.isEmpty() || l2.isEmpty()) return 0f;

        // L1 sera toujours la plus petite
        List<String> petite = l1.size() <= l2.size() ? l1 : l2;
        List<String> grande = l1.size() > l2.size() ? l1 : l2;

        int taille = petite.size();

        List<List<String>> permutationsPetite = genererPermutations(petite);
        List<List<String>> sousListesGrande = genererSousListes(grande, taille);

        double meilleurScore = 0f;

        for (List<String> p : permutationsPetite) {
            for (List<String> s : sousListesGrande) {
                double score = comparerListes(p, s)/l2.size();
                if (score > meilleurScore) meilleurScore = score;
            }
        }

        return meilleurScore;
    }

    private double comparerListes(List<String> l1, List<String> l2) {
        double total = 0f;
        for (int i = 0; i < l1.size(); i++) {
            double score = 0f;
            for (ComparateurDeChaines comp : comparateursDeChaines) {
                score += comp.comparer(l1.get(i), l2.get(i));
            }
            score /= comparateursDeChaines.size();
            total += score;
        }
        return total ;  // moyenne
    }

    private List<List<String>> genererSousListes(List<String> liste, int taille) {
        List<List<String>> resultats = new ArrayList<>();
        for (int i = 0; i <= liste.size() - taille; i++) {
            resultats.add(new ArrayList<>(liste.subList(i, i + taille)));
        }
        return resultats;
    }

    private List<List<String>> genererPermutations(List<String> liste) {
        List<List<String>> resultats = new ArrayList<>();
        permuter(liste, 0, resultats);
        return resultats;
    }

    private void permuter(List<String> liste, int index, List<List<String>> resultats) {
        if (index == liste.size()) {
            resultats.add(new ArrayList<>(liste));
            return;
        }

        for (int i = index; i < liste.size(); i++) {
            Collections.swap(liste, i, index);
            permuter(liste, index + 1, resultats);
            Collections.swap(liste, i, index);
        }
    }
}
