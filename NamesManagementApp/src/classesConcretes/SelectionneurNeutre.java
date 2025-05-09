package classesConcretes;

import java.util.List;

import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.SelectionneurDeResultats;

public class SelectionneurNeutre implements SelectionneurDeResultats {
    @Override
    public List<ResultatDeComparaison> selectionner(List<ResultatDeComparaison> listeDeResultats) {
        return listeDeResultats; // Ne filtre rien
    }
}



