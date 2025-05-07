package classesConcretes;

import interfaces.SelectionneurDeResultats;
import classesPorteusesDeDonnees.ResultatDeComparaison;

import java.util.ArrayList;
import java.util.List;

public class SelectionneurAvecSeuil implements SelectionneurDeResultats {

    private float seuil;

    public SelectionneurAvecSeuil(float seuil) {
        this.seuil = seuil;
    }

    @Override
    public List<ResultatDeComparaison> selectionner(List<ResultatDeComparaison> listeDeResultats) {
        List<ResultatDeComparaison> resultat = new ArrayList<>();
        for (ResultatDeComparaison r : listeDeResultats) {
            if (r.getScore() >= seuil) {
                resultat.add(r);
            }
        }
        return resultat;
    }
}
