package classesConcretes;

import classesPorteusesDeDonnees.CoupleDeNoms;
import classesPorteusesDeDonnees.Nom;
import interfaces.GenerateurDeCandidatsALaComparaison;

import java.util.*;
import java.util.stream.Collectors;

public class GenerateurMulticritere implements GenerateurDeCandidatsALaComparaison {

    private int incertitude;

    public GenerateurMulticritere(int incertitude) {
        this.incertitude = incertitude;
    }

    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        Map<String, Map<Integer, Map<Integer, List<Nom>>>> index = new HashMap<>();

        // Construire l'index sur la plus grande des deux listes
        List<Nom> grandeListe = (liste1.size() >= liste2.size()) ? liste1 : liste2;
        List<Nom> petiteListe = (liste1.size() < liste2.size()) ? liste1 : liste2;

        for (Nom nom : grandeListe) {
            String alphabet = detecterAlphabet(nom.getNomComplet());
            int longueur = nom.getNomComplet().length();
            int nbParties = nom.getNomDecompose().size();

            index
                .computeIfAbsent(alphabet, k -> new HashMap<>())
                .computeIfAbsent(longueur, k -> new HashMap<>())
                .computeIfAbsent(nbParties, k -> new ArrayList<>())
                .add(nom);
        }

        List<CoupleDeNoms> couples = new ArrayList<>();

        // Chercher les correspondances
        for (Nom nomPetit : petiteListe) {
            String alphabet = detecterAlphabet(nomPetit.getNomComplet());
            int longueur = nomPetit.getNomComplet().length();
            int nbParties = nomPetit.getNomDecompose().size();
            String initialesPetit = extraireInitialesTriees(nomPetit);

            for (int l = longueur - incertitude; l <= longueur + incertitude; l++) {
                Map<Integer, Map<Integer, List<Nom>>> mapLongueur = index.get(alphabet);
                if (mapLongueur == null) continue;

                Map<Integer, List<Nom>> mapNbParties = mapLongueur.get(l);
                if (mapNbParties == null) continue;

                for (int p = nbParties - 1; p <= nbParties + 1; p++) {
                    List<Nom> candidats = mapNbParties.getOrDefault(p, Collections.emptyList());

                    for (Nom nomGrand : candidats) {
                        String initialesGrand = extraireInitialesTriees(nomGrand);

                        if (initialesProches(initialesPetit, initialesGrand)) {
                            couples.add(new CoupleDeNoms(nomPetit, nomGrand));
                        }
                    }
                }
            }
        }

        return couples;
    }

    private String detecterAlphabet(String texte) {
        for (char c : texte.toCharArray()) {
            Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
            if (block == Character.UnicodeBlock.ARABIC) {
                return "ARABE";
            } else if (block == Character.UnicodeBlock.BASIC_LATIN) {
                return "LATIN";
            }
        }
        return "AUTRE";
    }

    private String extraireInitialesTriees(Nom nom) {
        return nom.getNomDecompose().stream()
                .filter(partie -> !partie.isEmpty())
                .map(partie -> partie.substring(0, 1).toUpperCase())
                .sorted()
                .reduce("", String::concat);
    }

    private boolean initialesProches(String init1, String init2) {
        Set<Character> s1 = init1.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<Character> s2 = init2.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

        Set<Character> union = new HashSet<>(s1);
        union.addAll(s2);

        Set<Character> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);

        int diff = union.size() - intersection.size();
        return diff <= 1;
    }
}
