package classesConcretes;

import interfaces.Pretraiteur;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class PretraiteurSuppressionAccents implements Pretraiteur {

    public PretraiteurSuppressionAccents() {
		super();
	}


	private static final Pattern DIACRITICS_PATTERN =
        Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

   
    @Override
    public List<String> traiter(List<String> partiesDuNom) {
        if (partiesDuNom == null) {
            return new ArrayList<>(); // Ou lever une exception
        }
        return partiesDuNom.stream()
                           .map(this::removeAccents)
                           .collect(Collectors.toList());
    }

    
    private String removeAccents(String input) {
        if (input == null) {
            return "";
        }
        // 1. Normaliser la chaîne en NFD (Canonical Decomposition)
        // Cela sépare les caractères de base de leurs marques diacritiques.
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        
        // 2. Supprimer toutes les marques diacritiques (qui sont dans le bloc Unicode "Combining Diacritical Marks")
        return DIACRITICS_PATTERN.matcher(normalizedString).replaceAll("");
    }
}
