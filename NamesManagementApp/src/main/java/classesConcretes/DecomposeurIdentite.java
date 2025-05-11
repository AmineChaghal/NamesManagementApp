package classesConcretes;

import java.util.ArrayList;
import java.util.List;

import interfaces.DecomposeurDeNoms;

public class DecomposeurIdentite implements DecomposeurDeNoms {
    @Override
    public List<String> construire(String nomComplet) {
        List<String> resultat = new ArrayList<>();
        resultat.add(nomComplet); // pas de vraie décomposition
        return resultat;
    }
}

