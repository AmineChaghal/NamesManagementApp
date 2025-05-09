package classesConcretes;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;

import interfaces.ComparateurDeChaines;


public class ComparateurJaroWinkler implements ComparateurDeChaines {

    private final JaroWinklerSimilarity jaroWinkler = new JaroWinklerSimilarity();

    
    @Override
    public float comparer(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 1.0f; // Deux nulls sont considérés comme identiques
        }
        if (s1 == null || s2 == null) {
            return 0.0f; // Un null et un non-null sont complètement différents
        }
        
        
        return jaroWinkler.apply(s1, s2).floatValue();
    }
}
