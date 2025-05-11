package classesConcretes;

import interfaces.SelectionneurDeResultats;
import classesPorteusesDeDonnees.ResultatDeComparaison;

import java.util.Collections;
import java.util.List;

public class SelectionneurDesNMeilleurs implements SelectionneurDeResultats {

    private int n;

    public SelectionneurDesNMeilleurs(int n) {
        this.n = n;
    }

    @Override
    public List<ResultatDeComparaison> selectionner(List<ResultatDeComparaison> listeDeResultats) {
        Collections.sort(listeDeResultats);  // Assure-toi que compareTo trie par score décroissant
        return listeDeResultats.subList(0, Math.min(n, listeDeResultats.size()));
    }
}
