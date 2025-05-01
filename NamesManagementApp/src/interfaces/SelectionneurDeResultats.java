package interfaces;

import java.util.List;

import classesPorteusesDeDonnees.ResultatDeComparaison;

public interface SelectionneurDeResultats {
	List<ResultatDeComparaison> selectionner(List<ResultatDeComparaison> listeDeResultats);
}
