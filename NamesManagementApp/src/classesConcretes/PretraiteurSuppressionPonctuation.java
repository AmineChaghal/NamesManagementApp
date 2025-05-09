package classesConcretes;

import interfaces.Pretraiteur;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PretraiteurSuppressionPonctuation implements Pretraiteur {

   
    @Override
    public List<String> traiter(List<String> partiesDuNom) {
        if (partiesDuNom == null) {
            return new ArrayList<>(); // Ou lever une exception
        }
        return partiesDuNom.stream()
                           .map(this::removePunctuation)
                           .collect(Collectors.toList());
    }

   
    private String removePunctuation(String input) {
        if (input == null) {
            return "";
        }
        // Remplace tout ce qui n'est pas une lettre, un chiffre, un espace ou un tiret par une chaîne vide.
        // \w est équivalent à [a-zA-Z_0-9] mais on veut aussi les espaces et les tirets, et exclure le underscore s'il n'est pas voulu.
        // \p{L} pour les lettres Unicode, \p{N} pour les chiffres Unicode.
        return input.replaceAll("[^\\p{L}\\p{N}\\s-]", "");
    }
}
