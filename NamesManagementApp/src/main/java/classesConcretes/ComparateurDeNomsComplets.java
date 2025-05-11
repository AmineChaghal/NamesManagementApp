package classesConcretes;

import java.util.List;

import classesPorteusesDeDonnees.Nom;
import interfaces.ComparateurDeChaines;
import interfaces.ComparateurDeNoms;

public class ComparateurDeNomsComplets implements ComparateurDeNoms {
	List<ComparateurDeChaines> listeDeComparateursDeChaine;

	@Override
	public double comparer(Nom n1, Nom n2) {
		double x = 0;
		for (ComparateurDeChaines comp : listeDeComparateursDeChaine) {
			x += comp.comparer(n1.getNomComplet(), n2.getNomComplet());
		}
		return x / listeDeComparateursDeChaine.size();
	}

	public ComparateurDeNomsComplets(List<ComparateurDeChaines> listeDeComparateursDeChaine) {
		super();
		this.listeDeComparateursDeChaine = listeDeComparateursDeChaine;
	}

}
