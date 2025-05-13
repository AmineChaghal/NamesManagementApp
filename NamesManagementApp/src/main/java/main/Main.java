package main;

import classesConcretes.*;
import classesPorteusesDeDonnees.Nom;
import classesPorteusesDeDonnees.ResultatDeComparaison;
import interfaces.*;
import moteur.MoteurDeMatchingDeNoms;

import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println("Début du programme");

		// Étape 1 : Définir le chemin du fichier
		String cheminFichier1 = "C:\\Users\\amine\\OneDrive\\Documents\\ENIT_2024_2025\\Mini projet java\\peps_names_32k.csv";
		String cheminFichier2 = "C:\\Users\\amine\\OneDrive\\Documents\\ENIT_2024_2025\\Mini projet java\\peps_names_658k.csv";

		// Étape 2 : Initialisation des composants
		DecomposeurDeNoms decomposeur = new DecomposeurAvecSeparateur();
		Pretraiteur pretraiteur = new PretraiteurConversionMinuscules();
		List<Pretraiteur> pretraiteurs = new ArrayList<>();
		pretraiteurs.add(pretraiteur);

		RecuperateurDeNomsDepuisCSV recuperateur1 = new RecuperateurDeNomsDepuisCSV(cheminFichier1, decomposeur);
		List<Nom> listeDeNoms1 = recuperateur1.recuperer();
		RecuperateurDeNomsDepuisCSV recuperateur2 = new RecuperateurDeNomsDepuisCSV(cheminFichier2, decomposeur);
		List<Nom> listeDeNoms2 = recuperateur2.recuperer();

		// Étape 3 : Initialiser les comparateurs de chaînes
		List<ComparateurDeChaines> comparateurs = new ArrayList<>();
		comparateurs.add(new ComparateurJaroWinkler());

		// Étape 4 : Construire les modules
		ComparateurDeNoms comparateur = new ComparateurDifferentesCombinaisonsDuNomDecompose(comparateurs);
		GenerateurDeCandidatsALaComparaison generateur = new GenerateurParPartiesDeNom();
		SelectionneurDeResultats selectionneur = new SelectionneurAvecSeuil(0.3);

		// Étape 5 : Instancier le moteur
		MoteurDeMatchingDeNoms moteur = new MoteurDeMatchingDeNoms(pretraiteurs, comparateur, generateur,
				selectionneur);

		// Étape 6 : Créer le nom à rechercher
		Nom nomRecherche = new Nom("000", "person");
		nomRecherche.setNomDecompose(decomposeur.construire(nomRecherche.getNomComplet()));
		List<ResultatDeComparaison> resultatsDeRecherche = new ArrayList<>();
		resultatsDeRecherche.addAll(moteur.rechercherUnNomDansUneListe(nomRecherche, listeDeNoms2));
		for (ResultatDeComparaison resultat : resultatsDeRecherche) {
			System.out.println("ID1: " + resultat.getNom1().getId() + " | Nom1: "
					+ resultat.getNom1().getNomComplet() + " | ID2: " + resultat.getNom2().getId() + " | Nom2: "
					+ resultat.getNom2().getNomComplet() + " | Score: " + resultat.getScore());
		}

		/*// Étape 7 : Lancer la recherche
		int tailleBloc = 10000;
		List<ResultatDeComparaison> resultats = new ArrayList<>();
		System.out.println("Résultats :");
		for (int i = 0; i < listeDeNoms1.size(); i += tailleBloc) {
			int fin = Math.min(i + tailleBloc, listeDeNoms1.size());
			List<Nom> sousListe = listeDeNoms1.subList(i, fin);

			List<ResultatDeComparaison> resultatsBloc = moteur.comparerDeuxListes(sousListe, listeDeNoms2);
			resultats.addAll(resultatsBloc);
			// Étape 8 : Affichage des résultats

			for (ResultatDeComparaison resultat : resultats) {
				System.out.println("ID1: " + resultat.getNom1().getId() + " | Nom1: "
						+ resultat.getNom1().getNomComplet() + " | ID2: " + resultat.getNom2().getId() + " | Nom2: "
						+ resultat.getNom2().getNomComplet() + " | Score: " + resultat.getScore());
			}
			resultats.clear();
			System.out.println(fin);
		}*/

		System.out.println("Fin du programme");
	}
}
