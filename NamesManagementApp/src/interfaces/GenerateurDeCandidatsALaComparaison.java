package interfaces;

import java.util.List;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;

public interface GenerateurDeCandidatsALaComparaison {
	List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2);

}
