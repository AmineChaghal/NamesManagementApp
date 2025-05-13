package classesConcretes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;

import interfaces.GenerateurDeCandidatsALaComparaison;

public class GenerateurParPartiesDeNom implements GenerateurDeCandidatsALaComparaison {

    private Map<String, Set<Nom>> index = new HashMap<>();
    private boolean indexCree = false;

    public void creerIndexSiNonDejaFait(List<Nom> liste2) {
        if (indexCree) return;

        for (Nom nom : liste2) {
            for (String partie : nom.getNomDecompose()) {
                String cle = partie.toLowerCase();
                index.computeIfAbsent(cle, k -> new HashSet<>()).add(nom);
            }
        }

        indexCree = true;
    }

    public void viderIndex() {
        index.clear();
        indexCree = false;
    }

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        creerIndexSiNonDejaFait(liste2);

        List<CoupleDeNoms> couples = new ArrayList<>();

        for (Nom nomSource : liste1) {
            Set<Nom> candidats = new HashSet<>();

            for (String partie : nomSource.getNomDecompose()) {
                String cle = partie.toLowerCase();
                if (index.containsKey(cle)) {
                    candidats.addAll(index.get(cle));
                }
            }

            for (Nom candidat : candidats) {
                couples.add(new CoupleDeNoms(nomSource, candidat));
            }
        }

        return couples;
    }
}
