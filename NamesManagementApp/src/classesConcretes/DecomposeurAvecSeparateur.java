package classesConcretes;

import interfaces.DecomposeurDeNoms;

import java.util.Arrays;
import java.util.List;

public class DecomposeurAvecSeparateur implements DecomposeurDeNoms {

    private final String separateurRegex;

    public DecomposeurAvecSeparateur() {
        // Par défaut, on accepte les séparateurs : espace, virgule, underscore, double étoile
        this.separateurRegex = "\\s+|,+|_+|\\*\\*+";
    }

    public DecomposeurAvecSeparateur(String separateurRegex) {
        this.separateurRegex = separateurRegex;
    }

    @Override
    public List<String> construire(String nomComplet) {
        return Arrays.asList(nomComplet.trim().split(separateurRegex)); //split à base d'expressions régulières 
    }
}
