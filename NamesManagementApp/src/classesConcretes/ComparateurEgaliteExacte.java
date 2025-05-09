package classesConcretes;

import interfaces.ComparateurDeChaines;


public class ComparateurEgaliteExacte implements ComparateurDeChaines {

    
    @Override
    public float comparer(String s1, String s2) {
        // Gérer les cas de nullité
        if (s1 == null && s2 == null) {
            return 1.0f; // Deux nulls peuvent être considérés comme égaux dans ce contexte
        }
        if (s1 == null || s2 == null) {
            return 0.0f; // Un null et un non-null ne sont pas égaux
        }
        // Comparaison exacte
        return s1.equals(s2) ? 1.0f : 0.0f;
    }
}
