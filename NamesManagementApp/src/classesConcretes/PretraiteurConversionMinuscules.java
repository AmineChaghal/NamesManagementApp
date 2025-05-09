package classesConcretes;

import interfaces.Pretraiteur;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Prétraitement : Conversion de la casse en minuscules.
 * Ce prétraitement convertit chaque partie du nom en minuscules.
 * Par exemple, "Jean-Pierre", "DUPONT" deviendrait "jean-pierre", "dupont".
 */
public class PretraiteurConversionMinuscules implements Pretraiteur {

    /**
     * Convertit chaque chaîne de la liste fournie en minuscules.
     * @param partiesDuNom La liste des chaînes (tokens) à prétraiter.
     * @return Une nouvelle liste de chaînes où chaque chaîne a été convertie en minuscules.
     */
    @Override
    public List<String> traiter(List<String> partiesDuNom) {
        if (partiesDuNom == null) {
            return new ArrayList<>(); // Ou lever une exception selon la gestion d'erreur souhaitée
        }
        return partiesDuNom.stream()
                           .map(partie -> partie == null ? "" : partie.toLowerCase())
                           .collect(Collectors.toList());
    }
}
