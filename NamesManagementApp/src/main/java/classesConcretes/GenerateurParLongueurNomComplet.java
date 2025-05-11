package classesConcretes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import interfaces.GenerateurDeCandidatsALaComparaison;

public class GenerateurParLongueurNomComplet implements GenerateurDeCandidatsALaComparaison {

    private int incertitude;

    public GenerateurParLongueurNomComplet(int incertitude) {
        this.incertitude = incertitude;
    }

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<Nom> grandeListe, petiteListe;

        // Identifier la grande et la petite liste
        if (liste1.size() >= liste2.size()) {
            grandeListe = liste1;
            petiteListe = liste2;
        } else {
            grandeListe = liste2;
            petiteListe = liste1;
        }

        // Création d’un index (map) basé sur la longueur du nom complet
        Map<Integer, List<Nom>> index = new HashMap<>();
        for (Nom nom : grandeListe) {
            int taille = nom.getNomComplet().length();
            index.computeIfAbsent(taille, k -> new ArrayList<>()).add(nom);
        }

        List<CoupleDeNoms> couples = new ArrayList<>();

        // Parcours de la petite liste
        for (Nom nomPetit : petiteListe) {
            int tailleNom = nomPetit.getNomComplet().length();
            
            // Recherche des tailles dans l’intervalle [taille - incertitude, taille + incertitude]
            for (int i = tailleNom - incertitude; i <= tailleNom + incertitude; i++) {
                List<Nom> candidats = index.get(i);
                if (candidats != null) {
                    for (Nom nomGrand : candidats) {
                        // Conserver l’ordre d’origine pour les couples
                        couples.add(new CoupleDeNoms(nomPetit, nomGrand));
                    }
                }
            }
        }

        return couples;
    }
}
