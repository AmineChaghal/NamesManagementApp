package classesConcretes;

import org.apache.commons.text.similarity.LevenshteinDistance;

import interfaces.ComparateurDeChaines;


public class ComparateurLevenshtein implements ComparateurDeChaines {

    private final LevenshteinDistance levenshtein = new LevenshteinDistance();

    
    @Override
    public double comparer(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 1.0f; // Deux nulls sont considérés comme identiques
        }
        if (s1 == null || s2 == null) {
            return 0.0f; // Un null et un non-null sont complètement différents
        }

        // Si les deux chaînes sont vides, elles sont identiques.
        if (s1.isEmpty() && s2.isEmpty()) {
            return 1.0f;
        }

        int distance = levenshtein.apply(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());

       

        // Normalisation du score : 1 - (distance / longueur maximale)
        // Plus la distance est petite, plus le score de similarité est élevé.
        return 1.0f - ((double) distance / maxLength);
    }
}
