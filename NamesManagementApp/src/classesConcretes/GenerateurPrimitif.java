package classesConcretes;

import java.util.ArrayList;
import java.util.List;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import interfaces.GenerateurDeCandidatsALaComparaison;

public class GenerateurPrimitif implements GenerateurDeCandidatsALaComparaison {
    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            for (Nom nom2 : liste2) {
                couples.add(new CoupleDeNoms(nom1, nom2)); // sans score ici
            }
        }
        return couples;
    }
}

