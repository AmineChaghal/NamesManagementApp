package classesConcretes;

import interfaces.GenerateurDeCandidatsALaComparaison;
import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.CoupleDeNoms;

import java.util.*;

public class GenerateurParNbrPartiesNomDecompose implements GenerateurDeCandidatsALaComparaison {

    private final int incertitude;

    public GenerateurParNbrPartiesNomDecompose(int incertitude) {
        this.incertitude = incertitude;
    }

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<Nom> grandeListe, petiteListe;

        if (liste1.size() >= liste2.size()) {
            grandeListe = liste1;
            petiteListe = liste2;
        } else {
            grandeListe = liste2;
            petiteListe = liste1;
        }

        // Création de l'index : clé = nombre de parties du nom décomposé, valeur = liste de noms
        Map<Integer, List<Nom>> index = new HashMap<>();
        for (Nom nom : grandeListe) {
            int nbParties = nom.getNomDecompose().size();
            index.computeIfAbsent(nbParties, k -> new ArrayList<>()).add(nom);
        }

        // Génération des couples
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom : petiteListe) {
            int nbParties = nom.getNomDecompose().size();
            for (int i = nbParties - incertitude; i <= nbParties + incertitude; i++) {
                List<Nom> candidats = index.get(i);
                if (candidats != null) {
                    for (Nom autreNom : candidats) {
                        couples.add(new CoupleDeNoms(nom, autreNom));
                    }
                }
            }
        }

        return couples;
    }
}
