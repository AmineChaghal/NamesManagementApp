package classesConcretes;

import interfaces.RecuperateurDeNoms;
import interfaces.DecomposeurDeNoms;
import classesPorteusesDeDonnees.Nom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RecuperateurDeNomsDepuisCSV implements RecuperateurDeNoms {

    private final String cheminFichier;
    private final DecomposeurDeNoms decomposeur;

    public RecuperateurDeNomsDepuisCSV(String cheminFichier, DecomposeurDeNoms decomposeur) {
        this.cheminFichier = cheminFichier;
        this.decomposeur = decomposeur;
    }

    @Override
    public List<Nom> recuperer() {
        List<Nom> noms = new ArrayList<>();

        try (Stream<String> lignes = Files.lines(Paths.get(cheminFichier))) {
            lignes.skip(1) // Ignorer l'en-tête
                  .map(ligne -> ligne.split(","))
                  .filter(champs -> champs.length >= 2)
                  .forEach(champs -> {
                      String id = champs[0].trim();
                      String nomComplet = champs[1].trim();
                      Nom nom = new Nom(id, nomComplet);
                      nom.setNomDecompose(decomposeur.construire(nom.getNomComplet()));
                      noms.add(nom);
                  });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noms;
    }
}
