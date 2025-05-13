package classesConcretes;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import interfaces.GenerateurDeCandidatsALaComparaison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateurTroisAleatoires implements GenerateurDeCandidatsALaComparaison {

    public GenerateurTroisAleatoires() {
		super();
	}

	private final Random random = new Random();

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleDeNoms> couples = new ArrayList<>();

        if (liste2.isEmpty()) return couples;

        // On sélectionne 3 noms aléatoires de liste2
        List<Nom> sousListe2 = new ArrayList<>(liste2);
        Collections.shuffle(sousListe2, random);
        List<Nom> choisis = sousListe2.subList(0, Math.min(3, sousListe2.size()));

        // Pour chaque nom de liste1, on crée un couple avec les 3 noms choisis
        for (Nom nom1 : liste1) {
            for (Nom nom2 : choisis) {
                couples.add(new CoupleDeNoms(nom1, nom2));
            }
        }

        return couples;
    }
}
